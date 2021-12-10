package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.Account;
import io.security.corespringsecurity.dto.AccountCreateDto;

public interface AccountService {

    Account create(AccountCreateDto accountCreateDto);

}
