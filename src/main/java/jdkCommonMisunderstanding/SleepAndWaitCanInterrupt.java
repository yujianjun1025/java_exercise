package jdkCommonMisunderstanding;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/25/14
 * Time: 7:22 PM
 */
public class SleepAndWaitCanInterrupt {

    private static ReentrantLock lock = new ReentrantLock();
    private static final Object object = new Object();

    public static void main(String[] args) {


        sleepTest();
        //waitTest();

    }

    private static void sleepTest() {
        for (Integer i = 0; i < 10; i++) {
             final Integer k = i;

             Thread thread = new Thread(new SleepThread(k));
             thread.start();
         }
    }

    private static void waitTest() {
        for (Integer i = 0; i < 10; i++) {
            final Integer k = i;
            Thread thread = new Thread(new WaitThread(k));
            thread.start();
        }
    }

    static class SleepThread implements Runnable {

        private Integer k = 0;

        SleepThread(Integer k) {
            this.k = k;
        }

        public void run() {
            lock.lock();
            try {
                if (k == 2) {
                    Thread.sleep(10000);
                } else {
                    System.out.println(k);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    static class WaitThread implements Runnable {

        private Integer k;

        WaitThread(Integer k) {
            this.k = k;
        }

        public void run() {

            if (k == 2) {
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(k);


        }
    }

}
