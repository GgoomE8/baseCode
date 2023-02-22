package com.example.demo.controller.responsedto;

import com.example.demo.entity.Board;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String username;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .username(board.getMember().getUsername())
                .build();
    }

    public static List<BoardResponseDto> from(Collection<Board> entities) {
        return entities.stream().map(BoardResponseDto::from).collect(Collectors.toList());
    }
}
