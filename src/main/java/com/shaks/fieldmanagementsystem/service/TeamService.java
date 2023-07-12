package com.shaks.fieldmanagementsystem.service;

import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;

public interface TeamService {
    TeamResponseDto createTeam(TeamRequestDTO teamRequestDto);
}
