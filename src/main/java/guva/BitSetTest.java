package guva;

import com.google.common.collect.ObjectArrays;

import java.math.BigDecimal;
import java.util.BitSet;

/**
 * Created by root on 15-5-30.
 */
public class BitSetTest {

    private static String toBitSet(String str){
        String result = "[";
        BitSet bitSet = new BitSet();
        byte[] strBits = str.getBytes();
        for(int i = 0; i< strBits.length * 8; i++){
            if((strBits[i / 8] & (128 >> (i % 8))) > 0){
                bitSet.set(i);
            }
        }
        for(int i = 0; i < bitSet.length(); i++){
            if(bitSet.get(i))
                result += "1";
            else
                result +="0";
        }
        result += "]";
        return result;
    }

    public static void main(String[] args) {
        String str = "测试一下";
        System.out.println(toBitSet(str));


        Integer[] ints = ObjectArrays.newArray(Integer.class, 10);



        //bigDecimallTest();
    }

    private static void bigDecimallTest() {
        BigDecimal bigDecimal1 = new BigDecimal(12);
        BigDecimal bigDecimal2 = new BigDecimal("1245");
        System.out.println(bigDecimal1.add(bigDecimal2).toBigInteger());
    }
}
