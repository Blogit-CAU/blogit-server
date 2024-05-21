package com.blogit.blogitserver.dto;

import com.blogit.blogitserver.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberLogInRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String memberName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .memberName(this.memberName)
                .password(encodedPassword)
                .build();
    }

}