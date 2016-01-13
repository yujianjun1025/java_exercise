package synonmnTest;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by yjj on 15/12/8.
 */
public class SplitTest {


    public static void main(String[] args) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/qs_log/luxunzawen.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/yjj/m/data/qs_log/world_count.txt"), "UTF-8"));
        Map<String, Integer> map = Maps.newHashMap();


        String line = null;
        int worldCount = 0;
        while ((line = bufferedReader.readLine()) != null) {

            int length = line.length();
            worldCount += length;
            for (int i = 0; i < length - 2; i++) {
                for (int j = i + 2; j <= i + 2 && j <= length; j++) {
                    worldCount++;
                    String world = line.substring(i, j);
                    Integer value = map.get(world);
                    if (value == null) {
                        map.put(world, 1);
                        continue;
                    }
                    value++;
                    map.put(world, value);
                }
            }
        }

        bufferedReader.close();


        Map<String, Integer> charMap = Maps.newHashMap();
        bufferedReader = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/qs_log/luxunzawen.txt")));
        int charCount = 0;
        while ((line = bufferedReader.readLine()) != null) {
            charCount += line.length();
            for (Character ch : line.toCharArray()) {
                Integer value = charMap.get(String.valueOf(ch));
                if (value == null) {
                    charMap.put(String.valueOf(ch), 1);
                    continue;
                }
                value++;
                charMap.put(String.valueOf(ch), value);

            }
        }

        Map<String, Double> charPMap = Maps.newHashMap();
        for (Map.Entry<String, Integer> entry : charMap.entrySet()) {
            charPMap.put(entry.getKey(), (1.0 * entry.getValue()) / charCount);
        }


        List<Map.Entry<String, Integer>> list = Lists.newArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });

        Iterable<Map.Entry<String, Integer>> iterable = Iterables.filter(list, new Predicate<Map.Entry<String, Integer>>() {
            public boolean apply(Map.Entry<String, Integer> input) {
                return input.getValue() > 5 && input.getKey().length() > 1;
            }
        });

        for (Map.Entry<String, Integer> entry : iterable) {
            if (entry.getValue() > 5) {

                double p = 1.0;
                int str_size = entry.getKey().length();
                if (str_size > 1) {
                    try {
                        p = ((1.0 * map.get(entry.getKey().substring(0, str_size - 1))) / worldCount) * charPMap.get(String.valueOf(entry.getKey().toCharArray()[str_size - 1]));
                    }catch (Exception e){
                        System.out.println(e);

                    }
                }
               /* for (Character ch : entry.getKey().toCharArray()) {
                    p *= charPMap.get(String.valueOf(ch));
                }*/
                double nowP = 1.0 * entry.getValue() / worldCount;
                double expect = nowP / p;
                bufferedWriter.write(entry.getKey() + "\t" + entry.getValue() + "\t" + nowP + "\t" + p + "\t" + expect + "\n");
            }
        }

        bufferedReader.close();
        bufferedWriter.close();

    }
}
