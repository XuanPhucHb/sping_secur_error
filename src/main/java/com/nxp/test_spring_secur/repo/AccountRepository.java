package com.nxp.test_spring_secur.repo;

import com.nxp.test_spring_secur.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUsernameAndPasswordHash(String username, String password);
}
