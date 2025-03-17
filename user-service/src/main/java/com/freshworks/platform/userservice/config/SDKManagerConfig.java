package com.freshworks.platform.userservice.config;

import com.freshworks.sdk.SDKManager;
import com.freshworks.sdk.exception.ClientCredentialsNotConfiguredException;
import com.freshworks.sdk.exception.RegionNotConfiguredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;

import org.springframework.context.annotation.Bean;

@Configuration
public class SDKManagerConfig {

    @Value("${client.id:dummyClientId}")
    private String clientId;

    @Value("${client.secret:dummyClientSecret}")
    private String clientSecret;

    @Bean
    public SDKManager sdkManager() throws SSLException, ClientCredentialsNotConfiguredException, RegionNotConfiguredException {
        return new SDKManager(com.freshworks.sdk.Configuration.builder()
                .forRegion(com.freshworks.sdk.Configuration.FreshworksRegion.SANDBOX)
                .setClientCredentials(clientId, clientSecret)
                .build());
    }
}