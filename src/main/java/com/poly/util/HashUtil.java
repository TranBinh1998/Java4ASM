package com.poly.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {
    // Phuowngh thức hash() để băm moojy chuỗi bằng thuật toán SHA 256
    public static byte [] hash(String input) {
        try {
            // Tạo một đối tượng MessageDigest với thuật toán Sha 256

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Băm chuỗi input và trả về một byte kết quả

            return md.digest(input.getBytes());

        } catch (NoSuchAlgorithmException e) {
            throw  new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String pass = "binh12346";
        pass = Base64.getEncoder().encodeToString(HashUtil.hash(pass));
        // In ra kết quả
        System.out.println(pass);
    }
}
