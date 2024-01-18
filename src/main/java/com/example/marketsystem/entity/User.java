package com.example.marketsystem.entity;

import com.example.marketsystem.entity.template.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User extends AbsEntity implements UserDetails {

    private String name;

    @Pattern(regexp = "^998\\d{9}$",message = "Invalid phone number")
    @Column(nullable = false,unique = true)
    private String phoneNumber;

    private String address;

    private String password;

    private Double debit;


    @ManyToOne
    private Role role;

    private boolean accountNonExpired = true ;
    private boolean accountNonLocked = true;
    private boolean credentialNonExpired = true;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRoleName().name()));
    }

    @Override
    public String getUsername() {
        return this.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialNonExpired();
    }

}
