package com.example.demo.entity;

import com.example.demo.controller.requestdto.BoardRequestDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity(name="TB_BOARD")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "i_board", columnList = "board_id"))
public class Board extends BaseTimeEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column
    @NonNull
    private String title;
    @Column
    @NonNull
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true) //CaseCade (삭제시 같이 삭제)
//    private List<Comment> comments;

    public Board(BoardRequestDto requestDto, Member member) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.member = member;
    }

    public void update(BoardRequestDto dto) {
        this.title = dto.getTitle();
        this.title = dto.getTitle();
        this.title = dto.getTitle();
    }
}
