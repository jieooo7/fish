package com.fish.securety;

import com.fish.config.CommonData;
import com.fish.util.CodeGenetate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by thy on 16-11-13.
 */

public class MD5 {

    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }

    public static void main(String[] args){

        System.out.println(getMD5("your-name"));
        System.out.println(AESHelper.encrypt("464651", CommonData.ENCRYPT_KEY));
        System.out.println(AESHelper.decrypt("311C5B5F83ECF3FD7262AF58F6B5E23A", CommonData.ENCRYPT_KEY));
    }
}
