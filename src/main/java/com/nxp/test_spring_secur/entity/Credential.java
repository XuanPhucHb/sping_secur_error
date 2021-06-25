package com.nxp.test_spring_secur.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    @JsonBackReference
    private Account account;

    @Column(insertable = false, updatable = false)
    private Long accountId;

    private String tokenKey;

    private Long createAt;

    private Long expiredAt;

    public boolean isExpired() {
        return System.currentTimeMillis() > expiredAt;
    }
}
