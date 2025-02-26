package com.freshworks.platform.userservice.service;

import com.freshworks.platform.userservice.config.SDKManagerConfig;
import com.freshworks.sdk.SDKManager;
import com.freshworks.sdk.authorization.Token;
import io.grpc.CallCredentials;
import io.grpc.Channel;
import io.grpc.stub.AbstractStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseService {

    @Autowired
    private SDKManagerConfig sdkManagerConfig;

    protected CallCredentials getCallCredentials(String clientId, String clientSecret) throws Exception {
        SDKManager sdkManager = sdkManagerConfig.createSDKManager(clientId, clientSecret);
        Token token = sdkManager.generateClientAccessToken();
        return sdkManager.generateCallCredentialsWithToken(token.getAccessToken());
    }

    protected Channel getChannel(String clientId, String clientSecret) throws Exception {
        SDKManager sdkManager = sdkManagerConfig.createSDKManager(clientId, clientSecret);
        return sdkManager.getChannel();
    }

    protected <T extends AbstractStub<T>> T createStub(T stub, String clientId, String clientSecret) throws Exception {
        CallCredentials callCredentials = getCallCredentials(clientId, clientSecret);
        Channel channel = getChannel(clientId, clientSecret);
        return stub.withCallCredentials(callCredentials).withChannel(channel);
    }
}