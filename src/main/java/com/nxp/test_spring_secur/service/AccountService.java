package com.nxp.test_spring_secur.service;

import com.nxp.test_spring_secur.entity.Account;
import com.nxp.test_spring_secur.entity.Credential;
import com.nxp.test_spring_secur.repo.AccountRepository;
import com.nxp.test_spring_secur.repo.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CredentialRepository credentialRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Account login(Account account){
        Credential credential;
        account.setPasswordHash(bCryptPasswordEncoder.encode(account.getPassword()));
        Account result = accountRepository.findAccountByUsernameAndPasswordHash(account.getUsername(), account.getPasswordHash());
        if(result != null){
            credential = credentialRepository.findByAccountId(result.getId());
            Set<Credential> credentialSet = new HashSet<>();
            credentialSet.add(credential);
            result.setCredentials(credentialSet);
        }
        return result;
    }

    public Account getUser(Long id) {
        return accountRepository.getOne(id);
    }

    public Account findAccountByToken(String token) {
        Optional<Credential> optionalCredential = credentialRepository.findByTokenKey(token);
        if(optionalCredential.isPresent()){
            Credential credential = optionalCredential.get();
            if(credential.isExpired()){
                return null;
            }
            return credential.getAccount();
        }
        return null;
    }

//    public Account getCre(String token) {
//        credentialRepository.fin
//    }
}
