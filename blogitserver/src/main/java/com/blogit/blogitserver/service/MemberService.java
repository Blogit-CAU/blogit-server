package com.blogit.blogitserver.service;

import com.blogit.blogitserver.dto.MemberDTO;
import com.blogit.blogitserver.entity.Member;
import com.blogit.blogitserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public Long signup(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        Member memberEntity = memberDTO.toEntity(passwordEncoder.encode(memberDTO.getPassword()));
        return memberRepository.save(memberEntity).getId();
        // repository의 save 메서드 호출 (조건. entity 객체를 넘겨줘야 함)
    }
}
