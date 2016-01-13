package searchTest;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jianjun.yu on 15-4-29.
 */
public class Data11 {

    //保存字符编码code
    public static Map<String, Integer> codeMap = Maps.newHashMap();
    private static Element[] elements = null;
    public static Set<String> hashCheck = Sets.newHashSet();
    public static List<String> afterSortDate = Lists.newArrayList();
    static {
        List<Integer> integers = Lists.newArrayList(100,101,102,103,104,105);

        long beginTime = System.nanoTime();
        for (Collection<Integer> integerCollection : Collections2.orderedPermutations(integers)){

            String string = "\"" + Joiner.on("\",\"").join(integerCollection) +"\"";
            System.out.println(string);
            afterSortDate.add(string);
        }
        System.out.println("初始化耗时:"+String.valueOf(System.nanoTime() - beginTime));
    }

    public static Element[] getElements() {
        return elements;
    }

    public static void init() {

        ListTrie listTrie = new ListTrie("", 0);
        for (String string : afterSortDate) {
            ListTrie.structure(Lists.newArrayList(string.split(",")), listTrie, 0);
        }
        elements = new Element[ListTrie.INDEX + 2];
        listTrie.buildElement(elements);

        for(String string : afterSortDate){
            hashCheck.add(string);
        }

    }

    public static void output(){
        for(Element element : elements){

            if(element == null || MapUtils.isEmpty(element.conditionAndState)){
                continue;
            }
            for(Map.Entry<String, Integer> entry : element.conditionAndState.entrySet()){
                System.out.println("state:" + element.state + " + " + entry.getKey() + " --------> target: " + entry.getValue());
            }
        }
    }

    static class ListTrie {
        private static int INDEX = 0;

        String key;
        public ListTrie(String key, int depth) {
            this.key = key;
            this.depth = depth;
            index = INDEX++;
        }

        int depth = 0;
        private int index;

        List<ListTrie> childList = Lists.newArrayList();

        //递归构造trie树
        public static void structure(List<String> strings, ListTrie listTrie, int depth) {

            if (CollectionUtils.isEmpty(strings)) {
                return;
            }

            ++depth;
            boolean flag = false;

            //说明有子节点与下一个条件相匹配
            for (ListTrie child : listTrie.childList) {
                if (strings.get(0).equals(child.key)) {
                    strings.remove(0);
                    structure(strings, child, depth);
                    flag = true;
                }
            }

            if (!flag) {
                listTrie.childList.add(new ListTrie(strings.get(0), depth));
                strings.remove(0);
                structure(strings, listTrie.childList.get(listTrie.childList.size() - 1), depth);
            }
        }

        public void prevDisplay() {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("index:").append(index).append(",");
            stringBuilder.append("depth:").append(depth).append(",");

            stringBuilder.append(this.key);
            System.out.println(stringBuilder.toString());
            for (ListTrie child : this.childList) {
                child.prevDisplay();
            }
        }

        public void buildElement(Element[] elements) {

            Element element = new Element(index);
            if (CollectionUtils.isEmpty(childList)) {
                return;
            }

            element.startCode = this.key;
            for (ListTrie listTrie : childList) {

                if(CollectionUtils.isEmpty(listTrie.childList)){
                    element.conditionAndState.put(listTrie.key, -1);
                    continue;
                }

                element.conditionAndState.put(listTrie.key, listTrie.index);
                listTrie.buildElement(elements);
            }
            elements[index] = element;
        }
    }

    static class Element {
        String startCode = "";

        int state = 0;
        public Element(int state) {
            this.state = state;
        }

        public Element(){

        }

        HashMap<String, Integer> conditionAndState = Maps.newHashMap();
    }


}
