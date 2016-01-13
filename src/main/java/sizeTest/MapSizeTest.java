package sizeTest;

import StringTest.ShortDate;
import com.google.common.collect.*;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: root Date: 15-2-5 Time: 下午6:04 To change this template use File | Settings | File
 * Templates.
 */
public class MapSizeTest {

    private static List<Map<String, Integer>> mapList = Lists.newArrayList();


    public static void testRange() {

        RangeMap<Integer, Integer> rangeMap = TreeRangeMap.create();

        for(int i = 1, j = 10; i < 100; i+= 10, j+= 10){
            rangeMap.put(Range.range(i, BoundType.CLOSED, j, BoundType.CLOSED), i);
        }


        for(int i = 1; i < 100 ; i++){

            System.out.println(rangeMap.get(i));
        }

    }

    //^ 代表异或运算
    public static void test(){


        for(int i =0; i < 10; i++){
            System.out.println(i^1);
            System.out.println(i^2);
            System.out.println();
        }

    }

    public static void main(String[] args) {


        test();


    }
}