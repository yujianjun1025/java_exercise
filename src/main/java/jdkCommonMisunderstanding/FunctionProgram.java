package jdkCommonMisunderstanding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 3:10 PM
 */
public class FunctionProgram {

    public static void main(String[] args) {


        threadLocalTest();
       // guavaTransformTest();
    }

    public static void guavaTransformTest() {

        List<Integer> plain = Lists.newArrayList(1, 2, 3, 4, 5);

        List<Integer> transformResult = Lists.transform(plain,
                new Function<Integer, Integer>() {
                    public Integer apply(Integer input) {
                        System.out.println("input" + input);
                        return 2 * input;
                    }
                });

        for (Integer integer : transformResult) {
            System.out.println("transform " + integer);
        }

        System.out.println("over");

    }

    public static void threadLocalTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runner runner = new Runner(0);
        for (Integer i = 0; i < 30; i++) {

            runner.threadLocalInteger.set(10);
            executorService.submit(runner);
        }

        executorService.shutdown();
    }

    static class Runner implements Runnable {

        private ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>() {
            @Override
            public Integer initialValue() {
                return new Integer(0);
            }
        };
        private ThreadLocal<String> threadLocalStr = new ThreadLocal<String>() {

            @Override
            public String initialValue() {
                System.out.println("initialValue");
                return Thread.currentThread().toString();
            }
        };

        private int i ;

        public Runner(int i) {
            this.i = i;
        }

        public void run() {

            System.out.println(ToStringBuilder.reflectionToString(this));
            System.out.println(threadLocalInteger.get() + ": " + threadLocalStr.get());
            threadLocalInteger.set(i);

        }
    }


}
