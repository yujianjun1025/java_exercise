package serializationTest;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sun.tools.javac.util.Pair;

import java.util.List;

/**
 * Created by yjj on 16/2/3.
 */
public class TermInfo {

    List<TermInOneDoc> inOneDocList = Lists.newArrayList();

    @Override
    public String toString() {
        return "TermInfo{\n" +
                "inOneDocList=\n" + Joiner.on("\n").join(inOneDocList) +
                "\n}";
    }

    public TermInfo(List<TermInOneDoc> inOneDocList) {
        this.inOneDocList = inOneDocList;
    }

    public byte[] toBytes(){

        byte[] bytes = new byte[1];
        int sumLen = 0;
        for(TermInOneDoc inOneDoc : inOneDocList){
            byte[] oneDocByte = inOneDoc.toBytes();
            bytes = NumberBytes.add2Tail(bytes, sumLen, oneDocByte, 0, oneDocByte.length);
            sumLen += oneDocByte.length;
        }

        byte[] ret = new byte[sumLen];
        System.arraycopy(bytes, 0, ret, 0, sumLen);
        return ret;
    }


    public static TermInfo byte2Object(byte[] bytes){

        List<TermInOneDoc> inOneDocs = Lists.newArrayList();

        int begin = 0;
        Pair<TermInOneDoc, Integer> pair;
        do{
            pair = TermInOneDoc.byte2Object(bytes, begin);
            inOneDocs.add(pair.fst);
            begin += pair.snd;

        }while (pair.snd < bytes.length && begin < bytes.length);

        return new TermInfo(inOneDocs);
    }
}
















































