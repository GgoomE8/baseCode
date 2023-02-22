package com.example.demo.controller.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardRequestDto {
    private String title;
    private String content;
}
