package aboutMemeryTest;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 14-7-15
 * Time: 上午10:51
 * To change this template use File | Settings | File Templates.
 */
public class StrongReferences {


    public static void main(String[] args) {

        while(true){

            Object object = new Object();
            System.out.println(object);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
