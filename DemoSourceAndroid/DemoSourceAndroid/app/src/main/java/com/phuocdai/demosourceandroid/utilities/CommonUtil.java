package com.phuocdai.demosourceandroid.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by phuocdai on 1/27/16.
 */
public class CommonUtil {
    public static String convertStringToMd5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest)
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
