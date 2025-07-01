package com.task.knowledgebase.repository;

import com.task.knowledgebase.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    Integer findIdByUsername(String username);
}
