package aboutMemeryTest;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/9/14
 * Time: 4:02 PM
 */
public class CacheTest {


    private HashMap<Integer, Integer> cache1 = null;
    private HashMap<Integer, Integer> cache2 = null;

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public CacheTest() {
        cache1 = Maps.newHashMap();
        for (int i = 0; i < 100000; i++) {
            cache1.put(i, i);
        }
    }

    public static void main(String[] args) {

        CacheTest cacheTest = new CacheTest();

        List<Future<Integer> > futureList = Lists.newArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(Integer i = 0; i < 3; i++){
            futureList.add(executorService.submit(new Read(cacheTest)));
        }
        futureList.add(executorService.submit(new Write(cacheTest)));

        for(Future<Integer> future : futureList){
            try {

                System.out.println(future.get());
                System.exit(0);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


    public Integer getEntryCacheByKey(Integer key) {
        try{

            reentrantReadWriteLock.readLock().lock();
            return cache1.get(key);

        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void updateCache1()  {

        cache2 = Maps.newHashMap();
        for (Integer i = 0; i < 100000; i++) {
            cache2.put(i, i);
        }

        try{

            reentrantReadWriteLock.writeLock().lock();
            System.out.println("begin update date");
            cache1.clear();
            cache1 = cache2;
        }finally {
            reentrantReadWriteLock.writeLock().unlock();

        }

    }

    static class Read implements Callable<Integer> {

        private CacheTest cacheTest;

        Read(CacheTest cacheTest) {
            this.cacheTest = cacheTest;
        }

        public Integer call() throws Exception {

            boolean flag = true;
            Integer value = null;
            while (flag) {

                for (Integer i = 0; i < 100000; i++) {
                    value = cacheTest.getEntryCacheByKey(i);
                    if (!value.equals(i) ) {
                        System.out.println(i +" " +value);
                        flag = false;
                    }else {
                        System.out.println(i +" " +value);
                    }

                }
            }
            return value;
        }
    }

    static class Write implements Callable<Integer> {

        private CacheTest cacheTest;

        Write(CacheTest cacheTest) {
            this.cacheTest = cacheTest;
        }

        public Integer call() throws Exception {
            while (true) {
                cacheTest.updateCache1();
            }
        }
    }



    public void unGetLockButUnLock(){
        while(true){
            reentrantReadWriteLock.readLock().unlock();
        }
    }


}
