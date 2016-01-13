package producerAndConsumer.useSignalAchieve;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 2/15/14
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class BoundedBuffer {

    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition empty = lock.newCondition();
    private Integer capacity = 0;
    private List<Integer> integerList = new ArrayList<Integer>();

    public BoundedBuffer(Integer capacity) {
        this.capacity = capacity;
    }

    public void put(Integer integer) throws InterruptedException {

        try {
            lock.lock();
            while (integerList.size() >= capacity) {
                full.await();
            }
            integerList.add(integer);
            empty.signal();
            System.out.println("insert " + integer);

        } finally {
            lock.unlock();
        }

    }

    public Integer take() throws InterruptedException {

        Integer toBeReturn = null;
        try {
            lock.lock();
            while (integerList.size() < 1) {
                empty.await();
            }
            toBeReturn = integerList.remove(0);
            full.signal();
        } finally {
            lock.unlock();
        }

        return toBeReturn;

    }
}
