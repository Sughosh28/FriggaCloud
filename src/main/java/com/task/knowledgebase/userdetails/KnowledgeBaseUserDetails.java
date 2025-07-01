package com.task.knowledgebase.userdetails;

import com.task.knowledgebase.models.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KnowledgeBaseUserDetails implements UserDetails {
    private String username;
    private String password;
    private String email;
    private List<GrantedAuthority> authorities;
    public KnowledgeBaseUserDetails(String username, String password, String email, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }
    public static KnowledgeBaseUserDetails fromUser(UserEntity userEntity) {
        String role = userEntity.getRole();
        if (role == null || role.isEmpty()) {
            throw new RuntimeException("User does not have a role");
        }
        return new KnowledgeBaseUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public String getEmail() {
        return email;
    }
}
