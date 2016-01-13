package guva;

import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/13/14
 * Time: 5:42 PM
 */
public class Collections2Tes {

    public static void main(String[] args) {

        List<Integer> list = Lists.newArrayList();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Collection<Integer> filter = Collections2.filter(list, new Predicate<Integer>() {
                    public boolean apply(Integer input) {
                        return input > 5;
                    }

                    public boolean equals(Object object) {
                        return false;
                    }
                }
        );

        System.out.println(filter);


        Collection<Integer> transform = Collections2.transform(list, new Function<Integer, Integer>() {
            public Integer apply(Integer input) {
                return input*10;
            }
        });

        System.out.println(transform);


        //Arrays.copyOf();
    }
}
