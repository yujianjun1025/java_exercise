package jdkCommonMisunderstanding;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 10:21 AM
 */
public class StaticDiffUsuallySynchronized {

    static class SynchronizedThread implements Runnable{

        private Integer state;

        SynchronizedThread(Integer state) {
            this.state = state;
        }

        public synchronized  static void synStat(){

            System.out.println("synStat begin");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("synStat end");
        }

        public synchronized void synCommon(){
            System.out.println(state);
        }


        public void run() {

            if(state == 2){
                synStat();
            }
            else{
                synCommon();
            }
        }
    }

    public static void main(String[] args) {
        for(Integer i = 0;  i < 10; i++){
            Thread thread = new Thread(new SynchronizedThread(i));
            thread.start();
        }
    }
}
