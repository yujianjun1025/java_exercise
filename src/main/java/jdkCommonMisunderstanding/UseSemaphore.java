package jdkCommonMisunderstanding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/25/14
 * Time: 11:50 AM
 */
public class UseSemaphore {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(2);
        for(Integer i = 0; i < 20; i++){
            final Integer k = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(k);
                        Thread.sleep(1000);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            };
            executorService.execute(runnable);
        }

        executorService.shutdown();

    }
}
