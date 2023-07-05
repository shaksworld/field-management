package com.shaks.fieldmanagementsystem.service.impl;
import com.shaks.fieldmanagementsystem.entity.Role;
import com.shaks.fieldmanagementsystem.repo.RoleRepository;
import com.shaks.fieldmanagementsystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }



    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
