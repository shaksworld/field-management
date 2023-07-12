package com.shaks.fieldmanagementsystem.dto;

import com.shaks.fieldmanagementsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDTO {

    private String name;
    private String teamDescription;

}
