package com.nxp.test_spring_secur.controller;

import com.nxp.test_spring_secur.entity.Account;
import com.nxp.test_spring_secur.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/nxp/v1/account")
    public class AccountController {

//eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/loginAc", method = RequestMethod.POST)
    public Account login(@RequestBody Account account) {
        return accountService.login(account);
    }

    @RequestMapping(value = "/getInfo/{id}", method = RequestMethod.GET)
    public Account login(@PathVariable Long id) {
        return accountService.getUser(id);
    }

    @RequestMapping(value = "/cre", method = RequestMethod.GET)
    public Account login(@RequestParam String token) {
        return accountService.findAccountByToken(token);
    }
}
