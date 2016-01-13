package hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import rx.Observer;
import rx.functions.Action1;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 14-7-17
 * Time: 下午8:35
 * To change this template use File | Settings | File Templates.
 */
public class Init {

    class CommandHelloWorld extends HystrixCommand<String> {


        private final String name;

        public CommandHelloWorld(String name) {

            super(HystrixCommandGroupKey.Factory.asKey("exampleGroup"));
            this.name = name;
        }

        @Override
        protected String run() throws Exception {

            return name;
        }
    }


    public void synchronousExecution() {

        String s = new CommandHelloWorld("world").execute();
        System.out.println(s);


    }

    public void asynchronousExecution() {

        Future<String> fs = new CommandHelloWorld("world").queue();
        try {
            String s = fs.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void reactiveExecution() {

        /*rx.Observable<String> fs = new CommandHelloWorld("world").observe();
        fs.subscribe(new Action1<String>(){

            @Override
            public void call(String s) {
                System.out.println("observable call()");

            }
        });*/

        rx.Observable<String> fFirst = new CommandHelloWorld("first").observe();
        rx.Observable<String> fSecond = new CommandHelloWorld("second").observe();


        fFirst.subscribe(new Observer<String>() {
            public void onCompleted() {

                System.out.println("onCompleted");
            }

            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            public void onNext(String s) {
                System.out.println("first Observer.onNext():" + s);
            }
        });

        fSecond.subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println("second Action1():  " + s);
            }
        });

        System.out.println(fFirst.toBlockingObservable().single());
        System.out.println(fSecond.toBlockingObservable().single());
    }


    class CommandHelloFailure extends HystrixCommand<String> {

        private final String name;

        public CommandHelloFailure(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("exampleGroup"));
            this.name = name;
        }


        @Override
        protected String run() throws Exception {
            throw new RuntimeException("this command always fails");
        }

        @Override
        protected String getFallback() {

            return "hello failure " + name + "!";

        }

    }

    void testCallBack() {

        new CommandHelloFailure("first").execute();
        new CommandHelloFailure("second").execute();

    }

    class CommandUsingRequestCache extends HystrixCommand<Boolean> {

        private final int value;

        CommandUsingRequestCache(int value) {

            super(HystrixCommandGroupKey.Factory.asKey("exampleGroup"));
            this.value = value;
        }

        @Override
        protected Boolean run() throws Exception {
            return value == 0 || value % 2 == 0;
        }

        @Override
        protected String getCacheKey() {
            // System.out.println(value + "getCacheKey(): ");
            return String.valueOf(value);
        }
    }


    void testWithoutCacheHits() {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {

            System.out.println(new CommandUsingRequestCache(2).execute());
            System.out.println(new CommandUsingRequestCache(1).execute());
            System.out.println(new CommandUsingRequestCache(0).execute());
            System.out.println(new CommandUsingRequestCache(58672).execute());

        } finally {

            context.shutdown();
        }
    }


    void testCacheHits() {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {

            CommandUsingRequestCache command2a = new CommandUsingRequestCache(2);
            CommandUsingRequestCache command2b = new CommandUsingRequestCache(2);

            System.out.println(command2a.execute());
            System.out.println(command2a.isResponseFromCache());
            System.out.println(command2b.execute());
            System.out.println(command2b.isResponseFromCache());

        } finally {
            context.shutdown();
        }

        context = HystrixRequestContext.initializeContext();
        try {
            CommandUsingRequestCache command3b = new CommandUsingRequestCache(2);
            System.out.println(command3b.execute());
            System.out.println(command3b.isResponseFromCache());
        } finally {
            context.shutdown();

        }
    }

    class CommandThatFailsSilently extends HystrixCommand<String>{

        private final boolean throwException;


        CommandThatFailsSilently(boolean throwException){
            super(HystrixCommandGroupKey.Factory.asKey("example"));
            this.throwException = throwException;
        }


        @Override
        protected String run() throws Exception {
            if(throwException){
                throw new RuntimeException("failure from CommandThatFailsFast");
            }else {
                return "success";
            }
        }

        @Override
        protected String getFallback(){
            return "failed";
        }
    }

    void testSuccessAndFailure(){

        System.out.println(new CommandThatFailsSilently(true).execute());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(new CommandThatFailsSilently(false).execute());

    }



    public static void main(String[] args) {

        Init init = new Init();

        // understand
        // init.reactiveExecution();
        //init.testCallBack();


        //init.testWithoutCacheHits();
        //init.testCacheHits();

        init.testSuccessAndFailure();

    }
}
