package jdkCommonMisunderstanding;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/27/14
 * Time: 3:47 PM
 */
public class InterruptThread {


    public static void isInterruptTest() {

        class NormalInterrupt extends Thread {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    System.out.println("thread running");
                }

                System.out.println("thread interrupted");
            }
        }
        Thread thread = new Thread(new NormalInterrupt());
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public static void currentThreadInterruptTest() {

        class NormalCurrentThreadInterrupt extends Thread {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("running()");
                }
            }
        }
        Thread thread = new Thread(new NormalCurrentThreadInterrupt());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public static void sleepBlockSleep() {

        class SleppBlock extends Thread {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("running()");
                        //throw new InterruptedException();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        Thread thread = new Thread(new SleppBlock());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public static void waitBlockTest() {

        final  Object object = new Object();
        class WaitBlock extends Thread {


            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    try {
                        System.out.println("begin wait");
                        synchronized (object){
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        Thread thread = new Thread(new WaitBlock());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    public static void synchronizedBlockTest() {

        class SynchronizedBlock extends Thread {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    synchronized (this) {

                        double tmp = 0.0;
                        for (Integer i = 0; i < 1000; i++) {
                            for (Integer j = 0; j < 1000; j++) {
                                for (Integer k = 0; k < 10000; k++) {
                                    tmp = (9.9 * i * 9.9 * j * k) / (1.1 * i * i);
                                    //tmp = i*j;
                                }
                            }
                        }
                        System.out.println("tmp:" + tmp);
                    }
                }
            }
        }

        Thread thread = new Thread(new SynchronizedBlock());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }

    public static void lockBlockTest() {
        class LockBlock extends Thread {
            private ReentrantLock lock;

            LockBlock(ReentrantLock lock) {
                this.lock = lock;
            }

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {

                    double tmp = 0.0;
                    for (Integer i = 0; i < 1000; i++) {
                        for (Integer j = 0; j < 1000; j++) {
                            for (Integer k = 0; k < 10000; k++) {
                                tmp = (9.9 * i * 9.9 * j * k) / (1.1 * i * i);
                                //tmp = i*j;
                            }
                        }
                    }
                    System.out.println("tmp:" + tmp);
                }
            }
        }

        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread(new LockBlock(lock));
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();

    }

    public static void main(String[] args) {
        //isInterruptTest();
        //  currentThreadInterruptTest();

        //sleepBlockSleep();
        // waitBlockTest();

        //synchronizedBlockTest();
        lockBlockTest();
    }
}
