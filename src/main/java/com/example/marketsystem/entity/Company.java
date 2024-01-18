package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Company extends AbsEntity {

    private String name;

}
