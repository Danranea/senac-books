package com.senacbooks.senacbooks.users;

import com.senacbooks.senacbooks.categories.CategoryDTO;
import com.senacbooks.senacbooks.roles.RoleDTO;
import com.senacbooks.senacbooks.roles.RoleEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String userName;
    private Boolean status;

    private List<RoleDTO> roles = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String userName, Boolean status) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.status = status;
    }

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.userName = entity.getUserName();
        this.status = entity.getStatus();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }
}
