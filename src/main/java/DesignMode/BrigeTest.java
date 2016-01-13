package DesignMode;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-7-20
 * Time: 下午6:33
 * To change this template use File | Settings | File Templates.
 */


interface SourceAble{
    void method1();
}

class Achieve1 implements SourceAble{

    @Override
    public void method1() {

    }
}

class Achieve2 implements SourceAble{

    @Override
    public void method1() {

    }
}


abstract class  Bridge {

    SourceAble sourceAble ;

    void method(){
        sourceAble.method1();
    }
    SourceAble getSourceAble() {
        return sourceAble;
    }

    void setSourceAble(SourceAble sourceAble) {
        this.sourceAble = sourceAble;
    }
}


class MyBridge extends  Bridge{

    @Override
    void method() {
        getSourceAble().method1();
    }
}


public class BrigeTest {

}
