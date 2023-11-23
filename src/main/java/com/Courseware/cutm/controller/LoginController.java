package com.Courseware.cutm.controller;


import com.Courseware.cutm.Service.LoginService;
import com.Courseware.cutm.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/")
    public ResponseEntity<Login> save(@RequestBody Login login){
        return new ResponseEntity<>(loginService.saveLogin(login), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<Login>> getAllLoginDetails(){
        return new ResponseEntity<>(loginService.getAllLoginDetail(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Login> getLoginDetailById(@PathVariable("id") long LoginId){
        return new ResponseEntity<Login>(loginService.getLoginDetailById(LoginId),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Login> updateLoginDetails(@PathVariable("id") long id,@RequestBody Login loginCourseware)
    {
        return new ResponseEntity<Login>(loginService.updateLoginDetails(loginCourseware,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Login> deleteLoginDetails(@PathVariable("id") long id,@RequestBody Login login){
        return new ResponseEntity<Login>(loginService.deleteLoginDetails(login,id),HttpStatus.OK);
    }
}
