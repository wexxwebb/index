package pro.kretov.repository.search.jenkins;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import pro.kretov.repository.search.exception.JenkinsClientException;
import pro.kretov.repository.search.jobs.Job;
import pro.kretov.repository.search.jobs.Jobs;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Aleksandr Kretov
 * @date 22.02.2019
 */

class WorkSpaceTest {

//    @Test
//    void getWords() throws IOException {
//        Gson gson = new Gson();
//        JenkinsClient jenkinsClient = new JenkinsClient(gson);
//        jenkinsClient.postConstruct();
//
//        Jobs jobs = jenkinsClient.getJobs();
//
//        for (Job job : jobs.getJobs()) {
//            ZipFile zipFile;
//            try {
//                zipFile = jenkinsClient.getZip(job);
//            } catch (JenkinsClientException e) {
//                continue;
//            }
//            Enumeration<? extends ZipEntry> zipEntryEnumeration = zipFile.entries();
//            while (zipEntryEnumeration.hasMoreElements()) {
//                ZipEntry zipEntry = zipEntryEnumeration.nextElement();
//                System.out.println(zipEntry.getName());
//            }
//            zipFile.close();
//        }
//    }
}