package com.socket.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author miclefengzss
 * 2021/9/26 下午2:36
 */

interface FileCopyRunner {

    void copyFile(File source, File target);
}

public class FileCopyDemo {

    private static final int ROUNDS = 5;

    private static void benchmark(FileCopyRunner test, File source, File target) {
        long elapsed = 0L;
        for (int i = 0; i < ROUNDS; i++) {
            long startTime = System.currentTimeMillis();
            test.copyFile(source, target);
            elapsed += System.currentTimeMillis() - startTime;
            target.delete();
        }
        System.out.println(test + " used time: " + elapsed / ROUNDS + " ms.");
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        FileCopyRunner noBufferStreamCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                FileInputStream fis = null;
                FileOutputStream fos = null;

                try {
                    fis = new FileInputStream(source);
                    fos = new FileOutputStream(target);

                    int result;
                    while ((result = fis.read()) != -1) {
                        fos.write(result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(fis);
                    close(fos);
                }
            }

            @Override
            public String toString() {
                return "noBufferStreamCopy";
            }
        };

        FileCopyRunner bufferedStreamCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;

                try {
                    // buffered io jvm 默认 8KB 的字节数组缓存
                    bis = new BufferedInputStream(new FileInputStream(source));
                    bos = new BufferedOutputStream(new FileOutputStream(target));

                    byte[] buffer = new byte[1024];
                    int result;
                    while ((result = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(bis);
                    close(bos);
                }
            }

            @Override
            public String toString() {
                return "bufferedStreamCopy";
            }
        };

        FileCopyRunner nioBufferCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {

                FileChannel fic = null;
                FileChannel foc = null;

                try {
                    fic = new FileInputStream(source).getChannel();
                    foc = new FileOutputStream(target).getChannel();

                    ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
                    // 从文件流channel读取数据写入buffer中
                    while (fic.read(buffer) != -1) {
                        // flip() 将buffer转为读取模式
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            // 从buffer中读取数据写入到文件流channel中
                            foc.write(buffer);
                        }
                        // clear() 将buffer转为写入模式
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(fic);
                    close(foc);
                }
            }

            @Override
            public String toString() {
                return "nioBufferCopy";
            }
        };

        FileCopyRunner nioTransferCopy = new FileCopyRunner() {
            @Override
            public void copyFile(File source, File target) {
                FileChannel fic = null;
                FileChannel foc = null;

                try {
                    fic = new FileInputStream(source).getChannel();
                    foc = new FileOutputStream(target).getChannel();

                    long transferred = 0L;
                    long size = fic.size();
                    while (transferred != size) {
                        transferred = fic.transferTo(0, size, foc);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    close(fic);
                    close(foc);
                }
            }

            @Override
            public String toString() {
                return "nioTransferCopy";
            }
        };

        File sourceFile = new File("src/main/resources/file/a-tale-of-two-cities.txt");
        File targetFile = new File("src/main/resources/file/a-tale-of-two-cities-copy.txt");
//        benchmark(noBufferStreamCopy, sourceFile, targetFile);
        benchmark(bufferedStreamCopy, sourceFile, targetFile);
        benchmark(nioBufferCopy, sourceFile, targetFile);
//        benchmark(nioTransferCopy, sourceFile, targetFile);
    }
}
