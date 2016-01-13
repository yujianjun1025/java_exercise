package jdkCommonMisunderstanding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/27/
 * Time: 2:45 PM
 */
public class MainThreadCantCatchThreadException {


    public static void noTryCatch() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Integer i = 0; i < 100; i++) {
            executorService.execute(new ExceptionThread(i));
        }

        executorService.shutdown();
    }

    public static void outsideTryCatch() {

        try {

            ExecutorService executorService = Executors.newCachedThreadPool();

            for (Integer i = 0; i < 10; i++) {
                executorService.execute(new ExceptionThread(i));
            }

            executorService.shutdown();

        } catch (Exception e) {
            System.out.println("had try catch");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        //noTryCatch();
        outsideTryCatch();

    }

    static class ExceptionThread implements Runnable {

        private Integer value;

        ExceptionThread(Integer value) {
            this.value = value;
        }

        @Override
        public void run() {
            if (2 == value) {
                throw new RuntimeException();
            } else {
                System.out.println(value);
            }
        }
    }
}
