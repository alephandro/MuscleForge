package com.example.gym.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class HashUtils {

	public static String hashPassword(String password) {
		return toHexString(hashString(password));
	}

	private static byte[] hash(byte[] bytes) {
		byte[] hex = new byte[]{0};
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			hex = digest.digest(bytes);
		} catch (NoSuchAlgorithmException ignored) {}
		return hex;
	}

	private static byte[] hashString(String string) {
        return hash(string.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while(hexString.length() < 64)
            hexString.insert(0, '0');

        return hexString.toString();
    }

}
