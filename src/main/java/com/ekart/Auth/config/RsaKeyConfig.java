package com.ekart.Auth.config;



import com.ekart.Auth.exception.NoSuchAlogrithmExceptionNT;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


// This is first Step in Developement of  Auth feature

@Slf4j
@Configuration
public class RsaKeyConfig {

    @Bean
    public KeyPair keyPair()
    {
        try {

            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            return generator.generateKeyPair();
            // here we are generating keypair of  Private key and Public key to verifiy identity
        }catch (NoSuchAlgorithmException ex)
        {
            log.error(" No Such Algo found");
            throw new NoSuchAlogrithmExceptionNT();
        }


    }




    /*Why a second bean instead of just using KeyPair directly everywhere? Because java security KeyPair is Java's generic representation — it doesn't know anything about JWTs, JWKS, or key IDs. RSAKey is Nimbus's own class, purpose-built to (a) sign JWTs and
            (b) export itself as a JWKS JSON blob. So this method's job is to convert the generic Java object into the JWT-library-specific object.
    */


    @Bean
    public RSAKey rsaKey(KeyPair keyPair)
    {
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();

    }

}
