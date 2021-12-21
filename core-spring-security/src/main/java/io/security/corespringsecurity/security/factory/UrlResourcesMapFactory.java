package io.security.corespringsecurity.security.factory;

import io.security.corespringsecurity.service.ResourcesService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Setter
public class UrlResourcesMapFactory implements FactoryBean<Map<RequestMatcher, List<ConfigAttribute>>> {

    private ResourcesService resourcesService;
    private Map<RequestMatcher, List<ConfigAttribute>> resourceMap;

    @Override
    public Map<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
        if (resourceMap == null) {
            init();
        }
        return resourceMap;
    }

    private void init() {
        this.resourceMap = resourcesService.getActiveUrlResources();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
