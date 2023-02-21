package com.example.demo.service;

import com.example.demo.controller.requestdto.BoardRequestDto;
import com.example.demo.controller.responsedto.BoardResponseDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
//    private final CommentRepository commentRepository;
//    private final CommentService commentService;
//    private final JwtUtil jwtUtil;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, HttpServletRequest request) {
//        String token = jwtUtil.resolveToken(request);
//        Claims claims;

        // 토큰이 있는 경우에만 게시판 추가
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
//                // 토큰에서 사용자 정보 가져옴
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("Token Error");
//            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Member member = memberRepository.findById(1L).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Board board = boardRepository.saveAndFlush(new Board(requestDto, member));

            return BoardResponseDto.from(board);
//        } else {
//            return null;
//        }
    }


    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판이 존재하지 않습니다.")
        );

//        String token = jwtUtil.resolveToken(request);
//        Claims claims;

        // 토큰이 있는 경우에만 게시판 추가
//        if (token != null) {
//            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져옴
//                claims = jwtUtil.getUserInfoFromToken(token);
//            } else {
//                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
//            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
//            Member member = memberRepository.findByUserId(claims.getSubject()).orElseThrow(
//                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
//            );

//            if (validCheck(board.getId(), user.getId(), user.getRole()))
//                board.update(requestDto);
//            else
//                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
//            return new BoardResponseDto(board);
//        } else {
//            return null;
//        }
        return BoardResponseDto.from(board);
    }


    public BoardResponseDto getBoardList() {
        return null;
    }
    public BoardResponseDto getBoard(Long id) {
        return null;
    }
    public BoardResponseDto deleteBoard(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        return null;
    }
}