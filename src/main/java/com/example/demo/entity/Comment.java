package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity(name="TB_COMMENT")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "i_comment", columnList = "comment_id"))
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column
    private String content;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Board board;
}
