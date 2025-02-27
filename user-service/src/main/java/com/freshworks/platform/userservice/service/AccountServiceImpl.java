package com.freshworks.platform.userservice.service;

import com.freshworks.account.v2.GetAccountByProductIdRequest;
import com.freshworks.commons.v2.Account;
import com.freshworks.account.v2.AccountServiceGrpc;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseService {

    public String getAccountByProductAccountId(GetAccountByProductIdRequest request) {
        Account account = null;
        try {
            AccountServiceGrpc.AccountServiceBlockingStub stub = createStub(AccountServiceGrpc.newBlockingStub(getChannel()));
            account = stub.getAccountByProductAccountId(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account != null ? account.toString() : null;
    }
}