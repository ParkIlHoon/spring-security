package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.Resources;
import io.security.corespringsecurity.repository.ResourcesRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResourcesService {

    private final ResourcesRepository resourcesRepository;

    public Map<RequestMatcher, List<ConfigAttribute>> getActiveUrlResources() {
        Map<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findActiveUrlResources();
        resourcesList.forEach(r -> result.putAll(r.getResourcesMap()));
        return result;
    }
}
