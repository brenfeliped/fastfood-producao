package com.fastfood.producao.configs;

import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecurityConfigTest {

    @Test
    void filterChain_shouldReturnChain() throws Exception {
        SecurityConfig config = new SecurityConfig();
        HttpSecurity http = mock(HttpSecurity.class, org.mockito.Answers.RETURNS_DEEP_STUBS);
        
        DefaultSecurityFilterChain mockChain = mock(DefaultSecurityFilterChain.class);
        when(http.build()).thenReturn(mockChain);

        SecurityFilterChain chain = config.filterChain(http);
        assertNotNull(chain);
    }
}
