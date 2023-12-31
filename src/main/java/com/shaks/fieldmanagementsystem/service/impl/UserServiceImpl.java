package com.shaks.fieldmanagementsystem.service.impl;

import com.shaks.fieldmanagementsystem.dto.SignupDTO;
import com.shaks.fieldmanagementsystem.dto.UserDTO;
import com.shaks.fieldmanagementsystem.entity.Role;
import com.shaks.fieldmanagementsystem.entity.User;
import com.shaks.fieldmanagementsystem.enums.RoleType;
import com.shaks.fieldmanagementsystem.repo.RoleRepository;
import com.shaks.fieldmanagementsystem.repo.UserRepository;
import com.shaks.fieldmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public UserDTO save(SignupDTO signupDTO) {
        log.info("Saving user {}", signupDTO.getUsername());
        signupDTO.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        User user = mapper.map(signupDTO, User.class);
        Role role = roleRepository.findByName(String.valueOf(RoleType.ROLE_USER));
        user.setRoles(List.of(role));

        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO addRoleToUser(String username, String roleName) {
        log.info("Assign role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        return mapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            String message = String.format("User %s not found", username);
            log.error(message);
            throw new UsernameNotFoundException(message);
        } else {
            log.debug("User {} found", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    @Override
    public UserDTO findByUsername(String username) {
        return mapper.map(userRepository.findByUsername(username), UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return mapper.map(userRepository.findAll(), List.class);
    }

    @Override
    public UserDTO updateUser(Long userId, User user) {
        return null;
    }
}
