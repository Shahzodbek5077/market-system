package com.example.marketsystem.repository;

import com.example.marketsystem.entity.Pay;
import com.example.marketsystem.entity.Role;
import com.example.marketsystem.entity.template.PayType;
import com.example.marketsystem.entity.template.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PayRepository extends JpaRepository<Pay, Long> {
    @Query("select r from Pay r where r.payType = ?1")
    Role getByPayType(PayType payType);
}
