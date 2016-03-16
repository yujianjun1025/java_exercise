package serializationTest;

import com.google.common.collect.Lists;
import com.sun.tools.javac.util.Pair;

/**
 * Created by yjj on 16/2/2.
 */
public class ByteTest {


    public static void main(String[] args) {



        byte [] xx = new byte[-1];

        TermInOneDoc inOneDoc = new TermInOneDoc();

        inOneDoc.setDocId(12);
        inOneDoc.setField(2);
        inOneDoc.setTf(34);
        inOneDoc.setRank(3.14);
        inOneDoc.setPositions(Lists.newArrayList(3, 5, 7, 13, 14, 6, 7, 9, 0));


        Pair<TermInOneDoc,Integer> tmp = TermInOneDoc.byte2Object(inOneDoc.toBytes(), 0);
        System.out.println(tmp.fst);


        TermInOneDoc inOneDoc2 = new TermInOneDoc();

        inOneDoc2.setDocId(124);
        inOneDoc2.setField(112);
        inOneDoc2.setTf(340);
        inOneDoc2.setRank(3.564);
        inOneDoc2.setPositions(Lists.newArrayList(3, 5, 7, 13, 14, 6, 7, 9, 0, 11111, 111));

        TermInfo termInfo = new TermInfo(Lists.newArrayList(inOneDoc, inOneDoc2));




        byte[] bytes = termInfo.toBytes();


        System.out.println(TermInfo.byte2Object(bytes));


    }
}
