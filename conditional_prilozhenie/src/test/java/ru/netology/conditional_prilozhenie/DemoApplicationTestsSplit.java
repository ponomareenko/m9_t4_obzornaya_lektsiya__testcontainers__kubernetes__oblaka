package ru.netology.conditional_prilozhenie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class DemoApplicationTestsSplit {
    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> myDevApp =
            new GenericContainer<>("devapp:latest")
                    .withExposedPorts(8080);

    private static final GenericContainer<?> myProdApp =
            new GenericContainer<>("prodapp:latest")
                    .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myDevApp.start();
        myProdApp.start();
    }

    @Test
    void returnDevApp() {
        ResponseEntity<String> devAppEntity = restTemplate.getForEntity(
                "http://localhost:" + myDevApp.getMappedPort(8080) + "/profile", String.class);

        String resultDevApp = devAppEntity.getBody();

        String respondDevApp = "Current profile is dev";

        Assertions.assertEquals(respondDevApp, resultDevApp);
    }

    @Test
    void returnProdApp() {
        ResponseEntity<String> prodAppEntity = restTemplate.getForEntity(
                "http://localhost:" + myProdApp.getMappedPort(8081) + "/profile", String.class);

        String resultProductionApp = prodAppEntity.getBody();

        String respondProductionApp = "Current profile is production";

        Assertions.assertEquals(respondProductionApp, resultProductionApp);
    }

}
