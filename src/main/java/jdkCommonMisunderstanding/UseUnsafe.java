package jdkCommonMisunderstanding;

import sun.misc.Unsafe;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 9:35 PM
 */
public class UseUnsafe {


    private static final Unsafe unsafe = GetUnsafeClass.getUnsafe();
    private static long firstOffset;
    private static long secondOffset;
    private static long threeOffset;
    private static long addressOffset;

    static {


        try {

            firstOffset = unsafe.objectFieldOffset(UseUnsafe.class.getDeclaredField("first"));
            secondOffset = unsafe.objectFieldOffset(UseUnsafe.class.getDeclaredField("second"));
            threeOffset = unsafe.objectFieldOffset(UseUnsafe.class.getDeclaredField("three"));
            addressOffset = unsafe.addressSize();


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    private Integer first;
    private Integer second;
    private Integer three;

    public static void objectLength() {
        System.out.println(UseUnsafe.addressOffset);
        System.out.println(UseUnsafe.firstOffset);
        System.out.println(UseUnsafe.secondOffset);
        System.out.println(UseUnsafe.threeOffset);
        System.out.println("end");
    }

    public static void arrayLength() {
        class Inner {
            private Integer integers[] = new Integer[100];
            private Integer second = 10;
        }

        try {

            long integersOffset = unsafe.objectFieldOffset(Inner.class.getDeclaredField("integers"));
            long secondOffset = unsafe.objectFieldOffset(Inner.class.getDeclaredField("second"));
            System.out.println(integersOffset);
            System.out.println(secondOffset);
            System.out.println("end");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    public static void readAndWrite(Integer size, Integer value) {
        long memoryAddress = unsafe.allocateMemory(size);
        unsafe.putAddress(memoryAddress, value);
        long readValue = unsafe.getAddress(memoryAddress);
        System.out.println(readValue);
        System.out.println("end");


    }

    public static void main(String[] args) {

        objectLength();
  //      arrayLength();
//        UseUnsafe.readAndWrite(10, 100);
    }


}
