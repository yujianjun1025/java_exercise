package synonmnTest;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by yjj on 15/12/10.
 */
public class SnsTest {


    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/qs_log/split_world/src.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/yjj/m/data/qs_log/split_world/res.txt"), "UTF-8"));

        String line = null;
        Integer worldCount = 0;
        Map<String, Node> map = Maps.newHashMap();
        while ((line = bufferedReader.readLine()) != null) {

            int length = line.length();
            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j <= length; j++) {
                    worldCount++;
                    String world = line.substring(i, j);
                    Node value = map.get(world);
                    if (value == null) {
                        value = new Node();
                    }

                    value.setCount(value.getCount() + 1);
                    if (i != 0) {
                        value.getLeft().add(line.substring(i - 1, i));
                    }

                    if (j != length) {
                        value.getRight().add(line.substring(j, j + 1));
                    }

                    map.put(world, value);
                }
            }
        }


        List<Map.Entry<String, Node>> list = Lists.newArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Node>>() {
            public int compare(Map.Entry<String, Node> o1, Map.Entry<String, Node> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (Map.Entry<String, Node> entry : list) {
            entry.getValue().setP(1.0 * entry.getValue().getCount() / worldCount);
        }

        for (Map.Entry<String, Node> entry : list) {

            bufferedWriter.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        bufferedReader.close();

    }


    static class Node {
        private Integer count = 0;
        private Double p = 0.0;
        private List<String> left = Lists.newArrayList();
        private List<String> right = Lists.newArrayList();

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Double getP() {
            return p;
        }

        public void setP(Double p) {
            this.p = p;
        }

        public List<String> getLeft() {
            return left;
        }

        public void setLeft(List<String> left) {
            this.left = left;
        }

        public List<String> getRight() {
            return right;
        }

        public void setRight(List<String> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "count=" + count +
                    ", p=" + p +
                    ", left=" + Joiner.on(" ").join(left) +
                    ", right=" + Joiner.on(" ").join(right);
        }
    }

    ;
}
