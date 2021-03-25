package com.senacbooks.senacbooks.products.images;

import java.io.Serializable;

public class UriDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String imgUrl;
    private Boolean prinicpal;

    public UriDTO() {
    }

    public UriDTO(Long id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
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

    public Boolean getPrinicpal() {
        return prinicpal;
    }

    public void setPrinicpal(Boolean prinicpal) {
        this.prinicpal = prinicpal;
    }
}
