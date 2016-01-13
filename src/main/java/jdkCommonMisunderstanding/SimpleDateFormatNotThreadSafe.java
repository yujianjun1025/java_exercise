package jdkCommonMisunderstanding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 2:22 PM
 */
public class SimpleDateFormatNotThreadSafe {


    private static final String fixedDateStr = "14/04/27 04:27";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
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
                    Date date = simpleDateFormat.parse(fixedDateStr);
                    String transformStr = simpleDateFormat.format(date);
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
