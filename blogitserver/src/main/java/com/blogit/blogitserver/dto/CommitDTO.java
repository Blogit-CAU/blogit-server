package com.blogit.blogitserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitDTO {

    private Long commitId;
    private String name;
    private String repositoryName;
    private String commitMessage;
}
