package com.shaks.fieldmanagementsystem.service.impl;

import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;
import com.shaks.fieldmanagementsystem.entity.Team;
import com.shaks.fieldmanagementsystem.entity.User;
import com.shaks.fieldmanagementsystem.exception.CustomException;
import com.shaks.fieldmanagementsystem.repo.TeamRepository;
import com.shaks.fieldmanagementsystem.repo.UserRepository;
import com.shaks.fieldmanagementsystem.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public TeamResponseDto createTeam(TeamRequestDTO teamRequestDto) {
        if (teamAlreadyExist(teamRequestDto.getName())) {
            throw new CustomException("Team name already exist", HttpStatus.BAD_REQUEST);
        }
        try {
            Team team = Team.builder()
                    .name(teamRequestDto.getName())
                    .teamDescription(teamRequestDto.getTeamDescription())
                    .build();
            log.info("this is the team name: {} ", teamRequestDto.getName());
            teamRepository.save(team);
            return modelMapper.map(team, TeamResponseDto.class);
        } catch (Exception e) {
            log.error("Error while creating team", e);
            throw new RuntimeException("Error while creating team");
        }
    }

    @Override
    public Team addMember(Long userId, Long teamId) {
        //check is user exist
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (userOptional.isEmpty()) {
            throw new CustomException("User does not exist", HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();

        if (teamOptional.isEmpty()) {
            throw new CustomException("team does not exist", HttpStatus.BAD_REQUEST);
        }
        Team team = teamOptional.get();
        try {
            log.info("the team is : {} and the user : {}", team, user);
            team.getUsers().add(user);
            return teamRepository.save(team);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CustomException("Error occur while adding member to the team", HttpStatus.INTERNAL_SERVER_ERROR);
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
