package com.senacbooks.senacbooks.products.images;

import com.senacbooks.senacbooks.products.ProductDTO;

import java.io.Serializable;
import java.util.Objects;


public class ImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String imgUrl;
    private Boolean principal;
    private Boolean status;

    private ProductDTO product;

    public ImageDTO() {
    }

    public ImageDTO(Long id, String imgUrl, Boolean principal, Boolean status) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.principal = principal;
        this.status = status;
    }

    public ImageDTO(ImageEntity entity){
        this.id = entity.getId();
        this.imgUrl = entity.getImgUrl();
        this.principal = entity.getPrincipal();
        this.status = entity.getStatus();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO that = (ImageDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
