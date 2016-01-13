package guva;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 15-5-30.
 */
public class ImmutableTest {

    public static void main(String[] args) {
        Set<Integer> data = new HashSet<Integer>();

        data.addAll(Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80));

        Set<Integer> fixedData = Collections.unmodifiableSet(data); // fixedData - [50, 70, 80, 20, 40, 10, 60, 30]

        System.out.println(Joiner.on(",").join(fixedData));

        data.add(90); // fixedData - [50, 70, 80, 20, 40, 10, 90, 60, 30]

        System.out.println(Joiner.on(",").join(fixedData));

    }
}
