package com.bnaio.socket;

import com.bnaio.foo.Hex;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author miclefengzss
 * 2021/9/25 下午9:21
 */
public class Client {

    public static final String HOST = "172.16.30.31";

    public static final int PORT = 60001;

    public static final String QUIT = "quit";

    public static Socket socket;

    public static void main(String[] args) {
        OutputStream writer = null;
        try {
            socket = new Socket(HOST, PORT);

            writer = socket.getOutputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

//            short[] brightH = new short[]{0xF5, 0x02, 0xFF, 0xD3, 0x14, 0x00, 0x01, 0xFF};
//            short[] brightL = new short[]{0xF5, 0x02, 0xFF, 0xD3, 0x14, 0x00, 0xF0, 0xFF};
//            short[] colorTh = new short[]{0xF5, 0x03, 0xFF, 0x84, 0x19, 0x00, 0x64, 0xFF};
//            short[] colorTl = new short[]{0xF5, 0x03, 0xFF, 0x84, 0x0A, 0x00, 0x8C, 0xFF};
            byte[] command = new byte[8];
            System.out.println("请输入:");
            while (true) {
                final String input = consoleReader.readLine();
                if (input.isEmpty()) {
                    continue;
                }
                short in = Short.parseShort(input);
                System.err.println("input: " + in);
                short area = 0;
                if (in > 8) {
                    area = (short) (in - 8);
                } else {
                    area = in;
                }
                for (int i = 0; i < 8; i++) {
                    command = buildCommand(1, area, i);
                    System.err.println(Hex.byte2HexStr(command));
                    writer.write(command);
                    writer.flush();
                    Thread.sleep(200);
                }
                if (in > 8) {
                    Thread.sleep(5000);
                    for (int i = 0; i < 8; i++) {
                        command = buildCommand(2, area, i);
                        System.err.println(Hex.byte2HexStr(command));
                        Thread.sleep(200);
                        writer.write(command);
                        writer.flush();
                    }
                    System.err.println(Hex.byte2HexStr(command));
                    writer.write(command);
                    writer.flush();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] buildCommand(int action, short area, int channel) {
        System.err.println("area: " + area + ",channel: " + channel);
        byte[] command;
//        F5 03 FF 84 19 00 64 08 // 灯带-高色温
//        F5 03 FF 84 0A 00 8C EF // 灯带-低色温
        short[] openCommand = new short[]{0xF5, 0x03, 0xFF, 0xD3, 0x0A, 0x00, 0x01, 0xFF};
        short[] closeCommand = new short[]{0xF5, 0x03, 0xFF, 0xD3, 0x0A, 0x00, 0xFF, 0xFF};
        short[] colorTh = new short[]{0xF5, 0x03, 0xFF, 0x84, 0x19, 0x00, 0x64, 0xFF};
        short[] colorTl = new short[]{0xF5, 0x03, 0xFF, 0x84, 0x0A, 0x00, 0x8C, 0xFF};
        if (action == 1) {
            System.err.println("open :");
            openCommand[1] = area;
            openCommand[5] = (short) channel;
            command = shortsToBytes(addCrc16(openCommand));
        } else if (action == 2){
            System.err.println("close :");
            closeCommand[1] = area;
            closeCommand[5] = (short) channel;
            command = shortsToBytes(addCrc16(closeCommand));
        } else if (action == 3) {
            System.err.println("height :");
            colorTh[1] = area;
            colorTh[5] = (short) channel;
            command = shortsToBytes(addCrc16(colorTh));
        } else {
            System.err.println("low :");
            colorTl[1] = area;
            colorTl[5] = (short) channel;
            command = shortsToBytes(addCrc16(colorTl));
        }
        return command;
    }

    public static short[] addCrc16(short[] data) {
        int sum = 0;
        for (int i = 0; i < (data.length - 1); ++i) {
            sum += data[i];
        }
        // modulo 256 sum
        sum %= 256;
        byte ch = (byte) sum;
        // twos complement
        byte twoscompl = (byte) (~ch + 1);
        data[data.length - 1] = twoscompl;
        return data;
    }

    public static byte[] shortsToBytes(short[] arr) {
        byte[] command = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            command[i] = (byte) arr[i];
        }
        return command;
    }
}
