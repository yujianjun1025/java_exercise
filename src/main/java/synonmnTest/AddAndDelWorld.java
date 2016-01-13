package synonmnTest;

import com.google.common.base.Charsets;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yjj on 15/12/7.
 */
public class AddAndDelWorld {


    public static void main(String[] args) throws IOException {


        List<String> rr = Files.readLines(new File("/Users/yjj/m/data/qs_log/rr"), Charsets.UTF_8);
        Map<String, Integer> map = Maps.newHashMap();

        for (String string : rr) {

            String[] line = string.split(" ");
            if (line.length < 2) {
                continue;
            }
            map.put(line[1], Integer.parseInt(line[0]));
        }

        Multimap<String, String> multimap = ArrayListMultimap.create();
        List<String> zz = Files.readLines(new File("/Users/yjj/m/data/qs_log/zz"), Charsets.UTF_8);
        for (String line : zz) {
            String[] strings = line.split("\t");
            multimap.put(strings[0], strings[2]);
        }

        Set<String> userAddWorldRes = Sets.newHashSet();
        Set<String> userDelWorldRes = Sets.newHashSet();
        for (Map.Entry<String, Collection<String>> entry : multimap.asMap().entrySet()) {

            List<String> querys = (List<String>) entry.getValue();
            //加词逻辑
            for (int i = querys.size() - 1; i > 0; i--) {
                for (int j = i - 1; j >= 0; j--) {

                    if (map.get(querys.get(i)) != null && map.get(querys.get(i)) > 1 && querys.get(i).contains(querys.get(j)) && !querys.get(i).equals(querys.get(j))
                            && querys.get(j).length() > 1 && querys.get(i).length() - querys.get(j).length() < 4) {

                        String removeString = querys.get(i).replace(querys.get(j), "");
                        userAddWorldRes.add(querys.get(i) + "\t" + querys.get(j) + "\t" + removeString);
                        break;
                    }
                }

            }

            //减词逻辑
            for (int i = 0; i < querys.size() - 1; i++) {
                for (int j = i + 1; j < querys.size(); j++) {

                    if (map.get(querys.get(i)) != null && map.get(querys.get(i)) > 1 && querys.get(i).contains(querys.get(j)) && !querys.get(i).equals(querys.get(j))
                            && querys.get(j).length() > 1 && querys.get(i).length() - querys.get(j).length() < 4) {

                        String removeString = querys.get(i).replace(querys.get(j), "");
                        userDelWorldRes.add(querys.get(i) + "\t" + querys.get(j) + "\t" + removeString);
                        break;
                    }
                }

            }

        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/yjj/m/data/qs_log/userAddWorld.txt")));
        for (String string : userAddWorldRes) {
            bufferedWriter.write(string + "\n");
        }

        bufferedWriter.close();

        bufferedWriter = new BufferedWriter(new FileWriter(new File("/Users/yjj/m/data/qs_log/userDelWorld.txt")));
        for (String string : userDelWorldRes) {
            bufferedWriter.write(string + "\n");
        }


        bufferedWriter.close();


    }
}
