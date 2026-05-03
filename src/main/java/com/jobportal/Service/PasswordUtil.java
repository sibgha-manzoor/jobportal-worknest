package com.jobportal.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Password hashing utility using SHA-256 + random salt.
 * No external dependencies required.
 * Format stored in DB:  SALT:HASH  (both Base64-encoded)
 */
public class PasswordUtil {

    private static final int SALT_BYTES = 32;

    public static String hashPassword(String rawPassword) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);

        byte[] hash = sha256(salt, rawPassword);

        String saltB64 = Base64.getEncoder().encodeToString(salt);
        String hashB64 = Base64.getEncoder().encodeToString(hash);

        return saltB64 + ":" + hashB64;
    }

    public static boolean checkPassword(String rawPassword, String stored) {
        if (stored == null || !stored.contains(":")) {
            // Legacy plain-text fallback (for old records before hashing was added)
            return rawPassword.equals(stored);
        }
        String[] parts = stored.split(":", 2);
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
        byte[] actualHash = sha256(salt, rawPassword);
        return MessageDigest.isEqual(actualHash, expectedHash); // constant-time compare
    }

    private static byte[] sha256(byte[] salt, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            md.update(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}