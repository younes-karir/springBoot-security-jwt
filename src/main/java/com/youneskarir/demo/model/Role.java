package com.youneskarir.demo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Getter
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_CREATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_UPDATE,
            Permission.MANAGER_READ,
            Permission.MANAGER_CREATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_UPDATE
    )),
    MANAGER(Set.of(
            Permission.MANAGER_READ,
            Permission.MANAGER_CREATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_UPDATE
    ));
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAthAuthorities(){
        var authorities = new java.util.ArrayList<>(getPermissions().stream().map(item -> new SimpleGrantedAuthority(item.getPermission())).toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return  authorities;
    }
}
