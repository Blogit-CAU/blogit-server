package com.blogit.blogitserver.service;

import com.blogit.blogitserver.entity.Commit;
import com.blogit.blogitserver.repository.CommitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommitService {

    private final CommitRepository commitRepository;

    @Transactional
    public Commit saveCommit(Commit commit) {
        return commitRepository.save(commit);
    }

    public List<Commit> getAllCommits() {
        return commitRepository.findAll();
    }

    public Commit getCommitById(Long id) {
        return commitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commit not found with id: " + id));
    }

}
