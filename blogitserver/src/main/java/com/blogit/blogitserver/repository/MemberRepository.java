package com.blogit.blogitserver.repository;

import com.blogit.blogitserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
