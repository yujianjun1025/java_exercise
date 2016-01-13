package jdkCommonMisunderstanding;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by root on 15-6-13.
 */
public class EnumTest {


    enum SINGLE {
        INSTANCE;
        private int key;
        private boolean flag;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        System.out.println(ToStringBuilder.reflectionToString(SINGLE.INSTANCE));
    }

}
