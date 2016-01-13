package RefreshCsdnTop;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA. User: jianjun.yu Date: 14-8-11 Time: 下午10:02 To change this template use File | Settings
 * | File Templates.
 */
public class ParsHtml {

    private static  Integer times = 0;
    private HttpClient httpClient = HttpClientEx.create().build().getHttpClient();

    private static final String prefix = "http://blog.csdn.net/";
    private Pattern pattern = Pattern.compile("<a href=\"(.*)\">");

    public List<String> getAllArticleUrl(String urlPath) {

        List<String> list = Lists.newArrayList();

        Element element = getDocumentByUrl(urlPath).getElementById("article_list");
        for (Element tmp : element.getElementsByClass("link_title")) {
            Matcher matcher = pattern.matcher(tmp.toString());
            if (matcher.find()) {
                list.add(prefix + matcher.group(1));
            }

        }

        return list;

    }

    public boolean visitCSDN(String urlPath) {

        Document document = getDocumentByUrl(urlPath);
        return document == null ? false : true;

    }

    private Document getDocumentByUrl(String urlPath) {

        System.out.println("开始访问"+urlPath + ":" +times);
        times++;
        Document document = null;
        HttpGet httpget = new HttpGet(urlPath);
        boolean flag = true;
        try {

            for (int i = 1; i <= 10 && flag; i++) {
                try {
                    HttpResponse response = httpClient.execute(httpget);
                    Integer status = response.getStatusLine().getStatusCode();
                    if (200 == status) {
                        HttpEntity entity = response.getEntity();
                        String html = EntityUtils.toString(entity, "utf8");
                        document = Jsoup.parse(html);
                        flag = false;
                    } else {
                    }
                } catch (IOException e) {
                } finally {
                    httpget.releaseConnection();
                }
            }

        } catch (Exception e) {
            System.out.println("访问" + urlPath + "出现错误");
            e.printStackTrace();
        }
        System.out.println("结束访问"+urlPath + ":" + times);
        return document;

    }

}
