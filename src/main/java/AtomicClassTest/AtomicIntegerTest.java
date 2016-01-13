package AtomicClassTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/14/14
 * Time: 10:50 AM
 */
public class AtomicIntegerTest {


    private static AtomicInteger id = new AtomicInteger(0);

    public static void main(String[] args) {


        getNext();

    }

    public static  Integer getNext() {

        Integer old = null;
        Integer newValue = null;
        while (true){

            do {
                old = id.get();
                newValue = old + 1;
                System.out.println("1");
            } while (!id.compareAndSet(old, newValue));

            System.out.println("2");
            if(id.get() == -1){
                break;
            }
        }

        return newValue;

    }
}
