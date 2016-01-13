package producerAndConsumer.useSynchronizedAchieve;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 2/14/14
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    private static final Object SYNFLAG = new Object();
    public static List<Integer> integerList = new ArrayList<Integer>();

    public static void main(String[] args) {

        App app = new App();
        app.run();

    }

    public void run() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Integer i = 0; i < 10; i++) {
            Producer producer = new Producer("producer" + i);
            Consumer consumer = new Consumer("consumer" + i);
            executorService.submit(producer);
            executorService.submit(consumer);

        }
        executorService.shutdown();
    }

    class Producer implements Runnable {

        private String name;

        Producer(String name) {
            this.name = name;

        }

        public void run() {


            while (true) {

                synchronized (SYNFLAG) {

                    if (integerList.size() < 3) {
                        Integer integer = integerList.size();
                        integerList.add(integer);
                        System.out.println(name + " insert " + integer);
                    }

                }

            }

        }
    }

    class Consumer implements Runnable {

        private String name;

        Consumer(String name) {
            this.name = name;
        }

        public void run() {


            while (true) {

                synchronized (SYNFLAG) {

                    if (integerList.size() > 0) {
                        Integer integer = integerList.remove(0);
                        System.out.println(name + " remove " + integer);
                    }

                }
            }

        }
    }
}
