package beanTest;

import java.util.Objects;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-7-27
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) applicationContext.getBean("person");



        System.out.println(Objects.toString(person));
    }
}
