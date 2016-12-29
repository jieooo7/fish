package com.fish.securety;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESHelper {

    /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/ECB/PKCS5Padding";

    /** 创建密钥 **/
    private static SecretKeySpec createKey(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(32);
        sb.append(password);
        while (sb.length() < 32) {
            sb.append("0");
        }
        if (sb.length() > 32) {
            sb.setLength(32);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    /** 加密字节数据 **/
    public static byte[] encrypt(byte[] content, String password) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 加密(结果为16进制字符串) **/
    public static String encrypt(String content, String password) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encrypt(data, password);
        String result = byte2hex(data);
        return result;
    }

    /** 解密字节数组 **/
    public static byte[] decrypt(byte[] content, String password) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 解密16进制的字符串为字符串 **/
    public static String decrypt(String content, String password) {
        byte[] data = null;
        try {
            data = hex2byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, password);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** 字节数组转成16进制字符串 **/
    public static String byte2hex(byte[] b) { // 一个字节的数，
        StringBuffer sb = new StringBuffer(b.length * 2);
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }

    /** 将hex字符串转换成字节数组 **/
    private static byte[] hex2byte(String inputString) {
        if (inputString == null || inputString.length() < 2) {
            return new byte[0];
        }
        inputString = inputString.toLowerCase();
        int l = inputString.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = inputString.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }

    public static void main(String args[]) throws Exception {

        BCryptPasswordEncoder bp=new BCryptPasswordEncoder();

        System.out.println(bp.encode("e10adc3949ba59abbe56e057f20f883e"));

        String html="        <p>请输入内容...<img src=\"/files/2d6cbbdcd0bf584699d5b4506711d81a\" alt=\"8ABF8049F3BFB42B3FEFA6B8C733AE81\" style=\"max-width: 100%;\">" +
                "<br><img src=\"/files/ecf0c4df99298daaad49333b661fea62\" alt=\"F72D72067210E8CAA460736FD35AE2AE\" style=\"max-width: 100%;\"></p>\n" +
                "        <p><br></p>";

        Pattern p = Pattern.compile("<img[\\s]+src[\\s]*=[\\s]*((['\"](?<src>[^'\"]*)[\\'\"])|(?<src1>[^\\s]*))");
        Matcher m = p.matcher(html);

        while (m.find()){
            System.out.println("+++++++"+m.group("src"));
            System.out.println("+++++++"+m.group("src1"));

        }



    }
}