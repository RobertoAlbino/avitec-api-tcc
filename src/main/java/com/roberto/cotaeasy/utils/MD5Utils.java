package com.roberto.cotaeasy.utils;

import java.security.*;

public class MD5Utils {

    public static String encriptarSenha(String senha) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(senha.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
