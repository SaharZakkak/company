package com.webservices.company.service;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityAuditorAwareService implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(securityContext -> securityContext.getAuthentication())
                .map(authentication -> authentication.getPrincipal())
                .filter(principal -> principal instanceof UserDetails)
                .map(principal -> (UserDetails) principal)
                .map(userDetails -> userDetails.getUsername());

    }
}
