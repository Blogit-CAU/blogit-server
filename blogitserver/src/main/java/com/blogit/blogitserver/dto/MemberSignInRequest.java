package com.blogit.blogitserver.dto;

import com.blogit.blogitserver.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@Data
@NoArgsConstructor
@AllArgsConstructor //필드를 매개변수로 하는 생성자 생성
@ToString //DTO 객체가 가지고 있는 필드값 출력
public class MemberSignInRequest {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;


    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(this.email)
                .memberName(this.memberName)
                .password(encodedPassword)
                .build();
    }
}
