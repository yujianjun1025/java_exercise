package guva;

import com.google.common.base.CaseFormat;

/**
 * Created by root on 15-5-31.
 */
public class StringTest {



    private static void testCaseFormat(){
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }

    public static void main(String[] args) throws InterruptedException {

        while(true){
            testCaseFormat();
            Thread.sleep(1000);
        }
    }
}
