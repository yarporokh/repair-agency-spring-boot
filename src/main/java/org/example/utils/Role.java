package org.example.utils;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, SERVICEMAN, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
