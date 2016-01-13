package DesignMode;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-7-20
 * Time: 下午6:01
 * To change this template use File | Settings | File Templates.
 */

interface  Target {
    void  method1_target();
}

class Source{
    void method1(){
        System.out.println("Source method1");
    }

    void method2(){
        System.out.println("Source method2");
    }
}


class Adaptor extends Source implements Target{

    @Override
    public void method1_target() {

        method1();

    }
}



interface  Target1 {
    void method1();
    void method2();
}

abstract  class Wrapper implements Target1{

    public void method1() {};
    public void method2() {};

}

class Adaptor1 extends Wrapper{
    @Override
    public void method1() {
        super.method1();    //To change body of overridden methods use File | Settings | File Templates.
    }
}

public class AdaptorTest {




    public static void main(String[] args) {


        try {
            Class<Adaptor1> adaptor11 = (Class<Adaptor1>) Class.forName("Adaptor1");
            System.out.println(adaptor11.getConstructors());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
