package com.blogit.blogitserver.controller;

import com.blogit.blogitserver.dto.CommitDTO;
import com.blogit.blogitserver.entity.Commit;
import com.blogit.blogitserver.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commits")
@RequiredArgsConstructor
public class CommitController {

    private final CommitService commitService;

    @PostMapping("/save")
    public ResponseEntity<Commit> saveCommit(@RequestBody CommitDTO commitDTO) {
        Commit commit = new Commit();
        // DTO에서 엔터티로 매핑
        commit.setName(commitDTO.getName());
        commit.setRepositoryName(commitDTO.getRepositoryName());
        commit.setCommitMessage(commitDTO.getCommitMessage());
        // 서비스를 통해 저장
        Commit savedCommit = commitService.saveCommit(commit);
        return ResponseEntity.ok(savedCommit);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Commit>> getAllCommits() {
        List<Commit> commits = commitService.getAllCommits();
        return ResponseEntity.ok(commits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commit> getCommitById(@PathVariable Long id) {
        Commit commit = commitService.getCommitById(id);
        return ResponseEntity.ok(commit);
    }

}
