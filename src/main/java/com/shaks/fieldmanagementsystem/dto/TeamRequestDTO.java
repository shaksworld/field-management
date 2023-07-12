package com.shaks.fieldmanagementsystem.dto;

import com.shaks.fieldmanagementsystem.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TeamRequestDTO {

    private String name;
    private String teamDescription;

}
