package AtomicClassTest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 5/14/14
 * Time: 12:18 PM
 */
public class AtomicReferenceTest {

    private static  AtomicReference<String> ar= new AtomicReference<String>("ref");
    static class AddThread implements Runnable{

        public void run() {
//if current reference is "ref" then it changes as newref
            ar.compareAndSet("ref","newref");
// sets the new reference without any check
            ar.set("reference");
//sets new value and return the old value
            String s= ar.getAndSet("reference1");
            System.out.println(s);
        }
    }
    public static void main(String... args){
        new Thread(new AddThread()).start();
        new Thread(new AddThread()).start();
    }

}
