package org.example.utils;

import org.springframework.security.core.GrantedAuthority;

/**
 * Roles for user
 */
public enum Role implements GrantedAuthority {
    USER, SERVICEMAN, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
