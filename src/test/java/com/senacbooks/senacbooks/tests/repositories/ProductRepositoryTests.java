package com.senacbooks.senacbooks.tests.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.products.ProductRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
public class ProductRepositoryTests {

  @Autowired
  private ProductRepository repository;

  private long countBooksWithAdminitrationName;
  private PageRequest pageRequest;
  private long countTotalProducts;
  private long countCategory3Products;

  @BeforeEach
  void setUp() throws Exception {
    countBooksWithAdminitrationName=5L;
    pageRequest = PageRequest.of(0,10);
    countTotalProducts = 256L;
    countCategory3Products=1L;
  }

  @Test
  public void findShouldReturnProductsWhenNameExists(){
    String title = "Administração";

    Page<ProductEntity> result = repository.find(null, title, pageRequest);

    Assertions.assertFalse(result.isEmpty());
    Assertions.assertEquals(countBooksWithAdminitrationName, result.getTotalElements());
  }

  @Test
  public void findShouldReturnAllProductsWhenNameIsEmpty(){
    String title = "";

    Page<ProductEntity> result = repository.find(null, title, pageRequest);

    Assertions.assertFalse(result.isEmpty());
    Assertions.assertEquals(countTotalProducts, result.getTotalElements());
  }

  @Test
  public void findShouldReturnOnlySelectCategoryWhenCategoryIsInformed(){
    List<CategoryEntity> categories = new ArrayList<>();
    categories.add(new CategoryEntity(3L, null, true));

    Page<ProductEntity> result = repository.find(categories, "", pageRequest);

    Assertions.assertFalse(result.isEmpty());
    Assertions.assertEquals(countCategory3Products, result.getTotalElements());
  }
}
