package com.blogit.blogitserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommitRelationDTO {

    private Long articleId;
    private Long commitId;
}
