package com.veridu.idos.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class IdOSUtils {

    /**
     * Generates companyToken necessary for many requests to the API
     * 
     * @param servicePublicKey
     *            The issuer credential public key
     * @param servicePrivateKey
     *            The issuer credential private key
     * @param subjectPublicKey
     *            The subject credential public key
     * @return the generated companyToken
     */
    public static String generateToken(String issuerPrivateKey, String issuerPublicKey, String subjectPublicKey) {
        byte[] apiKeySecretBytes = issuerPrivateKey.getBytes();
        JwtBuilder jwt = Jwts.builder().setIssuer(issuerPublicKey);
        if (!subjectPublicKey.isEmpty())
            jwt.setSubject(subjectPublicKey);
        String token = jwt.signWith(SignatureAlgorithm.HS256, apiKeySecretBytes).compact();
        return token;
    }

    /**
     * Generates a company companyToken when subject exists
     * 
     * @param companyPrivateKey
     * @param companyPublicKey
     * @param subject
     * @return String companyToken
     */
    public static String generateManagementToken(String privateKey, String publicKey, String subject) {
        return IdOSUtils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a company companyToken without subject
     * 
     * @param companyPrivateKey
     * @param companyPublicKey
     * @return String companyToken
     */
    public static String generateManagementToken(String privateKey, String publicKey) {
        return IdOSUtils.generateToken(privateKey, publicKey, "");
    }

    /**
     * Generates a credential companyToken when subject exists
     * 
     * @param companyPrivateKey
     * @param companyPublicKey
     * @param subject
     * @return String companyToken
     */
    public static String generateHandlerToken(String privateKey, String publicKey, String subject) {
        return IdOSUtils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a user companyToken when subject exists
     * 
     * @param companyPrivateKey
     * @param companyPublicKey
     * @param subject
     * @return String companyToken
     */
    public static String generateUserToken(String privateKey, String publicKey, String subject) {
        return IdOSUtils.generateToken(privateKey, publicKey, subject);
    }

    /**
     * Generates a user companyToken without subject
     * 
     * @param companyPrivateKey
     * @param companyPublicKey
     * @return
     */
    public static String generateUserToken(String privateKey, String publicKey) {
        return IdOSUtils.generateToken(privateKey, publicKey, "");
    }
}
