package producerAndConsumer.useSignalAchieve;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 2/14/14
 * Time: 10:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    private static final Object object = new Object();
    private static BoundedBuffer storage = new BoundedBuffer(1);

    public static void main(String[] args) {

        new App().run();

    }

    public void run() {

        ExecutorService service = Executors.newCachedThreadPool();

        for (Integer i = 0; i < 100; i++) {
            Producer producer = new Producer("xxx", 1);
            Consumer consumer = new Consumer("xxx");

            service.submit(producer);
            service.submit(consumer);
        }


    }

    class Producer implements Runnable {

        AtomicInteger atomicInteger = new AtomicInteger(10);
        private String name;
        private Integer putId;


        Producer(String name, Integer putId) {
            this.name = name;
            this.putId = putId;
        }

        public void run() {

            while (true) {

                putId += 1;
                try {
                    storage.put(putId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
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


                try {
                    System.out.println("take " + storage.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

}
