package synonmnTest;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by yjj on 15/12/14.
 */
public class DiscardTest {


    public static void main(String[] args) throws Exception {

        BufferedReader destCity = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/qs_log/addAndDel/dest_city.txt")));

        Map<Integer, String> map = Maps.newHashMap();
        String line = null;
        while ((line = destCity.readLine()) != null) {

            String[] str = line.split("\t");
            String[] str2 = str[1].split(":");
            map.put(Integer.parseInt(str2[1]), str2[0]);
        }

        destCity.close();

        List<String> discard = Files.readLines(new File("/Users/yjj/m/data/query_log/discard.txt"), Charsets.UTF_8);


        BufferedReader notContainSame = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/query_log/not_contain_same_city.txt")));


        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/yjj/m/data/query_log/not_contain_same_city_normalize"), "UTF-8"));


        Map<String, String> map1 = Maps.newHashMap();

        while ((line = notContainSame.readLine()) != null) {

            String[] str = line.split("\t");

            String cityName = map.get(Integer.parseInt(str[1]));
            str[0] = str[0].replace(cityName, "");

            cityName = map.get(Integer.parseInt(str[3]));
            str[2] = str[2].replace(cityName, "");


            for (String string : discard) {

                if (str[0].indexOf(string) != -1) {
                    str[0] = str[0].replace(string, "");
                }

                if (str[2].indexOf(string) != -1) {
                    str[2] = str[2].replace(string, "");
                }

            }


            if (!str[0].equals(str[2]) && str[0].length() > 1 && str[2].length() > 1) {

                if(str[2].equals(map1.get(str[0]))){
                    continue;
                }


                if(str[0].equals(map1.get(str[2]))){
                    continue;
                }

                map1.put(str[0], str[2]);
                map1.put(str[2], str[0]);

                bufferedWriter.write(Joiner.on("\t").join(str) + "\n");
            }

        }

        notContainSame.close();
        bufferedWriter.close();

    }
}
