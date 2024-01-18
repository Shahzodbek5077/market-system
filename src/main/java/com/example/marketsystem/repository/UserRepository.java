package com.example.marketsystem.repository;

import com.example.marketsystem.entity.User;
import com.example.marketsystem.entity.template.RoleName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumberEquals(String phoneNumber);
    @Query("select u from users u where u.role.roleName = ?1 and u.enabled = false ")
    Page<User> getByRoleName(RoleName roleName, Pageable pageable);

}
