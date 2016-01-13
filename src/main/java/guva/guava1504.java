package guva;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.http.annotation.Immutable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by jianjun.yu on 15-4-30.
 */
public class guava1504 {


    public static void main(String[] args) {

        test4();




    }

    private static void test4() {
        System.out.println(1>>2);
        System.out.println(1>>>2);
        System.out.println(-1>>>2);
        System.out.println(-1>>2);
        System.out.println( -2>>2);
        System.out.println( -2>>>2);
        System.out.println( -4>>2);
        System.out.println( -4>>>2);

        System.out.println( -32>>2);
        System.out.println( -32>>>2);

        System.out.println(1<<2);
        System.out.println(1<<2);
    }

    private static void test3(){
        List list = Collections.EMPTY_LIST;
        list.add("xx");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(0));
        }
    }
    private static void test1() {
        for( String str : Splitter.onPattern("\\s+").split("1       2 3")){

            System.out.println(str);
        }

        System.out.println("------------------");

        for(Map.Entry<String, String> entry : Splitter.on("#").withKeyValueSeparator(":").split("1:2#3:4").entrySet()){

            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());


        }


        Integer sum = 0;
        for(Integer i = 0; i < 10; i++){

            for(Integer j = 0; j < 10; j++){

                if(j > 3){
                    break;
                }

                sum+= j;
            }

        }

        System.out.println("sum == " + sum);


        sum = 0;
        position:
        for(Integer i = 0; i < 10; i++){

            for(Integer j = 0; j < 10; j++){

                if(j > 3){
                    continue position;
                }

                sum+= j;
            }

        }

        System.out.println("sum == " + sum);


        sum = 0;
        position1:
        for(Integer i = 0; i < 10; i++){

            for(Integer j = 0; j < 10; j++){

                if(j > 3){
                    break position1;
                }

                sum+= j;
            }

        }

        System.out.println("sum == " + sum);


        StringBuilder sb = new StringBuilder("result:");
        Joiner.on(" ").appendTo(sb, 1, 2, 3);
        System.out.println(sb);//result:1 2 3


        List<Integer> xx = Lists.newArrayList();
        List<Integer> hashCodes = Lists.newArrayList();
        for(Integer i =0; i < 1000; i++){
            xx.add(i);
            hashCodes.add(Arrays.hashCode(xx.toArray()));
        }
        Collections.sort(hashCodes);
        for(Integer i : hashCodes){
            System.out.println(i);
        }
        System.out.println(Integer.MAX_VALUE);
    }
}
