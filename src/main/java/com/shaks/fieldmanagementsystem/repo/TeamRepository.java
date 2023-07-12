package com.shaks.fieldmanagementsystem.repo;

import com.shaks.fieldmanagementsystem.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name);

}
