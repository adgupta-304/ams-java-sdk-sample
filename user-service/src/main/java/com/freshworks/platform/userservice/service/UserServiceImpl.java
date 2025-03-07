package com.freshworks.platform.userservice.service;

import com.freshworks.user.v2.UserServiceGrpc;
import com.freshworks.user.v2.GetUserByProductIdRequest;
import com.freshworks.commons.v2.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService {

    public String getUserByProductUserId(GetUserByProductIdRequest request) {
        User user = null;
        try {
            UserServiceGrpc.UserServiceBlockingStub stub = createStub(UserServiceGrpc.newBlockingStub(getChannel()));
            user = stub.getUserByProductUserId(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user != null ? user.toString() : null;
    }
}