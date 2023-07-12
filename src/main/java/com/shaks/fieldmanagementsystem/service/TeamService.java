package com.shaks.fieldmanagementsystem.service;

import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;
import com.shaks.fieldmanagementsystem.entity.Team;

public interface TeamService {
    TeamResponseDto createTeam(TeamRequestDTO teamRequestDto);

    Team addMember(Long userId, Long teamId);

}
