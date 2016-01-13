package jdkCommonMisunderstanding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/27/14
 * Time: 2:55 PM
 */
public class SetExceptionHandler {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread());
        executorService.shutdown();

    }

    static class ExceptionThread implements Runnable {

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            System.out.println("run() by " + thread);
            throw new RuntimeException();
        }
    }

    static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("exceptionHandler caught a exception:" + e);
        }

    }

    static class HandlerThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable runnable) {

            Thread thread = new Thread(runnable);
            thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

            return thread;
        }
    }
}
