package listTest;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-12-1 Time: 下午8:31 To change this template use File | Settings |
 * File Templates.
 */
public class Main {

    public static void testRemoveByFor() {

        List<Integer> removeByForlist = Lists.newArrayList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        System.out.println(ToStringBuilder.reflectionToString(Joiner.on("").join(removeByForlist)));
        for (Integer i = 0; i < removeByForlist.size(); i++) {
            Integer j = removeByForlist.get(i);
            if (j.equals(1)) {
                removeByForlist.remove(j);
            }
        }

        System.out.println(ToStringBuilder.reflectionToString(Joiner.on("").join(removeByForlist)));

    }

    public static void testRemoveByIterator() {

        List<Integer> removeByIterator = Lists.newArrayList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        System.out.println(ToStringBuilder.reflectionToString(Joiner.on("").join(removeByIterator)));

        Iterator<Integer> iterator = removeByIterator.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(1)) {
                iterator.remove();
            }
        }

        System.out.println(ToStringBuilder.reflectionToString(Joiner.on("").join(removeByIterator)));

    }

    public static void testRemoveByForeach() {

        List<Integer> removeByForeach = Lists.newArrayList(1, 1, 1);
       // System.out.println(ToStringBuilder.reflectionToString(Joiner.on("").join(removeByForeach)));
        for (Integer i : removeByForeach) {
            removeByForeach.remove(i);

        }

        System.out.println(ToStringBuilder.reflectionToString(Joiner.on("").join(removeByForeach)));

    }

    public static void main(String[] args) {

      //  testRemoveByForeach();

        List<Integer> integers = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

        final  Integer x = 5;
        System.out.println(Joiner.on(",").join(Collections2.filter(integers, new Predicate<Integer>() {
            public boolean apply(Integer input) {
                return input != x;
            }
        })));


        //testRemoveByFor();
    }
}
