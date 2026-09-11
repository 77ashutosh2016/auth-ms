package com.ekart.Auth.service;

import com.ekart.Auth.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final RSAKey rsaKey;

    public JwtService(RSAKey rsaKey)
    {
        this.rsaKey=rsaKey;
    }

    public String generateToken(User user) throws JOSEException
    {

        JWTClaimsSet claims=new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .claim("roles",user.getUserRoles())
                .issuer("auth-service")
                .expirationTime(new Date(System.currentTimeMillis()+15*60*1000))
                .build();

        JWSHeader header=new JWSHeader.Builder(JWSAlgorithm.RS256)
                .keyID(rsaKey.getKeyID())
                .build();
        SignedJWT signedJWT=new SignedJWT(header, claims);
        signedJWT.sign(new RSASSASigner(rsaKey.toPrivateKey()));
        return signedJWT.serialize();



    }

}
