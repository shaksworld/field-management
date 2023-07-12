package com.shaks.fieldmanagementsystem.controller;

import com.shaks.fieldmanagementsystem.dto.APIResponse;
import com.shaks.fieldmanagementsystem.dto.TeamRequestDTO;
import com.shaks.fieldmanagementsystem.dto.TeamResponseDto;
import com.shaks.fieldmanagementsystem.repo.UserRepository;
import com.shaks.fieldmanagementsystem.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.shaks.fieldmanagementsystem.util.ResponseConstants.SUCCESS;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<APIResponse<TeamResponseDto>> creatTeam (@Valid TeamRequestDTO teamRequestDto){
        TeamResponseDto teamResponseDto = teamService.createTeam(teamRequestDto);
        APIResponse<TeamResponseDto> build = APIResponse.<TeamResponseDto>builder()
                .status(SUCCESS)
                .results(teamResponseDto)
                .build();
        return ResponseEntity.ok(build);
    }
}
