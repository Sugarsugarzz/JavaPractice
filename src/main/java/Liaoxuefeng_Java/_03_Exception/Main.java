package Liaoxuefeng_Java._03_Exception;

import net.sf.ehcache.transaction.xa.EhcacheXAException;
import sun.rmi.runtime.Log;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        /*
        程序运行过程中，总是会出现各种各样的错误，一个健壮的程序必须能够处理各种错误。
        调用方获知调用失败信息的两种方法：
        - 约定返回错误码
        - 异常处理机制

        Throwable是异常体系的根，有两个体系：
        - Error：表示严重错误，程序无能为力
        - Exception：运行错误，可以捕获并处理
            - RuntimeException
            - 非RuntimeException（IOException等）

        Java规定：
        - 必须捕获的异常：Exception及其子类，不包括RuntimeException及其子类
        - 不需要捕获的异常：Error及其子类，RuntimeException及其子类
         */
//        byte[] bs = toGBK("中文");
//        System.out.println(Arrays.toString(bs));

        /*
        方法定义的时候，throws xxx 表示该方法可能抛出的异常类型，调用方必须强制捕获这些异常。
        当然在非调用层，也可以不捕获它，在方法定义处继续用throws抛出异常即可。
        但在main()方法中，所有未捕获的异常必须捕获。
         */

        /*
        多catch语句：
            - 可使用多个catch语句，每个catch捕获对应的Exception及其子类。
            - 从上到下匹配catch语句，匹配到后执行catch代码块，不再继续匹配。
            - 多个catch语句只有一个能被执行。
            - catch的顺序非常重要，子类必须写在前面。
         */

        /*
        finally语句：
            - 保证有无错误都会执行。
            特点：
                - finally不是必须的，可写可不写。
                - finally总是最后执行。
         */
        /*
        在try或者catch语句块中抛出异常，finally语句依然会执行。
         */
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            System.out.println("catched");
//            throw new RuntimeException(e);
//        } finally {
//            System.out.println("finally");
//        }
        /*
        finally语句块中抛出的异常，会覆盖try或catch中抛出的异常。
         */
        /*
        NullPointerException：
            - 严禁使用catch来隐藏这种编码错误。
            - 在成员变量定义初始化时，使用空字符串 "" 而不是默认的 null，可以避免很多NullPointerException，
              编写业务逻辑时，用空字符串 "" 表示未填写比 null 安全得多。
            - Java14中JVM可以给出详细信息null对象到底是谁，需在JVM开启参数。
         */

        /*
        断言：调试程序的一种方式。使用 assert 关键字来实现。
            - 一般情况下，断言失败并不会抛出 AssertionError，因为未开启。
            - 给JVM传递 -enableassertions 参数启用断言。
            - 实际开发中，很少使用断言。
            - 更好的方法是编写单元测试，JUnit。
         */
        int x = -1;
        assert x > 0 : "x must >= 0";
        System.out.println(x);

        /*
        JDK Logging
            - JVM启动时读取配置文件并完成初始化，一旦运行main()方法，就无法修改配置。
            - 内置的Logging使用并不广泛。
         */
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated");

        /*
        Commons Logging:
            - Apache的日志模块
            - 需要commons-logging.jar包
         */

        /*
        Log4j2
         */

        /*
        SLF4J和Logback
         */

    }

    static byte[] toGBK(String s) {
        try {
            return s.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            /*
            所有异常可以用 e.printStackTrace() 方法打印异常栈。
             */
            e.printStackTrace();
            return s.getBytes();
        }
    }
}
