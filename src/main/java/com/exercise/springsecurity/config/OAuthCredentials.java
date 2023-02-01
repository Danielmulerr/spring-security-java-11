package com.exercise.springsecurity.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OAuthCredentials {
    @Value("${oauth-credentials.google.client-id}")
    private String clientId;
    @Value("${oauth-credentials.google.client-secret}")
    private String clientSecret;
}
