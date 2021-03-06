package com.senacbooks.senacbooks.roles;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class RoleDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    @NotBlank(message = "campo obrigatório")
    private String authority;

    public RoleDTO() {
    }
    
    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(RoleEntity entity) {
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
