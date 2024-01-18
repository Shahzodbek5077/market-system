package com.example.marketsystem.repository;


import com.example.marketsystem.entity.Role;
import com.example.marketsystem.entity.template.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
