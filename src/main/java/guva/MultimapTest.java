package guva;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 15-6-1.
 */
public class MultimapTest {

    public static void main(String[] args) {
        List<String> badGuys =
                Arrays.asList("Inky", "Blinky", "Pinky", "Pinky", "Clyde");
        Function<String, Integer> stringLengthFunction = new Function<String, Integer>() {
            public Integer apply(String input) {
                return input.length();
            }
        };
        Multimap<Integer, String> index =
                Multimaps.index(badGuys, stringLengthFunction);
        System.out.println(index);



        badGuys = Arrays.asList("Inky", "Blinky", "Pinky", "abcde");
        Map<Integer, String> map = Maps.uniqueIndex(badGuys, stringLengthFunction);
        System.out.println(map);
    }

}
