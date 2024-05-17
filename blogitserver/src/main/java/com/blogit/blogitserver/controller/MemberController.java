package com.blogit.blogitserver.controller;

import com.blogit.blogitserver.dto.MemberDTO;
import com.blogit.blogitserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody MemberDTO request) {
        Long id = memberService.signup(request);
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.created(null).body(response);
    }

}
