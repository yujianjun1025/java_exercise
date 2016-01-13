package jdkCommonMisunderstanding;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 9:57 PM
 */
public class GetUnsafeClass {

    public static Unsafe getUnsafe() {

        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return unsafe;
    }
}
