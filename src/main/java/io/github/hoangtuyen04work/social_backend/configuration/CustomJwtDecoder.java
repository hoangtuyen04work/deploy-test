package io.github.hoangtuyen04work.social_backend.configuration;

import io.github.hoangtuyen04work.social_backend.exception.AppException;
import io.github.hoangtuyen04work.social_backend.exception.ErrorCode;
import io.github.hoangtuyen04work.social_backend.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {
    private String SIGNED_KEY;
    private NimbusJwtDecoder nimbusJwtDecoder;
    @Autowired
    private TokenUtils tokenUtils;
    @Override
    public Jwt decode(String token) throws JwtException {
        try{
            tokenUtils.isValidToken(token);
            if(Objects.isNull(nimbusJwtDecoder)){
                SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNED_KEY.getBytes(), "HmacSHA512");
                nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                        .macAlgorithm(MacAlgorithm.HS512)
                        .build();
                return nimbusJwtDecoder.decode(token);
            }
        } catch (Exception e) {
            try {
                throw new AppException(ErrorCode.NOT_AUTHENTICATION);
            } catch (AppException ex) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }
}
