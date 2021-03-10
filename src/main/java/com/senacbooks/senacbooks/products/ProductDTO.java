package com.senacbooks.senacbooks.products;

import com.senacbooks.senacbooks.categories.CategoryDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private Integer quantity;
    private Boolean status;
    private Double rating;
    private Double price;
    private String author;
    private String publisher;
    private Integer pages;
    private String size;
    private Integer year;
    private String edition;

    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO() {
    }

    public ProductDTO(
            Long id,
            String title,
            String description,
            Integer quantity,
            Boolean status,
            Double rating,
            Double price,
            String author,
            String publisher,
            Integer pages,
            String size,
            Integer year,
            String edition
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.rating = rating;
        this.price = price;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.size = size;
        this.year=year;
        this.edition = edition;
    }

    public ProductDTO(ProductEntity entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.quantity = entity.getQuantity();
        this.status = entity.getStatus();
        this.rating = entity.getRating();
        this.price = entity.getPrice();
        this.author = entity.getAuthor();
        this.publisher = entity.getPublisher();
        this.pages = entity.getPages();
        this.size = entity.getSize();
        this.year= entity.getYear();
        this.edition = entity.getEdition();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }
}
