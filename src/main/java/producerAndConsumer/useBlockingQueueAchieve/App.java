package producerAndConsumer.useBlockingQueueAchieve;

import java.io.FileNotFoundException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 2/14/14
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class App {

    public static void main(String[] args) throws FileNotFoundException {

        new App().run();

    }

    public void run() {

        ExecutorService service = Executors.newCachedThreadPool();
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<Integer>(10);
        service.submit(new Consumer(blockingDeque));
        service.submit(new Producer(blockingDeque));
        service.shutdown();

    }

    static class Consumer implements Runnable {

        private BlockingDeque<Integer> blockingDeque;

        Consumer(BlockingDeque<Integer> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        public void run() {

            while (true) {
                try {
                    System.out.println("consumer: " + blockingDeque.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable {

        private BlockingDeque<Integer> blockingDeque;
        private Integer count = 0;

        Producer(BlockingDeque<Integer> blockingDeque) {
            this.blockingDeque = blockingDeque;
        }

        public void run() {
            while (true) {
                try {
                    count++;
                    System.out.println("producer: " + count);
                    blockingDeque.put(count);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
