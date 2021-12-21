package io.security.corespringsecurity.domain;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Entity
@Getter
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String httpMethod;

    private int orderNum;

    private String type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_resources", joinColumns = {
        @JoinColumn(name = "resources_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "role_id")
    })
    private Set<Role> roles = new HashSet<>();

    public Map<RequestMatcher, List<ConfigAttribute>> getResourcesMap() {
        Map<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        result.put(new AntPathRequestMatcher(this.name), this.roles.stream().map(Role::getName).map(SecurityConfig::new).collect(Collectors.toList()));
        return result;
    }
}
