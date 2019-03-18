package com.alex.model;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class HashService {
    public static String getHash(String password) {
        try {
            String salt = "1234";
            int iterations = 10000;
            int keyLength = 512;
            char[] passwordChars = password.toCharArray();
            byte[] saltBytes = salt.getBytes();
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( passwordChars, saltBytes, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return Hex.encodeHexString(res);
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public static boolean compare(String inputPassword, String originalPasswordHash) {
        String inputPasswordHash = getHash(inputPassword);
        return inputPasswordHash.equals(originalPasswordHash);
    }
}
