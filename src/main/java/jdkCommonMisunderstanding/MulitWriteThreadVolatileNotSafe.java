package jdkCommonMisunderstanding;

import java.util.List;
import java.util.concurrent.*;

import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/28/14
 * Time: 1:53 PM
 */
public class MulitWriteThreadVolatileNotSafe {

    private static volatile long value = 0;

    public static void main(String[] args) {

        List<Future<Long>> futureList = Lists.newArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        for (Integer i = 0; i < 100; i++) {
            futureList.add(executorService.submit(new Write()));
        }

        List<Long> longList = Lists.newArrayList();
        for(Future<Long> future : futureList){
            try {
                longList.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        displayResult(longList);

        executorService.shutdown();

    }

    private static void displayResult(List<Long> longList) {

        for(Long tmp : longList){
            System.out.println(tmp);
        }
    }

    static class Write implements Callable<Long> {

        @Override
        public Long call() throws Exception {
            for (int i = 0; i < 100; i++) {
                value++;
            }
            return value;
        }
    }


}
