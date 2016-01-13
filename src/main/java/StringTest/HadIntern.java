package StringTest;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-8-11
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
public class HadIntern {

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, String > map = Maps.newHashMap();
        for(Integer i = 0; i < 10000; i++){
            map.put(i, new String("aaaaaaaaaaaaaaa").intern());
        }

        Thread.sleep(1000000);
    }
}
