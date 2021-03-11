package com.baseknowledge.exception;

/**
 * @author miclefengzss
 * @create 2021-03-10 下午4:52
 */

public class Exceptions {

    /**
     * java 中通过 Throwable 对错误和异常进行处理
     *
     * Error: 是程序无法处理的错误，表示应用程序中比较严重的问题。
     * Exception: 程序本身可以处理的异常。
     *                                Throwable (包含)
     *             Error                   |                                      Exception
     *  1.VirtualMachineError(虚拟机错误)   |   Unchecked Exception(RuntimeException)    |  Checked Exception
     *  2.OutOfMemoryError(内存溢出错误)    |   编译器不强制处理的异常                      |  编译器要求必须处理
     *  3.ThreadDeath(线程死锁)             |   1.NullPointerException(空指针异常)        |  1.IOException
     *                                     |   2.ArrayIndexOutOfBoundsException(数组越界)|  2.SQLException
     *                                     |   3.ArithmeticException(算术异常)           |
     *                                     |   4.ClassCastException(类型转换异常)         |
     *
     * java规定：对于可查异常必须捕获或者声明抛出。系统允许忽略 RuntimeException和UncheckedException
     *
     *  异常处理机制：
     *  1、抛出异常
     *  2、捕获异常
     */
}

class UserDefinedException extends Exception {

    public UserDefinedException() {
        super("自定义异常...");
    }
}

/**
 * 异常链：将底层的异常信息传递给上层，逐层抛出
 */
class ExceptionLink {

    public static void main(String[] args) {
        try {
            testThree();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testOne() throws UserDefinedException {
        throw new UserDefinedException();
    }

    public static void testTwo() throws Exception {
        try {
            testOne();
        } catch (Exception e) {
            throw new Exception("新产生的异常1...", e);
        }
    }

    public static void testThree() throws Exception {
        try {
            testTwo();
        } catch (Exception e) {
            Exception exception = new Exception("新产生的异常2...");
            exception.initCause(e);
            throw exception;
        }
    }
}
