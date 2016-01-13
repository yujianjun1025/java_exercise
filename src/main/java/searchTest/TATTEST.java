package searchTest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: jingjun.liu
 * Date: 15-4-30
 * Time: 下午4:39
 */
public class TATTEST {

    private static ArrayList<Integer> base = Lists.newArrayListWithCapacity(10000);
    private static ArrayList<Integer> next = Lists.newArrayListWithCapacity(10000);
    private static ArrayList<Integer> check = Lists.newArrayListWithCapacity(10000);

    private static int rootState = 0;
    private static int nextAndCheckCurrIdx = 1;
    private static int valState = 1;

    private static HashMap<Integer, Integer> result = Maps.newHashMap();

    private static int toId(char c) {
        return c - '\0';
    }

    private static ArrayList<Integer> getSNext(int state) {
        ArrayList<Integer> charIdList = Lists.newArrayList();
        for (int i = 0; i <= nextAndCheckCurrIdx; i++) {
            if (check.get(i) == state) {
                charIdList.add(i - base.get(state));
            }
        }
        return charIdList;
    }

    private static int getB(int state, ArrayList<Integer> sNext) {
        for (int i = 1; i <= nextAndCheckCurrIdx; i++) {
            boolean flag = true;
            for (Integer c : sNext) {
                if (next.get(i + c) != 0 || check.get(i + c) != 0) {
                    flag = false;
                }
            }

            if (flag) {
                return i;
            }
        }
        return -1;
    }

    private static boolean add(String word, int value) {
        int s = rootState;
        for (int i = 0; i != word.length(); i++) {
            int c = toId(word.charAt(i));
            if (next.get(base.get(s) + c) != 0) {


                ArrayList<Integer> sNext = getSNext(s);
                int b = getB(s, sNext);
                if (b == -1) {
                    System.out.println("ERROR: 找不到b.");
                    return false;
                }
                for (int j = 0; j != sNext.size(); j++) {
                    check.set(b + c, s);
                    next.set(b + c, next.get(base.get(s) + c));
                    check.set(base.get(s) + c, 0);
                }
                base.set(s, b);
            }
            next.set(base.get(s) + c, valState++);
            check.set(base.get(s) + c, s);
            s = valState - 1;
            if (i == word.length() - 1) {
                result.put(s, value);
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        for (int i = 0; i != 10000; i++) {
            base.add(0);
            next.add(0);
            check.add(0);
        }
        String word = "abc";
        int value = 10;
        add(word, value);
        add("abd", 11);
        add("abe", 12);
        add("abf", 13);
        add("zzz", 14);
        add("zzx", 15);

        System.out.println(result);
    }
}
