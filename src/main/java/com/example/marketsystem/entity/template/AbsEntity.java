package com.example.marketsystem.entity.template;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @CreatedBy
    @Column(updatable = false)
    private Long createdBy;


    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private Long updatedBy;

}
