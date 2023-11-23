package com.Courseware.cutm;

import com.Courseware.cutm.model.Role;
import com.Courseware.cutm.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CoursewarebackendApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(CoursewarebackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(this.passwordEncoder.encode("12345"));
		try{
			Role role = new Role();
			role.setId(501);
			role.setName("ADMIN");

			Role role1 = new Role();
			role1.setId(502);
			role1.setName("TEACHER");

			List<Role> roles = List.of(role, role1);
			List<Role> result = this.roleRepo.saveAll(roles);
			result.forEach(r -> System.out.println(r.getName()));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
