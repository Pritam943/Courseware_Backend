package com.Courseware.cutm.security;

import com.Courseware.cutm.ExceptionHandling.ApiException;
import com.Courseware.cutm.model.Login;
import com.Courseware.cutm.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = this.loginRepository.findByEmail(username).orElseThrow(() -> new ApiException("Username not found with" + username));
        return login;
    }
}
