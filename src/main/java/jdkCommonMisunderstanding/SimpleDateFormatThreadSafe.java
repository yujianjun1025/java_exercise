package jdkCommonMisunderstanding;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 3:26 PM
 */
public class SimpleDateFormatThreadSafe {

    private static final String fixedDateStr = "14/04/27 04:27";
    private static final ThreadLocal<DateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL =
            new ThreadLocal<DateFormat>() {
                @Override
                protected DateFormat initialValue() {
                    return new SimpleDateFormat("yy/MM/dd HH:mm");
                }
            };
    private static volatile boolean normalFlag = true;

    public static void main(String[] args) {

        for (Integer i = 0; i < 2; i++) {
            new Thread(new StrToDateEndToStr()).start();
        }


    }

    static class StrToDateEndToStr implements Runnable {

        public void run() {
            while (normalFlag) {
                try {
                    Date date = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(fixedDateStr);
                    String transformStr = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().format(date);
                    if (fixedDateStr.equals(transformStr)) {
                        System.out.println(transformStr);
                    } else {
                        System.out.println("error");
                        normalFlag = false;
                    }
                } catch (ParseException e) {
                    normalFlag = false;
                    e.printStackTrace();
                }
            }

        }
    }

}
