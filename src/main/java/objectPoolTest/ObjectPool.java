package objectPoolTest;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-11-24 Time: 下午10:45 To change this template use File | Settings
 * | File Templates.
 */
public class ObjectPool<E extends NoConstructorPojo> {

    private static int ELEMENT_ARRAY_LIST_SIZE = 1000;
    private static List<NoConstructorPojo>[] pool = null;

    static {
        pool = new List[ELEMENT_ARRAY_LIST_SIZE];
        for (Integer i = 0; i < ELEMENT_ARRAY_LIST_SIZE; i++) {
            pool[i] = Lists.newArrayList();
        }
    }

    public static NoConstructorPojo getInstance(Object... args) {

        NoConstructorPojo noConstructorPojo = new NoConstructorPojo(args);
        List<NoConstructorPojo> tmp = pool[noConstructorPojo.hashCode() % ELEMENT_ARRAY_LIST_SIZE];

        int index = Collections.binarySearch(tmp, noConstructorPojo);
        if (index < 0) {
            index = Math.abs(index + 1);
            tmp.add(index, noConstructorPojo);
        }
        return tmp.get(index);
    }

}
