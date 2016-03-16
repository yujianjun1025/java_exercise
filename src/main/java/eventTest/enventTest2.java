package eventTest;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by yjj on 16/1/18.
 */
public class enventTest2 {


    public static class TestEvent {
        private final int message;

        public TestEvent(int message) {
            this.message = message;
            System.out.println("event message:" + message);
        }

        public int getMessage() {
            return message;
        }
    }

    public static class EventListener {
        public int lastMessage = 0;

        @Subscribe
        public void listen(TestEvent event) {
            lastMessage = event.getMessage();
            System.out.println("Message:" + lastMessage);
        }

        public int getLastMessage() {
            return lastMessage;
        }
    }


    public static void main(String[] args) {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:" + listener.getLastMessage());
        ;


    }
}
