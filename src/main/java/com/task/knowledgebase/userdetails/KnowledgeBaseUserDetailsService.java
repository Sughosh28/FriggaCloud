package com.task.knowledgebase.userdetails;

import com.task.knowledgebase.models.UserEntity;
import com.task.knowledgebase.repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KnowledgeBaseUserDetailsService implements UserDetailsService {
    private final UserRepo userRepository;

    public KnowledgeBaseUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String role = userEntity.getRole();
        if (role == null || role.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new KnowledgeBaseUserDetails(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
        );
    }
}