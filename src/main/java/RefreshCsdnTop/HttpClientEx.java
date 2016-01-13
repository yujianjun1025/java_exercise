package RefreshCsdnTop;

import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.google.common.collect.Lists;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 3/28/14
 * Time: 1:44 PM
 */
public class HttpClientEx {

    HttpClient httpClient = null;

    private HttpClientEx(Builder builder) {

        httpClient = builder.httpClient;

    }

    public static Builder create() {
        return new Builder();
    }


    public HttpClient getHttpClient() {
        return httpClient;
    }

    public final static class Builder {

        private HttpHost httpHost = null;
        private HttpParams httpParams = null;
        private List<BasicHeader> basicHeaders = null;
        private HttpClient httpClient = null;


        private Builder() {
            httpHost = new HttpHost("qproxy.beta.xx.com", 80);
            httpParams = new BasicHttpParams();
            httpParams.setParameter(ClientPNames.DEFAULT_HEADERS, basicHeaders);
            httpParams.setParameter("http.socket.timeout", 60000);
            httpParams.setParameter("http.connection.timeout", 60000);
            httpParams.setParameter("http.connection-manager.timeout", 100000000L);
            basicHeaders = Lists.newArrayList();
            basicHeaders.add(new BasicHeader("QProxy-Token", "token"));
        }

        public Builder proxy(HttpHost httpHost) {
            this.httpHost = httpHost;
            return this;
        }

        public Builder basicHead(BasicHeader basicHeader) {
            this.basicHeaders.add(basicHeader);
            return this;
        }

        public Builder basicHeads(List<BasicHeader> basicHeaders) {

            for (BasicHeader basicHeader : basicHeaders) {
                this.basicHeaders.add(basicHeader);
            }
            return this;

        }

        public Builder httpParams(HttpParams httpParams) {
            this.httpParams = httpParams;
            return this;
        }

        public HttpClientEx build() {

            httpParams.setParameter(ClientPNames.DEFAULT_HEADERS, basicHeaders);
            httpClient = new DefaultHttpClient(httpParams);
            httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, httpHost);

            return new HttpClientEx(this);
        }

    }




}
