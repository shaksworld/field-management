package com.shaks.fieldmanagementsystem.service.impl;

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
}
