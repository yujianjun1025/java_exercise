package guva;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import org.apache.commons.collections.comparators.ComparatorChain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-12-4 Time: 下午10:10 To change this template use File | Settings
 * | File Templates.
 */
public class OrdingTest {

    static class A {

        Integer n1;
        Integer n2;

        A(Integer n1, Integer n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        @Override
        public String toString() {
            return "A{" + "n1=" + n1 + ", n2=" + n2 + '}';
        }
    }

    static class C2 implements Comparator<A> {

        public int compare(A o1, A o2) {
            return Integer.compare(o1.n2, o2.n2);
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    static class C1 implements Comparator<A> {

        public int compare(A o1, A o2) {
            return Integer.compare(o1.n1, o2.n1);
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    private static List<A> getList() {

        List<Integer> data = Lists.newArrayList(3, 1, 3, 1, 3, 2, 4, 2, 2, 2, 2, 4, 4, 2, 3, 1, 3, 4, 1, 2);
        List<A> list = Lists.newArrayList();
        for (Integer i = 0; i < data.size(); i += 2) {
            list.add(new A(data.get(i), data.get(i + 1)));
        }

        return list;
    }

    private static Ordering<A> getOrdering() {

        return new Ordering<A>() {
            @Override
            public int compare(guva.OrdingTest.A left, guva.OrdingTest.A right) {

                return Integer.compare(left.n1, right.n1);
            }
        };

    }

    private static void firstMethod() {

        List<A> aList = getList();
        Ordering<A> ordering = getOrdering();
        ordering.compound(new C2());

        System.out.println("排序前:" + Joiner.on(",").join(aList));
        List<A> result = ordering.sortedCopy(aList);
        System.out.println("排序后:" + Joiner.on(",").join(result));
    }


    private static void secondMethod() {

        List<A> aList = getList();
        Ordering<A> ordering = getOrdering();

        ComparatorChain multiSort = new ComparatorChain();
        multiSort.addComparator(new C1());
        multiSort.addComparator(new C2());

        System.out.println("排序前:" + Joiner.on(",").join(aList));
        Collections.sort(aList, multiSort);
        System.out.println("排序后:" + Joiner.on(",").join(aList));

    }

    public static void main(String[] args) {

        firstMethod();
        System.out.println("------------------------");
        secondMethod();

    }
}
