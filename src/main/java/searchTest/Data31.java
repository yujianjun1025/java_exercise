package searchTest;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by jianjun.yu on 15-5-5.
 */
public class Data31 {

    BiMap<Character, Integer> CODE_MAP = HashBiMap.create();
    public int CODE = 0;
    private int NEW_STATE = 0;
    private int BASE_VALUE = 0;
    private int[] base = new int[100];
    private int[] next = new int[100];
    private int[] check = new int[100];

    private Set<Integer> MARK_FIN = Sets.newHashSet();

    public Data31() {

        for (int i = 0; i < next.length; i++) {
            next[i] = -1;
            check[i] = -1;
        }
    }

    private String display(int[] base, int length) {

        StringBuilder stringBuilder = new StringBuilder("");
        for (Integer i = 0; i < length; i++) {
            stringBuilder.append(String.format("%4s", base[i])).append(", ");
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();

    }

    public void display(int length) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Integer i = 0; i < length; i++) {
            stringBuilder.append(String.format("%4s", i)).append(", ");
        }
        stringBuilder.append("\n");

        System.out.println("index:" + stringBuilder.toString());
        System.out.println("base :" + display(base, length));
        System.out.println("next :" + display(next, length));
        System.out.println("check:" + display(check, length));

        System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(CODE_MAP));

        System.out.println(Joiner.on(",").join(MARK_FIN));

    }

    public List<Integer> getSNextCValue(int s) {

        List<Integer> result = Lists.newArrayList();

        if (s == 0) {
            return result;
        }
        for (int i = 0; i < check.length; i++) {

            if (check[i] == s) {
                result.add(i - base[s]);
            }
        }

        return result;

    }

    public int getBaseValue(List<Integer> sNextCValue, int s) {


        int b = BASE_VALUE;

        if (CollectionUtils.isEmpty(sNextCValue)) {
            b = ++BASE_VALUE;
            return b;
        }


        while (true) {

            boolean flag = true;
            for (Integer c : sNextCValue) {

                if (check[b + c] != -1) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return b;
            }
            b++;
        }
    }


    public boolean search(String string) {

        int s = base[0];
        for (char ch : string.toCharArray()) {

            Integer c = CODE_MAP.get(ch);
            if (c == null) {
                c = ++CODE;
                CODE_MAP.put(ch, c);
            }

            int nextState = getNextState(s, c);
            if (nextState == -1) {
                return false;
            } else {
                s = nextState;
            }
        }

        return MARK_FIN.contains(s);
    }

    public void add(String string) {

        if (StringUtils.isEmpty(string)) {
            return;
        }

        int s = base[0];
        for (char ch : string.toCharArray()) {

            Integer c = CODE_MAP.get(ch);
            if (c == null) {
                c = ++CODE;
                CODE_MAP.put(ch, c);
            }

            int nextState = getNextState(s, c);
            if (nextState == -1) {
                s = addNewState(s, c);
            } else {
                s = nextState;
            }
        }

        //最后一个状态一定为终止状态
        MARK_FIN.add(s);
    }


    private int getNextState(int s, Integer c) {

        int t = base[s] + c;
        if (check[t] == s) {
            return next[t];
        }
        return -1;
    }

    private int addNewState(int s, final int c) {

        int newState = ++NEW_STATE;

        if (next[base[s] + c] == -1) {
            check[base[s] + c] = s;
            next[base[s] + c] = newState;

        } else {


            if(s != 0){

                List<Integer> sNextCValue = getSNextCValue(s);
                int originB = base[s];
                int b = getBaseValue(sNextCValue, s);
                for (Integer needMoveC : sNextCValue) {

                    check[b + needMoveC] = s;
                    next[b + needMoveC] = next[base[s] + c];
                    check[originB + needMoveC] = -1;
                }

                base[s] = b;
                check[base[s] + c] = s;
                next[base[s] + c] = newState;

            }else {

                int conflictState = base[s] + c;

                int oldS = s;
                s = conflictState;
                List<Integer> sNextCValue = getSNextCValue(s);
                int originB = base[s];
                int b = getBaseValue(sNextCValue, s);
                for (Integer needMoveC : sNextCValue) {

                    check[b + needMoveC] = s;
                    next[b + needMoveC] = next[base[s] + c];
                    check[originB + needMoveC] = -1;
                }

                base[s] = b;

                s = oldS;
                check[base[s] + c] = s;
                next[base[s] + c] = newState;

            }


        }

        return base[s] + c;
    }

}
