package synonmnTest;

import com.google.common.collect.Sets;

import java.io.*;
import java.util.Set;

/**
 * Created by yjj on 15/12/12.
 */
public class intersectionTest {


    public static void main(String[] args) throws Exception {
        BufferedReader intersection = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/qs_log/addAndDel/intersection")));
        BufferedReader useful = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/qs_log/addAndDel/useful.txt")));

        Set<String> set = Sets.newHashSet();
        String line;
        while ((line = useful.readLine()) != null) {
            set.add(line);
        }

        useful.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/yjj/m/data/qs_log/addAndDel/res.txt"), "UTF-8"));

        while ((line = intersection.readLine()) != null) {

            String[] str = line.split("\t");
            if (set.contains(str[0]) || set.contains(str[1]) || set.contains(str[2])) {
                continue;
            }

            bufferedWriter.write(line + "\n");
        }

        intersection.close();
        bufferedWriter.close();
    }
}
