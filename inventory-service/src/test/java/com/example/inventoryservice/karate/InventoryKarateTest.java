package com.example.inventoryservice.karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class InventoryKarateTest {

    @LocalServerPort
    private int serverPort;

    @Test
    void runInventoryTests() {
        String baseUrl = "http://localhost:" + serverPort;
        Results results = Runner.path("classpath:karate/inventory.feature")
                .systemProperty("karate.baseUrl", baseUrl)
                .reportDir("target/karate-reports")
                .outputJunitXml(true)
                .outputCucumberJson(true)
                .outputHtmlReport(true)
                .parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
