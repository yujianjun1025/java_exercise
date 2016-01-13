package searchTest;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jianjun.yu on 15-5-5.
 * cache
 */
public class Data12 {

    BiMap<Character, Integer> CODE_MAP = HashBiMap.create();
    public int CODE = 0;
    public int END_CODE = -1;
    List<Node> nodes = Lists.newArrayList();

    public Data12() {
        nodes.add(new Node());
    }

    public Integer nextState(int cur, int condition) {
        return Collections.binarySearch(nodes.get(cur).child, condition);
    }

    public boolean searchEndCode(int cur){
        return CollectionUtils.isNotEmpty(nodes.get(cur).child) && nodes.get(cur).child.get(0).key == -1;
    }

    public boolean search(String string) {

        int cur = 0;
        Node state = nodes.get(0);
        for (char ch : string.toCharArray()) {

            Integer code = CODE_MAP.get(ch);
            if (code == null) {
                return false;
            }

            int index = nextState(cur, code);
            if (index < 0) {
                return false;
            }
            cur = state.child.get(index).value;
            state = nodes.get(cur);
        }

        return searchEndCode(cur);
    }


    public void add(String string) {

        Integer cur = 0;
        Node state = nodes.get(0);
        for (char ch : string.toCharArray()) {

            Integer code = CODE_MAP.get(ch);
            if (code == null) {
                code = CODE++;
                CODE_MAP.put(ch, code);
            }

            int index = nextState(cur, code);
            if (index < 0) {
                state.child.add(new KV(code, Node.NODE_LENGTH + 1));
                Collections.sort(state.child, KVComparator.INSTANCE);
                cur = Node.NODE_LENGTH + 1;
                state = new Node();
                nodes.add(state);
            } else {
                cur = state.child.get(index).value;
                state = nodes.get(cur);
            }
        }

        int index = nextState(cur, END_CODE);
        if (index < 0) {
            if (state == null) {
                state = new Node();
                nodes.add(state);
            }
            state.child.add(new KV(END_CODE, -1));
            Collections.sort(state.child, KVComparator.INSTANCE);
        }
    }


    public void display() {

        int i = 0;
        for(Node node : nodes){
            System.out.println("i == " + i);
            System.out.println(Joiner.on(",").join(node.child));
            i++;
        }
        System.out.println("----------------------");
    }

    private static class Node {

        private static int NODE_LENGTH = -1;

        public Node() {
            NODE_LENGTH++;
        }

        public volatile List<KV> child = Lists.newArrayList();
    }

    enum KVComparator implements Comparator<KV> {

        INSTANCE;

        public int compare(KV o1, KV o2) {
            return Ints.compare(o1.key, o2.key);
        }
    }

    private static class KV implements Comparable<Integer> {
        private final int key;
        private final int value;

        public KV(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(Integer key) {
            return key == null ? -1 : Ints.compare(this.key, key);
        }

        @Override
        public String toString() {
            return "KV{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
