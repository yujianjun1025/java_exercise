package searchTest;

/**
 * Created by jianjun.yu on 15-5-5.
 */
public class ThreeArrayTrie {


    public static void main(String[] args) {


        Data31 data31 = new Data31();
        /*data31.add("ab");
        data31.add("abc");*/
        data31.add("ab");
        data31.add("b");
        //data31.add("c");
        //data31.add("abc");
        //data31.add("ac");
        //data31.add("b");

        System.out.println(data31.search("a"));
        //System.out.println(data31.search("ab"));
        System.out.println(data31.search("b"));
        //System.out.println(data31.search("c"));
        //System.out.println(data31.search("ac"));
        //System.out.println(data31.search("b"));

        //data31.add("b", false);
        //data31.add("b");
        //data31.add("c");
        //data31.add("d", false);
        /*data31.add("d");
        data31.add("e");*/
        //data31.add("c");
/*        data31.add("b");
        data31.add("c");*/
        data31.display(20);

    }


}