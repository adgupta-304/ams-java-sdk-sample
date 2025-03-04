package com.freshworks.platform.userservice.config;

import com.freshworks.sdk.Configuration;
import com.freshworks.sdk.SDKManager;
import com.freshworks.sdk.exception.ClientCredentialsNotConfiguredException;
import com.freshworks.sdk.exception.RegionNotConfiguredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;

@Component
public class SDKManagerConfig {

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    public SDKManager createSDKManager() throws SSLException, ClientCredentialsNotConfiguredException, RegionNotConfiguredException {
        return new SDKManager(Configuration.builder()
                .forRegion(Configuration.FreshworksRegion.SANDBOX)
                .setClientCredentials(clientId, clientSecret)
                .build());
    }
}