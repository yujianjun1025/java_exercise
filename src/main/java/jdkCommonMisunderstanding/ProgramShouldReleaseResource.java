package jdkCommonMisunderstanding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 10:52 PM
 */
public class ProgramShouldReleaseResource {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for(Integer ite = 0 ; ite< 50; ite++){
            final Integer finalIte = ite;
            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    double tmp = 0.0;
                    for(Integer i = 0 ; i < 1000; i++){
                        for(Integer j = 0; j < 1000; j++){
                            //for(Integer k = 0; k < 100; k++){
                                //tmp = (9.9*i*9.9*j*k)/(1.1*i*i);
                                tmp = i*j;
                            //}
                        }
                    }
                    System.out.println(finalIte + ":" + tmp);
                }
            });
        }

        //executorService.shutdown();

    }
}
