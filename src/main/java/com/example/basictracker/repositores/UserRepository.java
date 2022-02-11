package com.example.basictracker.repositores;

import com.example.basictracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserName(String userName);

    User findByUUID(long UUID);
}
