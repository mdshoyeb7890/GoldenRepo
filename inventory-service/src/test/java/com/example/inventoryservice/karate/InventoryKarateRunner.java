package com.example.inventoryservice.karate;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InventoryKarateRunner {

    @LocalServerPort
    private int serverPort;

    @Karate.Test
    Karate runInventoryTests() {
        String baseUrl = "http://localhost:" + serverPort;
        return Karate.run("classpath:karate/inventory.feature")
                .systemProperty("karate.baseUrl", baseUrl)
                .relativeTo(getClass());
    }
}
