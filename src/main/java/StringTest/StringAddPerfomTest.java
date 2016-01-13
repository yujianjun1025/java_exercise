package StringTest;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-8-11 Time: 下午6:33 To change this template use File | Settings |
 * File Templates.
 */
public class StringAddPerfomTest {

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList();
        for (Integer i = 0; i < 100000; i++) {
            list.add(String.valueOf(i));
        }

        String addStr = null;
        long begin = System.currentTimeMillis();
        for (String str : list) {
            addStr += str;
        }
        long end = System.currentTimeMillis();

        System.out.println("直接str + 耗时：" + String.valueOf(end - begin));

        StringBuilder stringBuilder = new StringBuilder();
        begin = System.currentTimeMillis();
        for (String str : list) {
            stringBuilder.append(str);

        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder.append()耗时：" + String.valueOf(end - begin));

        StringBuffer stringBuffer = new StringBuffer();
        begin = System.currentTimeMillis();
        for (String str : list) {
            stringBuffer.append(str);

        }
        end = System.currentTimeMillis();
        System.out.println("StringBuffer.append()耗时：" + String.valueOf(end - begin));

        begin = System.currentTimeMillis();
        Joiner.on("").join(list);
        end = System.currentTimeMillis();
        System.out.println("Joiner.on().join()耗时：" + String.valueOf(end - begin));

    }

}
