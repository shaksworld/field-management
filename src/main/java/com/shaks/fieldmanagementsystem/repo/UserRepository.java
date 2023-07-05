package com.shaks.fieldmanagementsystem.repo;


import com.shaks.fieldmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);

    User findByUsername(String username);
}
