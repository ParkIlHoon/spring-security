package io.security.corespringsecurity.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountAjaxLoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
