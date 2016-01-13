package objectPoolTest;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-11-24 Time: 下午10:48 To change this template use File | Settings
 * | File Templates.
 */
public class NoConstructorPojo implements Comparable<NoConstructorPojo> {

    public NoConstructorPojo(Object... agrs) {
    }

    public int compareTo(NoConstructorPojo o) {
        return 0;
    }
}
