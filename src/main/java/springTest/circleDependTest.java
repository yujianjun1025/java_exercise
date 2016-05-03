package springTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by yjj on 16/3/30.
 */
public class circleDependTest {

    public static void main(String[] args) {

        //FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("/Users/yjj/code/java_exercise/src/main/java/springTest/testPrototype.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("testPrototype.xml");
        System.out.println(context.getBean("testA"));

    }
}
