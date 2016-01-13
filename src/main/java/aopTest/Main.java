package aopTest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by yjj on 15/10/17.
 */
public class Main {


    public static void main(String[] args) throws IOException {


        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Enumeration resources = loader.getResources("");

        System.out.println();
        ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        User service = (User) factory.getBean("user");
        System.out.println(service.getClass());
        service.add();

    }
}
