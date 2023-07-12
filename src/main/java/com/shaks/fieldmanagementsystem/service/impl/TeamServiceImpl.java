package com.shaks.fieldmanagementsystem.service.impl;

import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;
import com.shaks.fieldmanagementsystem.entity.Team;
import com.shaks.fieldmanagementsystem.repo.TeamRepository;
import com.shaks.fieldmanagementsystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public TeamResponseDto createTeam(TeamRequestDTO teamRequestDto) {
//        try{
//            Team team = teamRepository.fiindByName(teamRequestDto.getName());
//            if (existName(team)){
//                throw new
//            }
//        } catch (Exception e){
//            log.error("Error while creating team", e);
//            throw new RuntimeException("Error while creating team");
//        }
        return null;
    }
}
