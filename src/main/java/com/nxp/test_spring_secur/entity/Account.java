package com.nxp.test_spring_secur.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Transient
    private String password;

    private String passwordHash;

    private String fullName;
    private int role;
    private int status;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Credential> credentials;

    public String getRoleString(){
        return this.role == 1 ? "Admin" : "User";
    }
}
