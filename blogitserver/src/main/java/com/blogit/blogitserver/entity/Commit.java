package com.blogit.blogitserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "commit")
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commit_id", nullable = false)
    private Long commitId;

    @Column(nullable = false)
    private String name;

    @Column(name = "repository_name", nullable = false)
    private String repositoryName;

    @Column(name = "commit_message", nullable = false)
    private String commitMessage;

}

