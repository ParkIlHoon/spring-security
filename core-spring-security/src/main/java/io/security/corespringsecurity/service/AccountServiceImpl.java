package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.dto.AccountCreateDto;
import io.security.corespringsecurity.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Account create(AccountCreateDto accountCreateDto) {
        return accountRepository.save(Account.of(accountCreateDto.getUsername(),
            passwordEncoder.encode(accountCreateDto.getPassword()),
            accountCreateDto.getEmail(),
            accountCreateDto.getAge()));
    }
}
