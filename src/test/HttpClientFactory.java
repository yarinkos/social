import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class HttpClientFactory {

    private static CloseableHttpClient closeableHttpClient;

    private static final int SOCKET_TIMEOUT = 2500;
    private static final int HTTP_CONNECTION_TIMEOUT = 2500;
    private static final int HTTP_CONNECTION_REQUEST_TIMEOUT = 2500;
    private static final int MAX_POOL_CONNECTIONS = 200;
    private static final int MAX_CONNECTIONS = 10;

    private static final String existUserName = "user";
    private static final String existPassword = "password";

    private HttpClientFactory() {
    }

    private static CloseableHttpClient getClient() {

        if (HttpClientFactory.closeableHttpClient != null) {

            return HttpClientFactory.closeableHttpClient;
        }

        PoolingHttpClientConnectionManager connManager =
                new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(MAX_POOL_CONNECTIONS);
        connManager.setDefaultMaxPerRoute(MAX_CONNECTIONS);

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                // The time to establish the connection with the remote host
                .setConnectTimeout(HTTP_CONNECTION_TIMEOUT)
                // The time waiting for data – after the connection was established; maximum time of inactivity between two data packets
                .setSocketTimeout(SOCKET_TIMEOUT)
                // The time to wait for a connection from the connection manager/pool
                .setConnectionRequestTimeout(HTTP_CONNECTION_REQUEST_TIMEOUT)
                .setStaleConnectionCheckEnabled(true)
                .build();

        /*List<ResponseBuilder.Header> defaultHeaders = new ArrayList<ResponseBuilder.Header>();
        defaultHeaders.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, HTTP.UTF_8));
        defaultHeaders.add(new BasicHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE));
        defaultHeaders.add(new BasicHeader(HttpHeaders.USER_AGENT, "..." + context.getString(R.string.app_version)));*/

        HttpClientFactory.closeableHttpClient = HttpClients
                .custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(defaultRequestConfig)
                //.setDefaultHeaders(defaultHeaders)
                .build();
        HttpPost httpPost = new HttpPost("http://localhost:8080/login?username="+existUserName+"&password="+existPassword);
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");



        try {
            closeableHttpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return HttpClientFactory.closeableHttpClient;
    }

    public static synchronized ClientHttpRequestFactory getClientHttpRequestFactory() {

        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(getClient());

        httpComponentsClientHttpRequestFactory.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
        httpComponentsClientHttpRequestFactory.setReadTimeout(HTTP_CONNECTION_REQUEST_TIMEOUT);

        return httpComponentsClientHttpRequestFactory;
    }

    public static synchronized RestTemplate getRestTemplate() {

        return new RestTemplate(getClientHttpRequestFactory());
    }

    /*public static synchronized RestTemplate getRestTemplate() {

        return new RestTemplate(getClientHttpRequestFactory());
    }*/

}