package com.senacbooks.senacbooks.roles;

import java.io.Serializable;

public class RolesDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String authority;

    public RolesDTO() {
    }
    
    public RolesDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RolesDTO(RolesEntity entity) {
        id = entity.getId();
        authority = entity.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    
}
