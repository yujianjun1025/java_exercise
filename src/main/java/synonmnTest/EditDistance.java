package synonmnTest;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.io.*;
import java.util.Collection;
import java.util.Map;

/**
 * Created by yjj on 15/12/16.
 */
public class EditDistance {


    public static int distance(String left, String right) {

        int m = left.length();
        int n = right.length();
        int matrix[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            matrix[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            matrix[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {

            Character p1 = left.charAt(i - 1);

            for (int j = 1; j <= n; j++) {

                Character p2 = right.charAt(j - 1);
                int res = p1.equals(p2) ? 0 : 1;
                matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + res);
            }
        }

        return matrix[m][n];

    }
/*
    public static void computer(Map<String, String> noResult, Map<String, List<String>> clickQuery) {

        for (Map.Entry<String, String> entry : noResult.entrySet()) {
            List<String> queryList = clickQuery.get(entry.getKey());
            for (String query : queryList) {
                System.out.println(distance(entry.getValue(), query));
            }
        }
    }*/

    public static void main(String[] args) throws IOException {

        System.out.println(distance("a", "b"));
        System.out.println(distance("北京", "北票"));
        System.out.println(distance("北京", "天津"));


        BufferedReader click = new BufferedReader(new FileReader(new File("/Users/yjj/m/no_result_log/click_query.txt")));
        BufferedReader no_result = new BufferedReader(new FileReader(new File("/Users/yjj/m/no_result_log/no_result.txt")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/yjj/m/no_result_log/distance.txt"), "UTF-8"));

        Multimap<String, String> clickQuery = ArrayListMultimap.create();
        Map<String, String> noResult = Maps.newHashMap();

        String line = null;
        while ((line = click.readLine()) != null) {

            String[] str = line.split("\t");
            clickQuery.put(str[0], str[1]);

        }

        while ((line = no_result.readLine()) != null) {

            String[] str = line.split("\t");

            Collection<String> queryList = clickQuery.get(str[0]);

            for (String query : queryList) {

                int maxLength = Math.max(str[1].length(), query.length());
                int dis = distance(str[1], query);
                if(maxLength == dis){
                    continue;
                }
                bufferedWriter.write(str[0] + "\t" + str[1] + "\t" + query + "\t" + distance(str[1], query) + "\n");
            }

        }


        click.close();
        no_result.close();
        bufferedWriter.close();
    }
}
