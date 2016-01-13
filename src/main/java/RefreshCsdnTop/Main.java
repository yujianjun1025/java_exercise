package RefreshCsdnTop;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-8-11 Time: 下午10:01 To change this template use File | Settings
 * | File Templates.
 */
public class Main {

    ParsHtml parsHtml = new ParsHtml();

    public void visitTime(Integer count, List<String> urls) {

        boolean flag = false;
        Integer success = 0;
        try {
            for (Integer i = 0; i < count; i++) {
                for (String str : urls) {
                    flag = parsHtml.visitCSDN(str);
                    if (flag) {
                        success++;
                    }
                }
                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("总共访问");
        stringBuilder.append(count * urls.size());
        stringBuilder.append("次页面");
        System.out.println(stringBuilder);

        System.out.printf("成功" + success + "次");
    }

    public void apply(Integer count) {

        List<String> urls = parsHtml.getAllArticleUrl("http://blog.csdn.net/minna_d");
        visitTime(count, urls);

    }

    public static void main(String[] args) {

        Main main = new Main();
        main.apply(1000);

    }
}
