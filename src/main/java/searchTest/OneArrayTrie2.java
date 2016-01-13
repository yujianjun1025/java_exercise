package searchTest;

/**
 * Created by jianjun.yu on 15-5-4.
 */
public class OneArrayTrie2 {


    public static void main(String[] args) {


        Data12 data12 = new Data12();

        data12.add("abc");
        data12.add("ab");
        data12.add("abb");
        data12.add("abd");

        data12.display();

        System.out.println(data12.search("abc"));
        System.out.println(data12.search("ab"));
        System.out.println(data12.search("abb"));
        System.out.println(data12.search("abb"));
        System.out.println(data12.search("abd"));
        System.out.println(data12.search("abde"));
        System.out.println(data12.search("de"));

        data12.add("abd");

        data12.display();
    }

}
