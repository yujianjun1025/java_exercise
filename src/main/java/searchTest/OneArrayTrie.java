package searchTest;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jianjun.yu on 15-4-29.
 */
public class OneArrayTrie {

    private static OneArrayTrie oneArrayTrie = new OneArrayTrie();
    static {
        oneArrayTrie.init();
    }

    private Data11.Element[] elements;

    private void init() {
        Data11.init();
        Data11.output();
        elements = Data11.getElements();
    }

    public Integer search(Integer curState, List<String> stringList) {

        if (CollectionUtils.isEmpty(stringList)) {
            return curState;
        }

        if (curState == null) {
            return null;
        }

        HashMap<String, Integer> condition2State = elements[curState].conditionAndState;
        if (MapUtils.isEmpty(condition2State)) {
            return null;
        }

        return search(condition2State.get(stringList.get(0)), stringList.subList(1, stringList.size()));
    }

    public static void searchTest(List<String> testList){

        long beginTime = System.nanoTime();
        System.out.println(oneArrayTrie.search(0, testList));
        System.out.println("OneArray耗时:" + String.valueOf(System.nanoTime() - beginTime));

        String string = testList.toString();
        System.out.println(string);
        beginTime = System.nanoTime();
        System.out.println(Data11.hashCheck.contains(testList));
        System.out.println("Hash耗时:" + String.valueOf(System.nanoTime() - beginTime));


        System.out.println("----------------------------");
    }

    public static void f(){

        searchTest(Lists.newArrayList("100", "101", "102", "103", "104", "105"));
        searchTest(Lists.newArrayList("100", "101", "102", "103", "104"));
        searchTest(Lists.newArrayList("100", "101", "102", "103", "109"));
        searchTest(Lists.newArrayList("101", "102", "103"));

    }

    public static void main(String[] args) {


        System.out.println("Data11.afterSortDate.size() == " + Data11.afterSortDate.size());
        f();


    }


}
