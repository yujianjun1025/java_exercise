package guva;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/13/14
 * Time: 3:14 PM
 */
public class OrderingTest {


    public static void main(String[] args) {

        List<String> list = Lists.newArrayList();
        list.add("peida");
        list.add("jerry");
        list.add("harry");
        list.add("eva");
        list.add("jhon");
        list.add("neron");

        System.out.println("list:"+ list);

        Ordering<String> naturalOrdering = Ordering.natural();
        Ordering<Object> usingToStringOrdering = Ordering.usingToString();
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();

        Ordering<String> orderingByLength = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return  left.length() - right.length();
            }
        };

        Ordering<String> orderingByLength2 = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return right.length() - left.length() ;
            }
        } ;


        System.out.println("naturalOrdering:"+ naturalOrdering.sortedCopy(list));
        System.out.println("usingToStringOrdering:"+ usingToStringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering:"+ arbitraryOrdering.sortedCopy(list));

        System.out.println(orderingByLength.sortedCopy(list));
        System.out.println(orderingByLength2.compound(orderingByLength).sortedCopy(list));

    }
}
