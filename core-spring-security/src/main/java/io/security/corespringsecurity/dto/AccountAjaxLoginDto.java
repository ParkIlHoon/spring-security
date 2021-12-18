package io.security.corespringsecurity.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountAjaxLoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
