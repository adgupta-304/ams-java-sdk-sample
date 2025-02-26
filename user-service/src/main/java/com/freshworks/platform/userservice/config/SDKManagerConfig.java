package com.freshworks.platform.userservice.config;

import com.freshworks.sdk.Configuration;
import com.freshworks.sdk.SDKManager;
import com.freshworks.sdk.exception.ClientCredentialsNotConfiguredException;
import com.freshworks.sdk.exception.RegionNotConfiguredException;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLException;

@Component
public class SDKManagerConfig {

    public SDKManager createSDKManager(String clientId, String clientSecret) throws SSLException, ClientCredentialsNotConfiguredException, RegionNotConfiguredException {
        return new SDKManager(Configuration.builder()
                .forRegion(Configuration.FreshworksRegion.PHOENIX)
                .setClientCredentials(clientId, clientSecret)
                .build());
    }
}