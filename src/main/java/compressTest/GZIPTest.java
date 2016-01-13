package compressTest;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import java.io.ObjectStreamField;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by root on 15-6-24.
 */
public class GZIPTest {

    public static void main(String[] args) throws Exception {


    // normalCompress("/root/q/data/dynamic_test/test.xml", "/root/q/data/dynamic_test/str.zip");

     //   fileCompress("/root/q/data/dynamic_test/test.xml",  "/root/q/data/dynamic_test/file.zip", "/root/q/data/dynamic_test/file.restore");

        //readZipFileUnzip("/root/q/data/dynamic_test/file.zip");


        //readZipStr2FileUnzip("/root/q/data/dynamic_test/file.zip", "/root/q/data/dynamic_test/file.tmp.1" , "/root/q/data/dynamic_test/file.tmp.2");

        //str2fileTest();


        //x();


        fromByteCompress("/root/q/data/dynamic_test/test.xml", "/root/q/data/dynamic_test/test.zip");
    }


    /*
    读取文件内容之后压缩成字符串然后写到文件中测试
    * */
    private static void normalCompress(String srcFile, String zipStr) throws Exception {

        String originContent = FileUtils.fileRead(srcFile);
        System.out.println("\n源文件内容\n\n");
        System.out.println(originContent);
        FileUtils.fileWrite(zipStr, new String(GZIPUtil.compress(originContent.getBytes())));;

    }

    /*
    压缩文件测试
    * */
    private static void fileCompress(String srcFile, String zipFile, String restoreFile ) throws IOException {

        String originContent = FileUtils.fileRead(srcFile);
        System.out.println("\n源文件内容\n\n");
        System.out.println(originContent);

        GZIPUtil.compress(new FileInputStream(new File(srcFile)), new FileOutputStream(new File(zipFile)));

        String zipContent = FileUtils.fileRead(zipFile);
        System.out.println("\n压缩后文件内容\n\n");
        System.out.println(zipContent);

        GZIPUtil.decompress(new FileInputStream(new File(zipFile)), new FileOutputStream(new File(restoreFile)));

        String restoreContent = FileUtils.fileRead(restoreFile);
        System.out.println("\n压缩后还原的内容\n\n");
        System.out.println(restoreContent);

    }

    /*
    重文件中读取压缩了的字符串， 尝试解压
    * */
    private static void readZipStr2FileUnzip(String zipFile, String tmpFile, String resultFile) throws Exception {

        String zipContent = FileUtils.fileRead(zipFile);
        System.out.println("\n压缩后文件内容\n\n");
        System.out.println(zipContent);

        FileUtils.fileWrite(tmpFile, zipContent);
        GZIPUtil.decompress(new FileInputStream(new File(tmpFile)), new FileOutputStream(new File(resultFile)));
    }



    private static void x() throws Exception {




     /*   str= "\\^M   n\\t\\cccccccc ^ ";
        byte[] bytes1 = str.getBytes();
        byte[] bytes2 = new String(bytes1).getBytes();


        System.out.println("byte1 length: " + bytes1.length);
        System.out.println("byte2 length: " + bytes2.length);
*/

        //byte[] bytes2 = GZIPUtil.compress(str.getBytes(Charsets.UTF_8));

        String str = "ae";
        byte[] bytes2 = GZIPUtil.compress(str.getBytes(Charsets.ISO_8859_1));
        byte[] bytes3 = new String(bytes2).getBytes();

        System.out.println(Arrays.toString(bytes2));

        String strr = new String(bytes2, "gbk");
        //System.out.println(strr);

        byte[] bytes1 = strr.getBytes("gbk");
        System.out.println(Arrays.toString(bytes1));

        System.out.println("byte1 length: " + bytes1.length);
        System.out.println("byte2 length: " + bytes2.length);











      /*  FileUtils.fileWrite("x", compress);

        String inFileCompress = FileUtils.fileRead("x");

        System.out.println("重文件中读取的内容");
        System.out.println(inFileCompress);

        System.out.println("直接压缩的内容");
        System.out.println(compress);
        System.out.println(compress.equals(inFileCompress));

        //String unzip = new String(GZIPUtil.decompress(inFileCompress.getBytes()));
        //String unzip = new String(GZIPUtil.decompress(compress.getBytes()));

        //String unzip = new String(GZIPUtil.decompress(GZIPUtil.compress(str.getBytes())));
        //String unzip = new String(GZIPUtil.decompress(inFileCompress.getBytes()));
        String unzip = new String(GZIPUtil.decompress(compress.getBytes()));

        System.out.println("解压的内容");
        System.out.println(unzip);

*/



    }

    /*
    读取文件然后压缩成字节数组写入到文件中，之后再重文件中读取字符数组还原
    * */
    private static void fromByteCompress(String srcFile, String zipFile) throws Exception {


        byte[] bytes= Files.readAllBytes(Paths.get(srcFile));
        byte[] compress = GZIPUtil.compress(bytes);

        Files.write(Paths.get(zipFile), compress);

        byte[] readFromFile = Files.readAllBytes(Paths.get(zipFile));
        byte[] restore = GZIPUtil.decompress(readFromFile);

        System.out.println("还原内容:");
        System.out.println(new String(restore));

    }

}
