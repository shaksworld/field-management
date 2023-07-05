package com.shaks.fieldmanagementsystem.service;


import com.shaks.fieldmanagementsystem.entity.Role;

public interface RoleService {
    Role save(Role roleType);
    Role findByName(String name);
}
