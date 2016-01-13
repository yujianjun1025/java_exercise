package aboutMemeryTest;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/12/14
 * Time: 10:40 AM
 */
public class WithNoLockCacheTest {

    private static boolean flag = true;
    private volatile HashMap<Integer, Integer> cache1 = null;

    public WithNoLockCacheTest() {
        cache1 = Maps.newHashMap();
        for (int i = 0; i < 100000; i++) {
            cache1.put(i, i);
        }
    }

    public static void main(String[] args) {

        WithNoLockCacheTest withNoLockCache = new WithNoLockCacheTest();

        List<Future<Integer>> futureList = Lists.newArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        futureList.add(executorService.submit(new Read(withNoLockCache)));
        futureList.add(executorService.submit(new Write(withNoLockCache)));

        for (Future<Integer> future : futureList) {
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

        HashMap<Integer, Integer> tmp = cache1;
        Integer value = tmp.get(key);
        for (Integer i = 0; i < 10000; i++) {
            if (value != tmp.get(key)) {
                System.out.println(value + " " + key);
                System.exit(1);
            }
        }


        return tmp.get(key);

        /*Integer value = cache1.get(key);
        for (Integer i = 0; i < 1000; i++) {
            if (!value.equals(cache1.get(key))) {
                System.out.println(value + " " + key);
                System.out.println("value != key" + "value  == " + value + "key == " + key);
                System.exit(1);
            }
        }*/

        // return cache1.get(key);

    }

    public void updateCache1() {

        HashMap<Integer, Integer> cache2 = Maps.newHashMap();

        for (Integer i = 0; i < 100000; i++) {
            if (flag) {
                cache2.put(i, i);
            } else {
                cache2.put(i, 2 * i);
            }

        }
        flag = !flag;
        System.out.println("begin update date");
        cache1 = cache2;

    }

    static class Read implements Callable<Integer> {

        private WithNoLockCacheTest withNoLockCache;

        Read(WithNoLockCacheTest withNoLockCache) {
            this.withNoLockCache = withNoLockCache;
        }

        public Integer call() throws Exception {

            boolean flag = true;
            Integer value = null;
            while (flag) {

                for (Integer i = 0; i < 100000; i++) {
                    value = withNoLockCache.getEntryCacheByKey(i);
                    if (value.equals(i) || value.equals(2 * i)) {
                        System.out.println(i + " " + value);
                    } else {
                        System.out.println(i + " " + value);
                        flag = false;
                    }
                }
            }
            return value;
        }
    }

    static class Write implements Callable<Integer> {

        private WithNoLockCacheTest withNoLockCache;

        Write(WithNoLockCacheTest withNoLockCache) {
            this.withNoLockCache = withNoLockCache;
        }

        public Integer call() throws Exception {
            while (true) {
                withNoLockCache.updateCache1();
            }
        }
    }

}
