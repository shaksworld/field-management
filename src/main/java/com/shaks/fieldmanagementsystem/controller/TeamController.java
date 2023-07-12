package com.shaks.fieldmanagementsystem.controller;

import com.shaks.fieldmanagementsystem.constants.ResponseStatus;
import com.shaks.fieldmanagementsystem.dto.APIResponse;
import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;
import com.shaks.fieldmanagementsystem.entity.Team;
import com.shaks.fieldmanagementsystem.repo.UserRepository;
import com.shaks.fieldmanagementsystem.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.shaks.fieldmanagementsystem.util.ResponseConstants.SUCCESS;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<APIResponse<TeamResponseDto>> creatTeam (@Valid @RequestBody TeamRequestDTO teamRequestDto){
        TeamResponseDto createdTeam = teamService.createTeam(teamRequestDto);
        APIResponse<TeamResponseDto> build = APIResponse.<TeamResponseDto>builder()
                .status(SUCCESS)
                .results(createdTeam)
                .build();
        return ResponseEntity.ok(build);
    }

    @PostMapping("/{teamId}/user/{userId}")
    public ResponseEntity<APIResponse> addMember(@PathVariable("teamId") Long userId, @PathVariable("userId") Long teamId){
       Team teamCreated =  teamService.addMember(teamId, teamId);
       return ResponseEntity.ok(APIResponse.builder().status(SUCCESS).results(teamCreated).build()) ;
    }
}
