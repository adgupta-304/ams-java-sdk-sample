package com.freshworks.platform.userservice.service;

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
    private SDKManager sdkManager;

    protected CallCredentials getCallCredentials() throws Exception {
        Token token = sdkManager.generateClientAccessToken();
        return sdkManager.generateCallCredentialsWithToken(token.getAccessToken());
    }

    protected Channel getChannel() throws Exception {
        return sdkManager.getChannel();
    }

    protected <T extends AbstractStub<T>> T createStub(T stub) throws Exception {
        CallCredentials callCredentials = getCallCredentials();
        Channel channel = getChannel();
        return stub.withCallCredentials(callCredentials).withChannel(channel);
    }
}