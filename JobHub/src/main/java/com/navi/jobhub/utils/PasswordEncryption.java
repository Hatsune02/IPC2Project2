package com.navi.jobhub.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    public static String encryptPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder newPass = new StringBuilder();
            for(byte b: hashBytes){
                String hex = Integer.toHexString(0xff & b);
                if(hex.length()==1){
                    newPass.append(0);
                }
                newPass.append(hex);
            }
            return newPass.toString();

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
