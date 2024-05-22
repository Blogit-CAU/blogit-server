//package com.blogit.blogitserver.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "commit")
//public class Commit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String repositoryName;
//
//    @Column(nullable = false)
//    private String commitMessage;
//
//    // 다대다 관계를 위한 매핑
//    @ManyToMany(mappedBy = "commits")
//    private List<Article> articles = new ArrayList<>();
//
//    // 생성자, getter, setter, 등등
//}
