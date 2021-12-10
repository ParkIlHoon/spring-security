package io.security.corespringsecurity.controller;

import io.security.corespringsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final AccountService accountService;

    @GetMapping
    public String myPage() {
        return "account/mypage";
    }
}
