package com.Courseware.cutm.controller;

import com.Courseware.cutm.ExceptionHandling.ApiException;
import com.Courseware.cutm.model.JwtAuthenticationRequest;
import com.Courseware.cutm.model.JwtAuthenticationResponse;
import com.Courseware.cutm.model.Login;
import com.Courseware.cutm.repository.LoginRepository;
import com.Courseware.cutm.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> createToken(@RequestBody JwtAuthenticationRequest request) {
        this.authenticate(request.getUserName(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setToken(token);
        response.setUser((Login)userDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException exception) {
            throw new ApiException("Incorrect username or password !!");
        }
    }
}
