package com.Courseware.cutm.repository;

import com.Courseware.cutm.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login,Long> {
    Optional<Login> findByEmail(String userName);
}
