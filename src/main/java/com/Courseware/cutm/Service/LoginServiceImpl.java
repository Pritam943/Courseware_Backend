package com.Courseware.cutm.Service;

import com.Courseware.cutm.ExceptionHandling.ResourceNotFoundException;
import com.Courseware.cutm.model.Login;
import com.Courseware.cutm.model.Role;
import com.Courseware.cutm.repository.LoginRepository;
import java.util.List;

import com.Courseware.cutm.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login saveLogin(Login login) {
        Role role = this.roleRepo.findById(502).get();
        login.setRole(role);
        return (Login)this.loginRepository.save(login);
    }
    @Override
    public List<Login> getAllLoginDetail() {
        return this.loginRepository.findAll();
    }
    @Override
    public Login getLoginDetailById(long id) {
        return (Login)this.loginRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("LoginCourseware", "id", id);
        });
    }
    @Override
    public Login updateLoginDetails(Login loginCourseware, long id) {
        Login existingLogin = (Login)this.loginRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("LoginCourseware", "id", id);
        });
        existingLogin.setFirstname(loginCourseware.getFirstname());
        existingLogin.setLastname(loginCourseware.getLastname());
        existingLogin.setEmail(loginCourseware.getEmail());
        existingLogin.setPassword(loginCourseware.getPassword());
        this.loginRepository.save(existingLogin);
        return existingLogin;
    }

    @Override
    public Login deleteLoginDetails(Login login, long id) {
        if (!this.loginRepository.existsById(id)) {
            throw new ResourceNotFoundException("LoginCourseware", "id", id);
        } else {
            this.loginRepository.deleteById(id);
            return (Login)this.loginRepository.save(login);
        }
    }
}
