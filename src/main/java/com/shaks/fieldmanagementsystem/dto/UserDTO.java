package com.shaks.fieldmanagementsystem.dto;

import com.shaks.fieldmanagementsystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;


    private Collection<Role> roles = new ArrayList<>();
}
