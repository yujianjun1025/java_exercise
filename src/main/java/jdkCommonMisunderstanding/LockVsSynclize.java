package jdkCommonMisunderstanding;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by root on 15-6-14.
 */
public class LockVsSynclize {



    public static void main(String[] args) {


        ExecutorService service = Executors.newCachedThreadPool();

        final ReentrantLock lock = new ReentrantLock();

        lock.lock();
        service.submit(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    System.out.println("hello world");
                } finally {
                    lock.unlock();
                }

            }
        });

        
        System.out.println("is over");

    }
}
