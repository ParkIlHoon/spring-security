package io.security.corespringsecurity.service;

import io.security.corespringsecurity.domain.RoleHierarchy;
import io.security.corespringsecurity.repository.RoleHierarchyRepository;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleHierarchyService {

    private final RoleHierarchyRepository roleHierarchyRepository;

    public String findAllHierarchy() {
        List<RoleHierarchy> hierarchies = roleHierarchyRepository.findAll();
        Iterator<RoleHierarchy> iterator = hierarchies.iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            RoleHierarchy roleHierarchy = iterator.next();
            if (roleHierarchy.getParentName() != null) {
                builder.append(roleHierarchy.getParentName().getChildName());
                builder.append(" > ");
                builder.append(roleHierarchy.getChildName());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
