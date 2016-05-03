package xx;

import java.math.BigDecimal;

/**
 * Created by yjj on 16/3/25.
 */
public class BigTest {


    public static void main(String[] args) {


        BigDecimal a = new BigDecimal("1000000000");
        BigDecimal b = new BigDecimal(1000000000);
        System.out.println(a.multiply(b));

    }
}
