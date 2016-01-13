package synonmnTest;

import com.google.common.collect.Maps;

import java.io.*;
import java.util.Map;

/**
 * Created by yjj on 15/12/11.
 */
public class mergePoi {


    public static void main(String[] args) throws Exception {

        BufferedReader idAndMergeId = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/merge_poi/idAndMergeId.txt")));


        String line = null;
        Map<Integer, String> poiInfoMap = Maps.newHashMap();
        BufferedReader poiInfo = new BufferedReader(new FileReader(new File("/Users/yjj/m/data/merge_poi/poiInfo")));
        while ((line = poiInfo.readLine()) != null) {

            String[] str = line.split("\t");
            poiInfoMap.put(Integer.parseInt(str[0]), str[1]);
        }
        poiInfo.close();

        //Map<Integer, Integer> integerIntegerMap = Maps.newHashMap();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/yjj/m/data/merge_poi/res.txt"), "UTF-8"));

        while ((line = idAndMergeId.readLine()) != null) {

            String[] str = line.split("\t");
            bufferedWriter.write(str[0] + "\t" + poiInfoMap.get(Integer.parseInt(str[0])) + "\t" + str[1] + "\t" + poiInfoMap.get(Integer.parseInt(str[1])) + "\n");
        }

        idAndMergeId.close();








    }
}
