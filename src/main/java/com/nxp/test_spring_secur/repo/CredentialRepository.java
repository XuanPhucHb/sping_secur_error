package com.nxp.test_spring_secur.repo;

import com.nxp.test_spring_secur.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Credential findByAccountId(Long accountId);

    Optional<Credential> findByTokenKey(String token);
}
