package aboutMemeryTest;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 14-7-15
 * Time: 上午10:42
 * To change this template use File | Settings | File Templates.
 */
public class Parameter {


    public static void main(String[] args) {

        StringBuffer str = new StringBuffer("hello");
        changeData(str);
        System.out.println(str);
    }

    public static void  changeData(StringBuffer str){

        StringBuffer tmp = new StringBuffer(str);
        str = tmp ;
        tmp.append("world");


    }
}
