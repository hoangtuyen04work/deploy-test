package io.github.hoangtuyen04work.social_backend.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import io.github.hoangtuyen04work.social_backend.exception.AppException;
import io.github.hoangtuyen04work.social_backend.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import com.nimbusds.jwt.SignedJWT;import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenUtils {
    private String SIGNER_KEY;

    public boolean isValidToken(String token) throws JOSEException, ParseException, AppException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);
        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.NOT_AUTHENTICATION);
        }
        return true;
    }
}
