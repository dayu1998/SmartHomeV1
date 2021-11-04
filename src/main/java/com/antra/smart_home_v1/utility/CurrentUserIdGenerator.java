package com.antra.smart_home_v1.utility;

import com.antra.smart_home_v1.config.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserIdGenerator {
    public static Integer generate(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal up = (UserPrincipal) auth.getPrincipal();
        Long userId = up.getId();
        return userId.intValue();
    }
}
