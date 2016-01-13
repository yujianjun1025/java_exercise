package jdkCommonMisunderstanding;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/28/14
 * Time: 2:30 PM
 */
public class HashCodeIsNotObjectAddress {

    private  Integer value ;

    public HashCodeIsNotObjectAddress() {
        value = 1;
    }

    public HashCodeIsNotObjectAddress(Integer value) {
        this.value = value;
    }

    public static void main(String[] args) {

        Random random = new Random();
        HashCodeIsNotObjectAddress[] hashCodeIsNotObjectAddresses = new HashCodeIsNotObjectAddress[1000];
        for(Integer i = 0; i < 1000; i++){

            hashCodeIsNotObjectAddresses[i] = new HashCodeIsNotObjectAddress(i);
            System.out.println(hashCodeIsNotObjectAddresses[i].hashCode());
        }


    }
}
