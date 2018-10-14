package com.qinxc.basic;


/**
 * Created by qxc on 2018/7/20.
 */
public class doubleLost {

    public static char[] getBinaryChars(Float f) {
        char[] result = new char[32];
        char[] binaryChars = Integer.toBinaryString(Float.floatToIntBits(f)).toCharArray();
        if (binaryChars.length < result.length) {
            System.arraycopy(binaryChars, 0, result, result.length - binaryChars.length, binaryChars.length);
            for (int i = 0; i < result.length - binaryChars.length; i++) {
                result[i] = '0';
            }
        } else {
            result = binaryChars;
        }
        return result;
    }

    public static char[] getBinaryChars(Double f) {
        char[] result = new char[64];
        char[] binaryChars = Long.toBinaryString(Double.doubleToLongBits(f)).toCharArray();
        if (binaryChars.length < result.length) {
            System.arraycopy(binaryChars, 0, result, result.length - binaryChars.length, binaryChars.length);
            for (int i = 0; i < result.length - binaryChars.length; i++) {
                result[i] = '0';
            }
        } else {
            result = binaryChars;
        }
        return result;
    }

    public static void printFloatBinaryString(Double f) {
        char[] binaryChars = getBinaryChars(f);
        for (int i = 0; i < binaryChars.length; i++) {
            System.out.print(binaryChars[i]);
            if (i == 0 || i == 8) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String args[]) throws Exception {

        String sdouble = "1";
        int ans = (int) Math.ceil(Double.valueOf(sdouble));
        System.out.println(ans);

//        float a = 0.3f;
//        float b = 0.1f + 0.2f;
//        System.out.println(a);
//        printFloatBinaryString(a);
//        System.out.println(b);
//        printFloatBinaryString(b);

        Double a = 0.3;
        Double b = 0.1 + 0.2;
        System.out.println(a);
        printFloatBinaryString(a);
        System.out.println(b);
        printFloatBinaryString(b);

        Double e = 2.99999999999999988897769753748E-1;
        System.out.println(e);

        String floatStr = "1";
        Float aFloat = Float.parseFloat(floatStr);
        System.out.println(aFloat);

    }


}
