package com.freshworks.platform.userservice.controller;

import com.freshworks.platform.userservice.service.UserServiceImpl;
import com.freshworks.user.v2.GetUserByProductIdRequest;
import com.google.protobuf.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/accounts/product/{productAccountId}/{productUserId}")
    public String getUserByProductUserId(@PathVariable String productUserId,
                                         @PathVariable String productAccountId) {
        GetUserByProductIdRequest request = GetUserByProductIdRequest.newBuilder()
                .setProductUserId(StringValue.of(productUserId))
                .setProductAccountId(StringValue.of(productAccountId))
                .build();
        return userService.getUserByProductUserId(request);
    }
}