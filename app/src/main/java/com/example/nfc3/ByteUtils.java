package com.example.nfc3;
import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;

public class ByteUtils {
    private static final char[] a = "0123456789ABCDEF".toCharArray();
    static final char[] b = "0123456789ABCDEF".toCharArray();

    public ByteUtils() {
    }


    public static byte[] hexString2ByteArray(String var0) {
        if (var0 != null && var0.length() != 0) {
            String var1;
            if (var0.length() % 2 != 0) {
                var1 = var0 + "0";
            } else {
                var1 = var0;
            }

            byte[] var3 = new byte[(var0.length() + 1) / 2];

            for (int var2 = 0; var2 < var3.length; var3[var2] = hexString2Byte(var1.substring(var2 * 2, ++var2 * 2))) {
            }

            return var3;
        } else {
            return null;
        }
    }

    public static byte hexString2Byte(String var0) {
        if (TextUtils.isEmpty(var0)) {
            return 0;
        } else {
            char var10000 = var0.charAt(0);
            char var2 = var0.charAt(1);
            byte var1;
            byte var4 = var1 = hexChar2Byte(var10000);
            byte var3 = hexChar2Byte(var2);
            return var4 >= 0 && var3 >= 0 ? (byte) ((var1 << 4) + var3) : 0;
        }
    }

    public static short[] StringAndShorttoShortArray(short[] var0, String var1) {
        byte[] var5 = hexString2ByteArray(var1.replaceAll(" ", ""));
        short[] var2;
        int var4;
        if (var0 != null) {
            var2 = new short[(var4 = var0.length) + var5.length];
            System.arraycopy(var0, 0, var2, 0, var4);
        } else {
            var4 = 0;
            var2 = new short[var5.length];
        }

        for (int var3 = 0; var3 < var5.length; ++var3) {
            var2[var4 + var3] = (short) var5[var3];
        }

        return var2;
    }

    public static short[] Bytes2ShortArray(byte[] var0) {
        short[] var1 = null;
        if (var0 != null) {
            var1 = new short[var0.length];

            for (int var2 = 0; var2 < var0.length; ++var2) {
                var1[var2] = (short) var0[var2];
            }
        }

        return var1;
    }

    public static byte hexChar2Byte(char var0) {
        if (var0 >= '0' && var0 <= '9') {
            return (byte) (var0 - 48);
        } else if (var0 >= 'a' && var0 <= 'f') {
            return (byte) (var0 - 97 + 10);
        } else {
            return var0 >= 'A' && var0 <= 'F' ? (byte) (var0 - 65 + 10) : -1;
        }
    }

    public static String byteArray2HexString(byte[] var0) {
        if (var0 != null && var0.length != 0) {
            byte var1 = 0;
            int var2;
            char[] var3 = new char[(var2 = var0.length) * 2];
            int var4 = -1;
            int var5 = var4;

            for (var4 = var1; var4 < var1 + var2; ++var4) {
                int var6 = (var0[var4] & 240) >> 4;
                int var7;
                int var10001 = var7 = ++var5 * 2;
                char[] var8;
                var3[var7] = (var8 = a)[var6];
                var6 = var0[var4] & 15;
                var3[var10001 + 1] = var8[var6];
            }

            return new String(var3);
        } else {
            return "";
        }
    }


    public static String getBCDString(byte[] var0, int var1, int var2) {
        byte[] var10000 = var0;
        byte[] var10002 = var0 = new byte[var2 - var1 + 1];
        System.arraycopy(var10000, var1, var10002, 0, var10002.length);
        return byteArray2HexString(var0);
    }

    public static String getBCDString(String var0) {
        byte[] var10000 = hexString2ByteArray(var0);
        return getBCDString(var10000, 0, var10000.length);
    }


    public static byte[] shortArray2bytes(short[] var0) {
        byte[] var1 = new byte[var0.length];

        for (int var2 = 0; var2 < var0.length; ++var2) {
            var1[var2] = (byte) (var0[var2] & 255);
        }

        return var1;
    }

    public static byte[] shortArray2bytes2(short[] var0) {
        byte[] var1 = new byte[var0.length * 2];

        for (int var2 = 0; var2 < var0.length; ++var2) {
            int var3;
            int var10001 = var3 = var2 * 2;
            var1[var3 + 1] = (byte) (var0[var2] / 256);
            var1[var10001] = (byte) (var0[var2] & 255);
        }

        return var1;
    }

    public static byte[] short2ByteArrayLow(short var0) {
        return new byte[]{(byte) (var0 & 255), (byte) (var0 / 256)};
    }

    public static byte[] short2ByteArrayHigh(short var0) {
        return new byte[]{(byte) (var0 / 256), (byte) (var0 & 255)};
    }

    public static byte[] short2BcdByteArray(short var0) {
        byte var1 = 2;
        byte[] var2 = new byte[2];

        for (int var3 = 0; var3 < var1; ++var3) {
            byte var4;
            byte var10002 = (byte) ((var4 = (byte) (var0 >> (var1 - var3 - 1) * 8 & 255)) / 10);
            var4 = (byte) (var4 % 10);
            var2[var3] = (byte) ((var10002 << 4) + var4);
        }

        return var2;
    }

    public static int bcdByteArray2Int(byte var0, byte var1) {
        byte var2 = 0;
        if ((var0 & 128) == 128) {
            var0 += 256;
        }
        return 0;
    }
}

