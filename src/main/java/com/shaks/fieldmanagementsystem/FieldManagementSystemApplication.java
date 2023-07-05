package com.shaks.fieldmanagementsystem;

import com.shaks.fieldmanagementsystem.entity.Role;
import com.shaks.fieldmanagementsystem.enums.RoleType;
import com.shaks.fieldmanagementsystem.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FieldManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldManagementSystemApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(RoleService roleService) {
        return args -> {
            Role role = new Role(null, RoleType.ROLE_USER.toString());
            roleService.save(role);

            Role auditor = new Role(null, RoleType.ROLE_AUDITOR.toString());
            roleService.save(auditor);

            Role teamLead = new Role(null, RoleType.ROLE_TEAMLEAD.toString());
            roleService.save(teamLead);

        };
    }

}
