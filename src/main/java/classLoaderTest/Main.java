package classLoaderTest;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: jianjun.yu
 * Date: 14-7-27
 * Time: 下午8:53
 * To change this template use File | Settings | File Templates.
 */
public class Main {


    public static void main(String[] args) {

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for(URL url : urls){
            System.out.println(url.toExternalForm());
        }

        System.out.println("AAAAAAAAAAAAAAAA");


        System.out.println(System.getProperty("sun.boot.class.path"));


        ClassLoader classLoader = Main.class.getClassLoader();
        while (classLoader != null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }


    }
}
