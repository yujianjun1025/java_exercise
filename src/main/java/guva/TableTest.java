package guva;

import beanTest.Person;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Collections;
import java.util.Map;

/**
 * Created by root on 15-5-30.
 */
public class TableTest {

    static class Person{

        public Person(int i, int i1, String a, String s, int i2, int i3) {

        }
    }
    public static void main(String[] args) {
        Table<Integer,Integer,Person> personTable= HashBasedTable.create();
        personTable.put(1,20,new Person(1, 1, "a", "46546", 1, 20));
        personTable.put(0,30,new Person(2, 1, "ab", "46546", 0, 30));
        personTable.put(0,25,new Person(3, 1, "abc", "46546", 0, 25));
        personTable.put(1,50,new Person(4, 1, "aef", "46546", 1, 50));
        personTable.put(0,27,new Person(5, 1, "ade", "46546",0, 27));
        personTable.put(1,29,new Person(6, 1, "acc", "46546", 1, 29));
        personTable.put(0,33,new Person(7, 1, "add", "46546",0, 33));
        personTable.put(1,66,new Person(8, 1, "afadsf", "46546", 1, 66));

//1,得到行集合
        Map<Integer,Person> rowMap= personTable.row(0);
        int maxAge= Collections.max(rowMap.keySet());
        System.out.println(maxAge);
    }
}
