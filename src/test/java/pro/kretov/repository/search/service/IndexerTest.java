package pro.kretov.repository.search.service;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import pro.kretov.repository.search.index.entity.Word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class IndexerTest {

    @Test
    void run() throws IOException {

//        Set<String> strings = new HashSet<>();
//
//        File file = new File("C:\\Users\\Aleksandr\\repositories\\RoutingCore\\RoutingCore-ejb\\src\\main\\java\\ru\\mts\\esb\\routing\\ESPPRouter.java");
//
//        String content = IOUtils.toString(new FileInputStream(file), UTF_8);
//
//        Pattern pattern = Pattern.compile("[@0-9A-Za-z\\-_]+");
//        Matcher matcher = pattern.matcher(content);
//        while (matcher.find()) {
//            strings.add(matcher.group());
//        }
//
//        System.out.println(strings.size());

    }
}