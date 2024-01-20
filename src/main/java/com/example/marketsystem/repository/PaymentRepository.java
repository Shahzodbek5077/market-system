package com.example.marketsystem.repository;

import com.example.marketsystem.entity.Payment;
import com.example.marketsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Payment p where p.user = ?1")
    List<Payment> getUserPayment(User user);

}
