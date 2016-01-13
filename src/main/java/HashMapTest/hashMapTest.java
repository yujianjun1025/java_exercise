package HashMapTest;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-8-11 Time: 下午11:27 To change this template use File | Settings
 * | File Templates.
 */
public class hashMapTest {

    public static void noInitVolumeTest(Integer count) {

        long begin = System.currentTimeMillis();
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (Integer i = 0; i < count; i++) {
            hashMap.put(i, i);
        }

        long end = System.currentTimeMillis();

        System.out.println("noInitVolumeTest count:" + count + "耗时：" + String.valueOf(end - begin));

    }

    public static void initVolumeTest(Integer count) {

        long begin = System.currentTimeMillis();
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>((int) (count * 1.2), (float) 0.95);
        for (Integer i = 0; i < count; i++) {
            hashMap.put(i, i);
        }

        long end = System.currentTimeMillis();

        System.out.println("initVolumeTest count:" + count + "耗时：" + String.valueOf(end - begin));

    }

    public static Integer getNearTwoPower(Integer count) {
        Integer result = 1;
        while (result < count) {
            result *= 2;
        }
        return result;
    }

    public static void initVolumeTest2(Integer count) {

        long begin = System.currentTimeMillis();
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>(count + 100, (float) 1.0);
        for (Integer i = 0; i < count; i++) {
            hashMap.put(i, i);
        }

        long end = System.currentTimeMillis();

        System.out.println("initVolumeTest2 count:" + count + "耗时：" + String.valueOf(end - begin));

    }

    public static void initVolumeTest3(Integer count) {

        long begin = System.currentTimeMillis();
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>(getNearTwoPower(count), (float) 1.0);
        for (Integer i = 0; i < count; i++) {
            hashMap.put(i, i);
        }

        long end = System.currentTimeMillis();

        System.out.println("initVolumeTest3 count:" + count + "耗时：" + String.valueOf(end - begin));

    }

    public static void main(String[] args) {

        Integer count = 10;
        for (Integer i = 0; i < 8; i++) {

            noInitVolumeTest(count);
            // initVolumeTest(count);
            // initVolumeTest2(count);
            initVolumeTest3(count);

            count *= 10;
        }

    }
}
