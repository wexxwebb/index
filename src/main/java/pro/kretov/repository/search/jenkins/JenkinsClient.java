package pro.kretov.repository.search.jenkins;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.kretov.repository.search.exception.JenkinsClientException;
import pro.kretov.repository.search.jobs.Job;
import pro.kretov.repository.search.jobs.Jobs;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author Aleksandr Kretov
 * @date 25.02.2019
 */

@SuppressWarnings("Duplicates")
@Component
public class JenkinsClient {

    private CloseableHttpClient closeableHttpClient;
    private HttpClientContext context;

    private final Gson gson;

    @Autowired
    public JenkinsClient(Gson gson) {
        this.gson = gson;
    }

    @PostConstruct
    public void postConstruct() {
        HttpHost targetHost = new HttpHost("0001git02", 8080, "http");

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("avkretov", "avkretov"));

        AuthCache authCache = new BasicAuthCache();
        authCache.put(targetHost, new BasicScheme());

        // Add AuthCache to the execution context
        context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);

        PoolingHttpClientConnectionManager poolingConnManager =
                new PoolingHttpClientConnectionManager();
        poolingConnManager.setMaxTotal(1000);
        poolingConnManager.setDefaultMaxPerRoute(100);
        closeableHttpClient = HttpClients.custom()
                .setConnectionManager(poolingConnManager)
                .build();
    }

    public Jobs getJobs() throws IOException {
        HttpGet httpGet = new HttpGet("http://0001git02:8080/api/json?pretty=true");
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet, context);
        String json = IOUtils.toString(closeableHttpResponse.getEntity().getContent(), UTF_8);
        return gson.fromJson(json, Jobs.class);
    }

    public ZipFile getZip(Job job) throws IOException, JenkinsClientException {
        String address = "http://0001git02:8080/job/" + job.getName() + "/ws/*zip*/" + job.getName() + ".zip";
        HttpGet httpGet = new HttpGet(address);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet, context);
        if (checkStatusCode(closeableHttpResponse)) {
            Path path = Paths.get("zip/" + job.getName() + ".zip");
            Files.copy(closeableHttpResponse.getEntity().getContent(), path, REPLACE_EXISTING);
            return new ZipFile(path.toFile());
        }
        throw new JenkinsClientException();
    }

    public ZipFile getZip(String link, String name) throws IOException, JenkinsClientException {
        HttpGet httpGet = new HttpGet(link);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet, context);
        if (checkStatusCode(closeableHttpResponse)) {
            Path path = Paths.get("zip/" + name + ".zip");
            Files.copy(closeableHttpResponse.getEntity().getContent(), path, REPLACE_EXISTING);
            return new ZipFile(path.toFile());
        }
        throw new JenkinsClientException();
    }

    private boolean checkStatusCode(CloseableHttpResponse closeableHttpResponse) {
        return closeableHttpResponse.getStatusLine().getStatusCode() >= 200
                && closeableHttpResponse.getStatusLine().getStatusCode() < 300;
    }
}
