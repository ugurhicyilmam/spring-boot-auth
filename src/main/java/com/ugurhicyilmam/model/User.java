package com.ugurhicyilmam.model;

import com.ugurhicyilmam.util.enums.Language;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@ToString(exclude = {"password", "activationToken", "recoveryToken", "refreshToken"})
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToOne(mappedBy = "user")
    private transient ActivationToken activationToken;

    @OneToOne(mappedBy = "user")
    private transient RecoveryToken recoveryToken;

    @OneToMany(mappedBy = "user")
    private transient Set<RefreshToken> refreshToken;

    // ### UserDetails ###
    private long registeredAt;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getRealName() {
        return firstName + " " + lastName;
    }
}
