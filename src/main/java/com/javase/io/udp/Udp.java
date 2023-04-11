package com.javase.io.udp;

import com.javase.io.foo.Hex;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.zip.CRC32;

/**
 * @author miclefengzss
 * 2022/8/29 上午11:59
 */
public class Udp {
    private static DatagramSocket mSocket;

    public static void main(String[] args) {

        try {
            // 1.初始化DatagramSocket，指定端口号
            mSocket = new DatagramSocket(30080);
            // F1B0000000060002187ED534C3B41000008A54301319D7DFC2B4
            // 3.接收客户端消息
            HandleThread handleThread = new HandleThread(null, mSocket);
            handleThread.start();
            System.out.println("***************服务器开始监听消息***************");
            while (true) {
                System.out.println("Receive Message: ");
                byte[] receiveData = new byte[4096];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                mSocket.receive(receivePacket);// 在接收到信息之前，一直保持阻塞状态
                System.out.println(receivePacket.getLength());
                System.out.println("客户端(" + receivePacket.getSocketAddress() + "):" + Hex.byte2HexStr(receiveData));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            mSocket.close();// 关闭资源
        }
    }

    static class HandleThread extends Thread {
        private DatagramPacket mPacket;
        private DatagramSocket mSocket;

        public HandleThread(DatagramPacket packet, DatagramSocket socket) {
            super();
            mPacket = packet;
            mSocket = socket;
        }

        @Override
        public void run() {
            try {
//                int[] pre = new int[]{0xF1, 0xB1, 0x00, 0x00, 0x00, 0x00, 0xFF, 0xFE};
//                int curTime = (int) (System.currentTimeMillis() / 1000);
//                byte[] t = intToByteArray(curTime);
//                byte[] a = transformIntToByteArray(pre);
//                byte[] b = unionByteArray(a, t);
//                int[] uuid = new int[]{0xFF, 0xFF, 0xFF, 0x81};
//                byte[] u = transformIntToByteArray(uuid);
//                byte[] c = unionByteArray(b, u);
//                byte[] crc = longToByteArray(encode(c));
//                byte[] sendData = unionByteArray(c, crc);
//                System.out.println("服务端: " + Hex.byte2HexStr(sendData));
//                InetAddress adds = InetAddress.getByName("192.168.255.255");
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, adds, 5918);

                // 空开
                int[] pre = new int[]{0xF1, 0xD3, 0xA1, 0x00, 0x00, 0x1B, 0xFF, 0xE8};
                int curTime = (int) (System.currentTimeMillis() / 1000);
                byte[] t = intToByteArray(curTime);
                byte[] a = transformIntToByteArray(pre);
                byte[] b = unionByteArray(a, t);
                int[] uuid = new int[]{0xFF, 0xFF, 0xFF, 0x82};
                byte[] u = transformIntToByteArray(uuid);
                byte[] c = unionByteArray(b, u);
                int[] nodes = new int[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10, 0x11, 0x12, 0x13};
//                int[] nodes = new int[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x04};
                byte[] d = transformIntToByteArray(nodes);
                byte[] f = unionByteArray(c, d);
                byte[] crc = longToByteArray(encode(f));
                byte[] sendData = unionByteArray(f, crc);
                System.out.println("Send Message: " + Hex.byte2HexStr(sendData));

                // DMX 512
     /*           int[] pre = new int[]{0x00, 0x58, 0x4D, 0x59};
                int[] cmd = new int[]{0x00, 0x00, 0x00, 0x01};
                int[] channel = new int[]{0x00, 0x00, 0x00, 0x01};
                int[] reserve = new int[]{0x00, 0x00, 0x00, 0x00};
                int[] a = unionIntArray(pre, cmd);
                int[] b = unionIntArray(a, channel);
                int[] c = unionIntArray(b, reserve);
                byte[] crc = longToByteArray(encode(transformIntToByteArray(c)));
                byte[] sendData = unionByteArray(transformIntToByteArray(c), crc);

//                System.out.println("Send Message: " + Hex.byte2HexStr(sendData));*/

                // 空开
                InetAddress adds = InetAddress.getByName("192.168.17.13");
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, adds, 5918);


                // DMX 512
//                InetAddress adds = InetAddress.getByName("255.255.255.255");
//                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, adds, 51000);


                System.out.println("socket: " + sendPacket.getSocketAddress() + ", address: " + sendPacket.getAddress() + ", port: " + sendPacket.getPort());
                // 5.向客户端发送消息
                mSocket.send(sendPacket);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mSocket != null) {
//                    mSocket.close();// 关闭资源
                }
            }
        }
    }

    public static long encode(byte[] data) {
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] longToByteArray(long a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    private static byte[] unionByteArray(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    private static int[] unionIntArray(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * int[] 转 byte[]
     *
     * @param iarr
     * @return
     */
    private static byte[] transformIntToByteArray(int[] iarr) {
        byte[] by = new byte[iarr.length];
        for (int i = 0; i < iarr.length; i++) {
            by[i] = (byte) iarr[i];
        }
        return by;
    }
}
