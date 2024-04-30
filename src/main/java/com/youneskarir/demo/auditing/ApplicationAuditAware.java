package com.youneskarir.demo.auditing;

import com.youneskarir.demo.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    
    @Override
    public @NotNull Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        
        if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) 
            return Optional.empty();

        User user = (User) authentication.getPrincipal();
        return Optional.ofNullable(user.getId());        
    }
    
    
    
}
