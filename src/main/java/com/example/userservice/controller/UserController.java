package com.example.userservice.controller;

import com.example.userservice.dto.SignUpRequestDto;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    // 의존성 주입
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody SignUpRequestDto signUpRequestDto
    ) {
        try {
            userService.signUp(signUpRequestDto);
            log.info("회원가입 성공: {}", signUpRequestDto.getEmail());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("회원가입 실패: {}", signUpRequestDto.getEmail(), e);  // 예외 로깅
            return ResponseEntity.internalServerError().build();
        }
    }

}
