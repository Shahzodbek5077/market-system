package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Payment extends AbsEntity {

    @OneToOne
    private User user;

    @OneToMany
    private List<Order> orders;

    @OneToMany
    private List<Pay> pays;

    @ManyToOne
    private Company company;

}
