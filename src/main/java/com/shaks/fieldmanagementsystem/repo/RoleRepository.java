package com.shaks.fieldmanagementsystem.repo;


import com.shaks.fieldmanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
