package jdkCommonMisunderstanding;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/28/14
 * Time: 4:55 PM
 */
public class NotVolatileWhetherThreadSafe {

    private static boolean commonShutdownFlag = false;
    private static  Integer count = 0;
    public static void main(String[] args) {

        for (Integer i = 0; i < 100; i++) {
            Read read = new Read();
            Thread thread = new Thread(read);
            thread.start();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        commonShutdownFlag = true;


//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    static class Read implements Runnable {
        @Override
        public void run() {
            while (!commonShutdownFlag) {
                System.out.println(count++);
            }
//            System.out.println("running");
        }
    }


}
