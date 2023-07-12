package com.shaks.fieldmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "team")
@Getter
@Setter
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String teamDescription;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<User> users;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private User teamLeader;

}
