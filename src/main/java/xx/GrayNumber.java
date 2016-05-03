package xx;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by yjj on 16/3/25.
 */
public class GrayNumber {

    static boolean isGray(int [] a, int cmp, int size){


        if(cmp == size - 1){
            return true;
        }

        int n = a[cmp] ^ a[cmp + 1];
        return (n & (n-1)) == 0 && isGray(a, cmp+1, size);
    }


    static boolean isGray(int [] a){

        if(a == null || a.length <= 1 ){
            return true;
        }
        return  isGray(a, 0, a.length);
    }

    public static void main(String[] args) {


        System.out.println(1^1);
        System.out.println(1^2);
        System.out.println(2^3);
        System.out.println(3^4);

        int[] a = new int[2];
        a[0] = 2;
        a[1] = 3;


       /* int[] a = new int[3];
        a[0] = 1;
        a[1] = 2;
        a[2] = 3;*/
        System.out.println(isGray(a));
    }



}
