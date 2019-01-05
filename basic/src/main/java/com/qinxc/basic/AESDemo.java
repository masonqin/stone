package com.qinxc.basic;


import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;


/**
 * Created by qxc on 2018/6/12.
 */
public class AESDemo {

    String key = "new_face01";
    String signature = "tg_ad_adx_rule";

    /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_LOWER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_UPPER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public String encrypt(String key, String signature, String target) throws Exception {

        String genMD5 = getKeyAndIV(key, signature);
        System.out.println(genMD5.substring(0, 16));
        System.out.println(genMD5.substring(16, 32));
        SecretKey aesKey = new SecretKeySpec(genMD5.substring(0, 16).getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(genMD5.substring(16, 32).getBytes());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
        byte[] encryptBytes = cipher.doFinal(target.getBytes());
        return new String(java.util.Base64.getEncoder().encode(encryptBytes));
    }

    public byte[] encrypt(String key, String target) throws Exception {

        SecretKey aesKey = new SecretKeySpec(key.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptBytes = cipher.doFinal(target.getBytes());
        return encryptBytes;

//        SecretKey aesKey = new SecretKeySpec(key.getBytes(), "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
//        byte[] result = cipher.doFinal(target.getBytes());
//        return result;
    }

//    public static String urlSafeBase64(byte[] bytes){
//        return Base64.encodeBase64URLSafeString(bytes);
//    }
//
//    public static byte[] unUrlSafeBase64(String str) throws UnsupportedEncodingException {
//        return Base64.decodeBase64(str.getBytes("UTF-8"));
//    }

    public String decrypt(String key, String signature, String target) throws Exception {

        String genMD5 = getKeyAndIV(key, signature);
        SecretKey aesKey = new SecretKeySpec(genMD5.substring(0, 16).getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(genMD5.substring(16, 32).getBytes());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
        byte[] decryptBytes = cipher.doFinal(java.util.Base64.getMimeDecoder().decode(target));
        return new String(decryptBytes);
    }


    public String getKeyAndIV(String key, String signature) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((key + signature).getBytes());
        byte[] ret = md.digest();
        return new String(encodeHex(ret, DIGITS_LOWER));
    }

    char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }


    public static void main(String[] args) throws Exception {

        AESDemo aesDemo = new AESDemo();

        String target = "This is the target string need to be encrypt";

        String enresult = aesDemo.encrypt(aesDemo.key, aesDemo.signature, target);

        String deresult = aesDemo.decrypt(aesDemo.key, aesDemo.signature, enresult);

        System.out.println(target);
        System.out.println(enresult);
        System.out.println(deresult);
        System.out.println(target.equals(deresult));

        System.out.println("==========");
        target = "code_id=a10001&os_ver=5.1.1&app_ver=7.0&imei=867068020992938&mac=9C:99:A0:FF:E9:15&android_id=eedbdb39f66910a4&sw=1440&sh=2560&ot=1&ct=100";
        String key = "wina012300000000";
        byte[] retBytes = aesDemo.encrypt(key, target);
        char[] finalBytes = aesDemo.encodeHex(retBytes, DIGITS_UPPER);

        System.out.println(new String(finalBytes));

        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
        System.out.println(DigestUtils.md5DigestAsHex("!Q@W#E$R%T^Y".getBytes()));
    }

}
