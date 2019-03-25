package pro.kretov.repository.search.dao;

import jdk.internal.util.xml.impl.Input;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ElasticSearchTest {

    @Test
    void test() throws IOException {
        Client client = new PreBuiltTransportClient(
                Settings.builder().put("client.transport.sniff", true)
                        .put("cluster.name","elasticsearch").build())
                .addTransportAddress(
                        new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9200));
        String string = new String(Files.readAllBytes(Paths.get("")));

        InputStream inputStream = new FileInputStream("C:\\Users\\Aleksandr\\repositories\\repository-idex-search\\src\\main\\java\\pro\\kretov\\repository\\search\\config\\PersistenceConfiguration.java");

        XContent xContent = XContentFactory.xContentType(inputStream).xContent();


        IndexResponse response = client.prepareIndex("class", "Java")
                .setSource(xContent).get();

        System.out.println(response.getId());
        System.out.println(response.getIndex());
        System.out.println(response.getVersion());


    }
}
