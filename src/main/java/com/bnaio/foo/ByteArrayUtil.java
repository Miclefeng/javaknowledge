package com.bnaio.foo;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Class providing utility methods for handling byte arrays.
 */
public class ByteArrayUtil {
    /**
     * Puts the entire <tt>source</tt> array in the <tt>target</tt> array at
     * offset <tt>offset</tt>.
     */
    public static void put(byte[] source, byte[] target, int offset) {
        System.arraycopy(source, 0, target, offset, source.length);
    }

    /**
     * Gets the subarray from <tt>array</tt> that starts at <tt>offset</tt>.
     */
    public static byte[] get(byte[] array, int offset) {
        return get(array, offset, array.length - offset);
    }

    /**
     * Gets the subarray of length <tt>length</tt> from <tt>array</tt> that
     * starts at <tt>offset</tt>.
     */
    public static byte[] get(byte[] array, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(array, offset, result, 0, length);
        return result;
    }

    public static void putShort(short value, byte[] array, int offset) {
        array[offset] = (byte) (0xff & value);
        array[offset + 1] = (byte) (0xff & (value >> 8));
    }

    public static short getShort(byte[] array, int offset) {
        return (short) (((array[offset + 1] & 0xff) << 8) | (array[offset] & 0xff));
    }

    public static void putInt(int value, byte[] array, int offset) {
        array[offset] = (byte) (0xff & value);
        array[offset + 1] = (byte) (0xff & (value >> 8));
        array[offset + 2] = (byte) (0xff & (value >> 16));
        array[offset + 3] = (byte) (0xff & (value >> 24));
    }

    public static int getInt(byte[] array, int offset) {
        return ((array[offset + 3] & 0xff) << 24)
                | ((array[offset + 2] & 0xff) << 16)
                | ((array[offset + 1] & 0xff) << 8) | (array[offset] & 0xff);
    }

    public static void putLong(long value, byte[] array, int offset) {
        array[offset] = (byte) (0xff & value);
        array[offset + 1] = (byte) (0xff & (value >> 8));
        array[offset + 2] = (byte) (0xff & (value >> 16));
        array[offset + 3] = (byte) (0xff & (value >> 24));
        array[offset + 4] = (byte) (0xff & (value >> 32));
        array[offset + 5] = (byte) (0xff & (value >> 40));
        array[offset + 6] = (byte) (0xff & (value >> 48));
        array[offset + 7] = (byte) (0xff & (value >> 56));
    }

    public static long getLong(byte[] array, int offset) {
        return ((array[offset] & 0xff))
                | ((long) (array[offset + 1] & 0xff) << 8)
                | ((long) (array[offset + 2] & 0xff) << 16)
                | ((long) (array[offset + 3] & 0xff) << 24)
                | ((long) (array[offset + 4] & 0xff) << 32)
                | ((long) (array[offset + 5] & 0xff) << 40)
                | ((long) (array[offset + 6] & 0xff) << 48)
                | ((long) (array[offset + 7] & 0xff) << 56);
    }

    /**
     * Checks whether <tt>value</tt> matches <tt>pattern</tt> with respect to
     * the bits specified by <tt>mask</tt>. In other words: this method returns
     * true if <tt>(value[i] ^ pattern[i]) &amp; mask[i] == 0</tt> for all i.
     */
    public static boolean matchesPattern(byte[] value, byte[] mask,
                                         byte[] pattern) {
        for (int i = 0; i < value.length; i++) {
            if (((value[i] ^ pattern[i]) & mask[i]) != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether <tt>subValue</tt> matches the region in
     * <tt>superValue</tt> starting at offset <tt>offset</tt>.
     */
    public static boolean regionMatches(byte[] subValue, byte[] superValue,
                                        int offset) {
        for (int i = 0; i < subValue.length; i++) {
            if (subValue[i] != superValue[i + offset]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Compares two regions of bytes, indicating whether one is larger than the
     * other.
     *
     * @param array1
     *            The first byte array.
     * @param startIdx1
     *            The start of the region in the first array.
     * @param array2
     *            The second byte array.
     * @param startIdx2
     *            The start of the region in the second array.
     * @param length
     *            The length of the region that should be compared.
     * @return A negative number when the first region is smaller than the
     *         second, a positive number when the first region is larger than
     *         the second, or 0 if the regions are equal.
     */
    public static int compareRegion(byte[] array1, int startIdx1,
                                    byte[] array2, int startIdx2, int length) {
        int result = 0;
        for (int i = 0; result == 0 && i < length; i++) {
            result = (array1[startIdx1 + i] & 0xff)
                    - (array2[startIdx2 + i] & 0xff);
        }
        return result;
    }

    /**
     * Returns the hexadecimal value of the supplied byte array. The resulting
     * string always uses two hexadecimals per byte. As a result, the length of
     * the resulting string is guaranteed to be twice the length of the supplied
     * byte array.
     */
    public static String toHexString(byte[] array) {
        StringBuilder sb = new StringBuilder(2 * array.length);

        for (int i = 0; i < array.length; i++) {
            String hex = Integer.toHexString(array[i] & 0xff);

            if (hex.length() == 1) {
                sb.append('0');
            }

            sb.append(hex);
        }

        return sb.toString();
    }

    public static int shortToUnsigned(short s) {
        return s & 0x0FFFF;
    }

    public static int indexOf(byte[] array, byte[] target) {
        if (target.length == 0) {
            return 0;
        }

        outer:
        for (int i = 0; i < array.length - target.length + 1; i++) {
            for (int j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                    continue outer;
                }
            }
            return i;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] array, byte target) {
        return lastIndexOf(array, target, 0, array.length);
    }

    private static int lastIndexOf(byte[] array, byte target, int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static byte[] exchangeObjectToBytes(Object obj) {
        try(
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream sOut = new ObjectOutputStream(out)
        ){
            sOut.writeObject(obj);
            sOut.flush();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
