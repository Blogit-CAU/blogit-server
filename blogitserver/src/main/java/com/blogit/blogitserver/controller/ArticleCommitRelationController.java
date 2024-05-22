package com.blogit.blogitserver.controller;

import com.blogit.blogitserver.dto.ArticleCommitRelationDTO;
import com.blogit.blogitserver.entity.ArticleCommitRelation;
import com.blogit.blogitserver.service.ArticleCommitRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relations")
@RequiredArgsConstructor
public class ArticleCommitRelationController {

    private final ArticleCommitRelationService relationService;

    @PostMapping("/save")
    public ResponseEntity<ArticleCommitRelation> saveRelation(@RequestBody ArticleCommitRelationDTO relationDTO) {
        ArticleCommitRelation savedRelation = relationService.saveRelation(relationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRelation);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleCommitRelation>> getAllRelations() {
        List<ArticleCommitRelation> relations = relationService.getAllRelations();
        return ResponseEntity.ok(relations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleCommitRelation> getRelationById(@PathVariable Long id) {
        ArticleCommitRelation relation = relationService.getRelationById(id);
        return ResponseEntity.ok(relation);
    }
}


