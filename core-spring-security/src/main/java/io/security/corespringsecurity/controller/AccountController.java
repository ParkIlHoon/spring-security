package io.security.corespringsecurity.controller;

import io.security.corespringsecurity.dto.AccountCreateDto;
import io.security.corespringsecurity.service.AccountService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public String registerPage() {
        return "account/register";
    }

    @PostMapping
    public String createAccount(@Valid AccountCreateDto accountCreateDto) {
        accountService.create(accountCreateDto);
        return "redirect:/";
    }
}
