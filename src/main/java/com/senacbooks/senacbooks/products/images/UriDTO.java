package com.senacbooks.senacbooks.products.images;

import java.io.Serializable;

public class UriDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String imgUrl;
    private Boolean principal;
    private Boolean status;

    public UriDTO() {
    }

    public UriDTO(Long id, String imgUrl, Boolean principal,  Boolean status) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.principal = principal;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
