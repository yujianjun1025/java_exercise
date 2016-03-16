package eventTest;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by yjj on 16/1/19.
 */
public class syncTest {


    static class Consumer{

        @Subscribe
        public void lister(Integer num) throws InterruptedException {
            System.out.println(num);
           // Thread.currentThread().sleep(10);
        }

        @Subscribe
        public void listerString(String str){
            System.out.println(str);
        }
    }

    static class Provider{

        EventBus eventBus = new EventBus();

        Consumer consumer = new Consumer();
        public Provider() {
            eventBus.register(consumer);
        }

        public void run(){

            for(int i = 0 ;i < 1000000000; i++)
            {
                eventBus.post((Integer) i);
                System.out.println("xx");
            }

            eventBus.post("end");
            eventBus.unregister(consumer);
        }
    }


    public static void main(String[] args) {

        Provider provider = new Provider();
        provider.run();
    }
}
