package com.qinxc.basic;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

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
        System.out.println(genMD5.substring(0,16));
        System.out.println(genMD5.substring(16,32));
        SecretKey aesKey = new SecretKeySpec(genMD5.substring(0, 16).getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(genMD5.substring(16, 32).getBytes());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
        byte[] encryptBytes = cipher.doFinal(target.getBytes());
        return new String(Base64.getEncoder().encode(encryptBytes));
    }

    public String decrypt(String key, String signature, String target) throws Exception {

        String genMD5 = getKeyAndIV(key, signature);
        SecretKey aesKey = new SecretKeySpec(genMD5.substring(0, 16).getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(genMD5.substring(16, 32).getBytes());
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
        byte[] decryptBytes = cipher.doFinal(Base64.getMimeDecoder().decode(target));
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


    }

}
