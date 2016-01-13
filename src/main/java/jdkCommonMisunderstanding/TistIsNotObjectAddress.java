package jdkCommonMisunderstanding;

import sun.misc.Unsafe;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/28/14
 * Time: 2:13 PM
 */
public class TistIsNotObjectAddress {

    private Unsafe unsafe = GetUnsafeClass.getUnsafe();
    private String str = "hello world";

    public static void main(String[] args) {

        for (Integer i = 0; i < 10; i++) {
            TistIsNotObjectAddress objectAddressTist = new TistIsNotObjectAddress();
            System.out.println(objectAddressTist);
            System.out.println("unsafeOffset: " + objectAddressTist.getUnsafeOffset());
            System.out.println("strOffset: " + objectAddressTist.getStrOffset());
        }


    }

    public long getUnsafeOffset(){
        long unsafeOffset = 0;
        try {
            unsafeOffset = unsafe.objectFieldOffset(TistIsNotObjectAddress.class.getDeclaredField("unsafe"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return unsafeOffset;
    }
    public long getStrOffset() {
        long strOffset = 0;
        try {
            strOffset = unsafe.objectFieldOffset(TistIsNotObjectAddress.class.getDeclaredField("str"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return strOffset;
    }

    public Unsafe getUnsafe() {

        return unsafe;


    }

    public Integer getAddressSize() {
        return unsafe.addressSize();
    }

    public void arrayBaseSize() {
        Integer baseOffset = unsafe.arrayBaseOffset(Object[].class);
        Integer indexScale = unsafe.arrayIndexScale(Object[].class);
        System.out.println(baseOffset);
        System.out.println(indexScale);


    }

}
