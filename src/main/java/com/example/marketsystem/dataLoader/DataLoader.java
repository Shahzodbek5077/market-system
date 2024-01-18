package com.example.marketsystem.dataLoader;

import com.example.marketsystem.entity.Company;
import com.example.marketsystem.entity.Role;
import com.example.marketsystem.entity.User;
import com.example.marketsystem.entity.template.RoleName;
import com.example.marketsystem.exception.GenericNotFoundException;
import com.example.marketsystem.repository.CompanyRepository;
import com.example.marketsystem.repository.RoleRepository;
import com.example.marketsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String initMode;


    @Override
    public void run(String... args){
        if (initMode.equals("create-drop") || initMode.equals("create")) {
            roleRepository.save(Role.builder().roleName(RoleName.ROLE_USER).build());
            roleRepository.save(Role.builder().roleName(RoleName.ROLE_SELLER).build());
            roleRepository.save(Role.builder().roleName(RoleName.ROLE_ADMIN).build());
            companyRepository.save(new Company("Market"));
            User user = User.builder()
                    .name("Admin")
                    .phoneNumber("998993393300")
                    .role(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() ->
                            GenericNotFoundException.builder().message("role not found ").statusCode(404).build()))
                    .password("admin123").build();
            userRepository.save(user);
        }
    }
}
