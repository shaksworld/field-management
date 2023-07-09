package com.shaks.fieldmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDTO {

    private String teamName;
    private String teamDescription;
    private Long teamMemberId;
}
