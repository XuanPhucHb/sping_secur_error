package com.nxp.test_spring_secur.config;

import com.nxp.test_spring_secur.entity.Account;
import com.nxp.test_spring_secur.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    AccountService accountService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        System.out.println("AuthenticationProvider");
        Object credentials = usernamePasswordAuthenticationToken.getCredentials();
        //lấy token từ phía client truyền vào
        //kiểm tra xem credentials có bị null k, null thì báo lỗi

        System.out.println("credentials:" + credentials.toString());
        if (credentials != null && !credentials.toString().isEmpty()) {
            String token = String.valueOf(credentials);
            Account account = accountService.findAccountByToken(token);
            if(account == null){
                throw new UsernameNotFoundException("Can not find user with this token");
            }
            return User
                    .builder()
                    .username(account.getUsername())
                    .password(account.getPasswordHash())
                    .roles(account.getRoleString()).build();
            //k null thì kiểm tra với token đc cấp cho user trong DB: find user by token
            //đúng thì build 1 cái user có username, password và role

            //sau đó config sẽ đối chiếu quyền của user trong db để cho phép user đi vào các url hay k
        }
        throw new UsernameNotFoundException("Credential not found");
    }
}
