package com.javase.base;


import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.zip.CRC32;

public class StringTest {

    private static final char[] HEXCHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public static void main(String[] args) {


//
//        // + 的操作优先级高于 ==
//        // "xxx" + pid == dog  => 用()来改写   ("xxx" + pid) == dog
//        System.out.println("Animals are equal: " + pig == dog1);//6
        String str = "9d4f2eabd846478d4f735c33f0a96c363c22fbbfa9ad";
        System.out.println(str.substring(str.length() - 12));
        System.out.println((int) (System.currentTimeMillis() / 1000));

        String s = "teSt138HellWorld";
        System.out.println(encodeHexString(s.getBytes()));


        byte[] array = new byte[2];
        short value = (short) 0x8000;
        array[0] = (byte) (0xff & value);
        array[1] = (byte) (0xff & (value >> 8));

        short dst = (short) (((array[1] & 0xff) << 8) | (array[0] & 0xff));
        System.out.println(value);
        System.out.println(dst);

        System.out.println("==============================");

        String s1 = "abc";
        System.out.println(String.join(".", s1.split("")));
        System.out.println("==============================");

        byte[] message = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
        CRC32 crc = new CRC32();
        crc.update(message, 0, message.length);
        int myCrc = (int) crc.getValue();
        System.out.println(myCrc);
        System.out.println(myCrc & 5);
        System.out.println("===============================");
        String d = "data={\"sensor\":\"BME680\",\"timestamp\":1604392440.0427563,\"uid\":\"b8:27:eb:5e:69:72\",\"sid\":\"BME680_1\",\"data\":{\"T\":21.39,\"P\":1024.32,\"H\":38.30}}";
        System.out.println(d.replaceFirst("data=", ""));

        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        System.err.println(todayStart.getTime().getTime() / 1000);
        //当天零点
        long l = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toEpochSecond(OffsetDateTime.now().getOffset());
        long l1 = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).plusDays(1).toEpochSecond(OffsetDateTime.now().getOffset());

        System.err.println(l);
        System.err.println(l1);
        System.out.println("===============================");
        str = "visit_id=1&";
        System.out.println(str.substring(0, str.length() - 1));
        System.out.println("===============================");
        System.out.println(timeStamp2Date(getCurrentTime()+"", "yyyy年MM月dd日HH:mm")+"-"+timeStamp2Date((getCurrentTime()+3600)+"", "HH:mm"));
        System.out.println("===============================");
        System.out.println(Arrays.asList("a"));

        String hexS = "FEFE";
        HexStringToByteArray(hexS);
    }

    public static byte[] HexStringToByteArray(String s) {
        byte data[] = new byte[s.length()/2];
        for(int i=0;i < s.length();i+=2) {
            data[i/2] = (Integer.decode("0x"+s.charAt(i)+s.charAt(i+1))).byteValue();
        }
        return data;
    }

    /**
     * Encode the bytes with hex string (lower case)
     *
     * @param data
     * @return
     */
    public static String encodeHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(HEXCHARS[(data[i] & 0xf0) >>> 4]);
            sb.append(HEXCHARS[data[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(seconds + "000")));
    }
}

