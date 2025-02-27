package com.freshworks.platform.userservice.controller;

import com.freshworks.platform.userservice.service.AccountServiceImpl;
import com.freshworks.account.v2.GetAccountByProductIdRequest;
import com.google.protobuf.StringValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountServiceImpl accountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/product/{productAccountId}")
    public String getAccountByProductAccountId(@PathVariable String productAccountId) {
        GetAccountByProductIdRequest request = GetAccountByProductIdRequest.newBuilder()
                .setProductAccountId(StringValue.of(productAccountId))
                .build();
        return accountService.getAccountByProductAccountId(request);
    }
}