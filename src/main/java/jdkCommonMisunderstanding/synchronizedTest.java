package jdkCommonMisunderstanding;

/**
 * Created by yjj on 16/3/31.
 */
public class synchronizedTest {

    static class A{

        public synchronized void a1(){

            while (true){
                System.out.println("a1()");
            }
        }


        public synchronized void a2(){

            while (true){
                System.out.println("a2()");
            }
        }


    }


    public static void oneObjectSynchronized() throws InterruptedException {

        A a = new A();


        new Thread(new Runnable() {
            @Override
            public void run() {

                a.a1();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                a.a2();
            }
        }).start();



        Thread.sleep(10000);

    }

    public static void notOneObjectSynchronized() throws InterruptedException {

        A a = new A();


        new Thread(new Runnable() {
            @Override
            public void run() {

                a.a1();
            }
        }).start();


        A a2 = new A();
        new Thread(new Runnable() {
            @Override
            public void run() {

                a2.a2();
            }
        }).start();



        Thread.sleep(10000);

    }

    public static void main(String[] args) throws InterruptedException {


        //oneObjectSynchronized();

        notOneObjectSynchronized();




    }
}
