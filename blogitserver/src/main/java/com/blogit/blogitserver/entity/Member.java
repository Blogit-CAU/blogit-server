package com.blogit.blogitserver.entity;

import com.blogit.blogitserver.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member")
public class Member {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String memberName;

    @Builder
    public Member(String email, String memberName, String password) {
        this.email = email;
        this.memberName = memberName;
        this.password = password;
    }

}
