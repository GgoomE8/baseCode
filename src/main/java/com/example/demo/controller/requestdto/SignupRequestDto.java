package com.example.demo.controller.requestdto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디는 소문자랑 숫자만 가능합니다.")
    @Size(min=4, max=10, message = "4 ~ 10길이의 소문자, 숫자만 가능합니다.")
    private String username;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,15}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 15자의 비밀번호여야 합니다.")
    private String password;
    @Email
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}
