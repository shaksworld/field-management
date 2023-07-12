package com.shaks.fieldmanagementsystem.service;

import com.shaks.fieldmanagementsystem.dto.SignupDTO;
import com.shaks.fieldmanagementsystem.dto.UserDTO;
import com.shaks.fieldmanagementsystem.entity.User;

import java.util.List;

public interface UserService {



    UserDTO save(SignupDTO user);

    UserDTO addRoleToUser(String username, String roleName);

    UserDTO findByUsername(String username);

    List<UserDTO> findAll();

    UserDTO updateUser(Long userId, User user);

//    void saveVerificationTokenForUser(String token, User user);

//    VerificationToken generateNewVerificationToken(String oldToken);

//    String validateVerificationToken(String token);

//    void generatePasswordResetToken(User user, String newToken);

//    User findUserByEmail(String email);

//    String validatePasswordResetToken(String token);

//    Optional<User> getUserByPasswordResetToken(String token);
//
//    void changePassword(User user, String newPassword);
//
//    boolean checkIfValidOldPassword(User user, String oldPassword);


//    UserResponseDto getCurrentUser(String email);

//    UserDTO updateUser(UserDTO userDTO, String currentUserEmail);
}
