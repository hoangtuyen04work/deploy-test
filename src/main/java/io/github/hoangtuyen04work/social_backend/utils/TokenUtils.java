package io.github.hoangtuyen04work.social_backend.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import io.github.hoangtuyen04work.social_backend.entities.Authority;
import io.github.hoangtuyen04work.social_backend.entities.RoleEntity;
import io.github.hoangtuyen04work.social_backend.entities.UserEntity;
import io.github.hoangtuyen04work.social_backend.exception.AppException;
import io.github.hoangtuyen04work.social_backend.exception.ErrorCode;
import io.github.hoangtuyen04work.social_backend.services.others.RefreshTokenService;
import io.github.hoangtuyen04work.social_backend.services.redis.TokenRedisService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TokenUtils {
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @Autowired
    private TokenRedisService redis;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public String getUserId(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getUserIdByToken(String token) throws ParseException {
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            return null;
        }
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public boolean removeToken(String token) throws ParseException {
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            return false;
        }
        String id = signedJWT.getJWTClaimsSet().getSubject();
        refreshTokenService.deleteRefreshTokenByUserId(id);
        redis.deleteToken(id);
        return true;
    }

    public void isValidToken(String token) throws AppException, ParseException, JOSEException {
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATION);
        }
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        boolean verified = signedJWT.verify(verifier);
        if (!verified) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATION);
        }
        String userId = signedJWT.getJWTClaimsSet().getSubject();
        String tokenRedis = redis.getToken(userId);
        if (tokenRedis == null || !tokenRedis.equals(token)) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATION);
        }
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expiryTime.before(new Date())) {
            redis.deleteToken(userId);
            throw new AppException(ErrorCode.NOT_AUTHENTICATION);
        }
        else{
            System.out.println("123123123123");
        }
    }

    public boolean checkToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        String id = signedJWT.getJWTClaimsSet().getSubject();
        String tokenRedis = redis.getToken(id);
        if(tokenRedis.isEmpty()) return false;
        else if(!tokenRedis.equals(token)) return false;
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);
        return verified && expiryTime.after(new Date());
    }

    public String generateToken(UserEntity user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet;
        if(user.getRoles().size() == 1){
            jwtClaimsSet = new JWTClaimsSet.Builder()
                    .issuer("hoangtuyen.com")
                    .subject(user.getId())
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(24*60*60, ChronoUnit.SECONDS)))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("roles",buildRoles(user.getRoles()))
                    .claim("scope", buildAuthorities(user.getRoles()))
                    .build();
        }
        else{
            jwtClaimsSet = new JWTClaimsSet.Builder()
                    .issuer("hoangtuyen.com")
                    .subject(user.getId())
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plus(24*60*60, ChronoUnit.SECONDS)))
                    .jwtID(UUID.randomUUID().toString())
                    .claim("roles",buildRoles(user.getRoles()))
                    .claim("scope", buildAuthorities(user.getRoles()))
                    .build();
        }
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(jwtClaimsSet.toJSONObject()));
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        String token =  jwsObject.serialize();
        redis.saveToken(user.getId(), token);
        return token;
    }

    //build list Roles for claim roles
    private List<String> buildRoles(Set<RoleEntity> roles){
        List<String> list = new ArrayList<>();
        for(RoleEntity role : roles) list.add(role.getRoleName());
        return list;
    }

    //Build list authorities for claim authorities
    private List<String> buildAuthorities(Set<RoleEntity> roles){
        List<String> list = new ArrayList<>();
        for(RoleEntity role : roles)
            for(Authority auth : role.getAuthorities())
                list.add(auth.getName());
        return list;
    }
}
