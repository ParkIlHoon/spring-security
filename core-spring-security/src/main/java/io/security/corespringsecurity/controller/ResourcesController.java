package io.security.corespringsecurity.controller;

import io.security.corespringsecurity.security.metadatasource.UrlFilterInvocationSecurityMetaDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ResourcesController {

    private final UrlFilterInvocationSecurityMetaDataSource urlFilterInvocationSecurityMetaDataSource;

    @GetMapping("/admin/resources/reload")
    public String reloadResources() {
        urlFilterInvocationSecurityMetaDataSource.reload();
        return "";
    }

}
