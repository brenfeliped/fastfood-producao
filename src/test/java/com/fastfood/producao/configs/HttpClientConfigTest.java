package com.fastfood.producao.configs;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HttpClientConfigTest {

    @Test
    void restTemplate_shouldReturnInstance() {
        HttpClientConfig config = new HttpClientConfig();
        RestTemplate restTemplate = config.restTemplate();
        assertNotNull(restTemplate);
    }
}
