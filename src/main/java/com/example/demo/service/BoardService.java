package com.example.demo.service;

import com.example.demo.controller.requestdto.BoardRequestDto;
import com.example.demo.controller.responsedto.BoardResponseDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Member;
import com.example.demo.enums.UserRoleEnum;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto, Member member) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Board board = boardRepository.saveAndFlush(new Board(requestDto, member));

        return BoardResponseDto.from(board);
    }


    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시판이 존재하지 않습니다.")
        );

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 추가
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                 // 토큰에서 사용자 정보 가져옴
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            if (validCheck(board.getMember().getId(), member.getId(), member.getRole()))
                board.update(requestDto);
            else
                throw new IllegalArgumentException("작성자만 삭제/수정할 수 있습니다.");
            return BoardResponseDto.from(board);
        } else {
            return null;
        }
    }

    private boolean validCheck(Long id, Long id1, UserRoleEnum role) {
        if((id.equals(id1) && role.equals(UserRoleEnum.USER)) || role.equals(UserRoleEnum.ADMIN)) {
            return true;
        } else
            return false;
    }


    public List<BoardResponseDto> getBoardList() {
        List<Board> list = boardRepository.findAllByOrderByIdDesc();
        return BoardResponseDto.from(list);
    }
    public BoardResponseDto getBoard(Long id) {
        Optional<Board> result = boardRepository.findById(id);
        if(result.isPresent())
            return BoardResponseDto.from(result.get());
        else
            return null;
    }
    public Long deleteBoard(Long id, HttpServletRequest request) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new NullPointerException("게시물을 찾을 수 없습니다."));
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시판 추가
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져옴
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            if (validCheck(board.getMember().getId(), member.getId(), member.getRole()))
                boardRepository.deleteById(id);
            else
                throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
            return id;
        } else {
            return null;
        }
    }
}
