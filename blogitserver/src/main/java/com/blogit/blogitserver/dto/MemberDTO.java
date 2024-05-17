package com.blogit.blogitserver.dto;

import com.blogit.blogitserver.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //필드를 매개변수로 하는 생성자 생성
@ToString //DTO 객체가 가지고 있는 필드값 출력
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String memberName;

//    public static Member toMemberEntity(MemberDTO memberDTO) {
//        Member member = new Member();
//        member.setEmail(memberDTO.getEmail());
//        member.setPassword(memberDTO.getPassword());
//        member.setMemberName(memberDTO.getMemberName());
//        return member;
//    }

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(this.email)
                .memberName(this.memberName)
                .password(encodedPassword)
                .build();
    }
}
