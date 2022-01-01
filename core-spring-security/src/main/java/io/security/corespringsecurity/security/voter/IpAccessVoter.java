package io.security.corespringsecurity.security.voter;

import io.security.corespringsecurity.service.ResourcesService;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IpAccessVoter implements AccessDecisionVoter<Object> {

    private final ResourcesService resourcesService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = authenticationDetails.getRemoteAddress();
        List<String> accessIpList = resourcesService.getAccessIpList();
        int result = ACCESS_DENIED;

        for (String accessIp : accessIpList) {
            if (remoteAddress.equals(accessIp)) {
                result = ACCESS_ABSTAIN;
                break;
            }
        }

        if (result == ACCESS_DENIED) {
            throw new AccessDeniedException("Invalid IP Address");
        }
        return result;
    }
}
