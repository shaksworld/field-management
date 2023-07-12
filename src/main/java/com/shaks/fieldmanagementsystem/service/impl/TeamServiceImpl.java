package com.shaks.fieldmanagementsystem.service.impl;

import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;
import com.shaks.fieldmanagementsystem.entity.Team;
import com.shaks.fieldmanagementsystem.exceptions.CustomException;
import com.shaks.fieldmanagementsystem.repo.TeamRepository;
import com.shaks.fieldmanagementsystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final ModelMapper modelMapper;

    @Override
    public TeamResponseDto createTeam(TeamRequestDTO teamRequestDto) {
        try {
            if (teamAlreadyExist(teamRequestDto.getName())) {
                throw new CustomException("Team name already exist", HttpStatus.BAD_REQUEST);
            }
            Team team = Team.builder()
                    .name(teamRequestDto.getName())
                    .teamDescription(teamRequestDto.getTeamDescription())
                    .build();
            teamRepository.save(team);
            return modelMapper.map(team, TeamResponseDto.class);
        } catch (Exception e) {
            log.error("Error while creating team", e);
            throw new RuntimeException("Error while creating team");
        }
    }

    private Boolean teamAlreadyExist(String team) {
        Team found = teamRepository.findByName(team);
        if (found != null && found.getName().equalsIgnoreCase(team)) {
            return true;
        } else {
            return false;
        }
    }
}
