package jdkCommonMisunderstanding;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 2/14/14
 * Time: 11:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LongIsThreadSafe {

    private static long aLong = 1;

    public static void main(String[] args) {

        LongIsThreadSafe longIsThreadSafe = new LongIsThreadSafe();
        longIsThreadSafe.validateLongIsThreadSafe(100, 100);

    }

    public void validateLongIsThreadSafe(int readThreadNum, int writeThreadNum) {

        for (int i = 0; i < readThreadNum; i++) {
            new ReadOrWriteThread(ReadOrWrite.READ).start();
        }

        for (int i = 0; i < writeThreadNum; i++) {
            new ReadOrWriteThread(ReadOrWrite.WRITE).start();
        }
    }

    enum ReadOrWrite {

        READ(0),
        WRITE(1),
        READ_AND_WRITE(2);
        private Integer code;

        ReadOrWrite(int i) {
            this.code = code;
        }
    }

    class ReadOrWriteThread extends Thread {

        private ReadOrWrite readOrWriteReadOrWrite;

        ReadOrWriteThread(ReadOrWrite readOrWriteThreadAction) {
            readOrWriteReadOrWrite = readOrWriteThreadAction;
        }

        @Override
        public void run() {
            switch (readOrWriteReadOrWrite) {
                case READ:

                    while (aLong == 1) {
                        continue;
                    }

                    System.out.println("read thread aLong != 0 && aLong != 1");
                    break;

                case WRITE:

                    while (aLong == 1) {
                        aLong *= 1;
                    }

                    System.out.println("write thread aLong != 0 && aLong != 1");

                    break;
                case READ_AND_WRITE:
                    break;
            }
        }
    }

}
