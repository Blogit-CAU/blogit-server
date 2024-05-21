package com.blogit.blogitserver.service;

import com.blogit.blogitserver.config.jwt.JwtTokenProvider;
import com.blogit.blogitserver.dto.MemberLogInRequest;
import com.blogit.blogitserver.dto.MemberSignInRequest;
import com.blogit.blogitserver.entity.Member;
import com.blogit.blogitserver.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    public Long signup(MemberSignInRequest dto) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        Member memberEntity = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        return memberRepository.save(memberEntity).getId();
        // repository의 save 메서드 호출 (조건. entity 객체를 넘겨줘야 함)
    }

    /**
     * 로그인
     */
    @Transactional
    public Map<String, Object> login(MemberLogInRequest dto) {

        Optional<Member> optionalMember = memberRepository.findByName(dto.getMemberName());

        // name이 일치하는 Member가 없는 경우
        if (optionalMember.isEmpty()) {
            throw new AuthenticationException("이름 또는 비밀번호가 존재하지 않습니다.") {};
        }

        Member member = optionalMember.get();

        // password가 일치하지 않으면 null 반환
        if(!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new AuthenticationException("이름 또는 비밀번호가 일치하지 않습니다.") {};
        }


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getMemberName(), dto.getPassword());


        // authenticationToken 객체를 통해 Authentication 객체 생성
        // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("id", member.getId());
        return body;
    }

    /**
     * ID로 회원 조회
     */
    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }


    /**
     * 닉네임으로 회원 조회
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(memberName)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

        System.out.println("loadUserByUsername 유저 찾음: " + member);
        return member;
    }
}
