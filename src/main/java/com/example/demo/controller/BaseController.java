package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Slf4j
public class BaseController {

    @GetMapping({"/","/index"})
    public ModelAndView home() {
       log.info("경로 찾기 /");
        return new ModelAndView("index");
    }

    @GetMapping("/error/400")
    public ModelAndView error400() {
        log.info("경로 찾기 error/400");
        return new ModelAndView("error400");
    }

    @GetMapping("/error/404")
    public ModelAndView error404() {
        return new ModelAndView("error404");
    }

    @GetMapping("/error/500")
    public ModelAndView error500() {
        return new ModelAndView("error500");
    }

    @RequestMapping("/forbidden")
    public ResponseEntity<?> getForbidden() {
//        Message message = new Message(HttpStatus.FORBIDDEN.value(), "로그인을 안했거나 권한이 없습니다.", null);
//
//        HttpHeaders headers= new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
