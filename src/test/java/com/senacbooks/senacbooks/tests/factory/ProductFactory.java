package com.senacbooks.senacbooks.tests.factory;

import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.products.images.ImageEntity;

public class ProductFactory {
  public static ProductEntity createProduct() {
    ProductEntity product = new ProductEntity(
      1L, 
      "Teste", 
      "Teste unitário", 
      356, 
      true, 
      3.56, 
      65.50, 
      "Autor teste", 
      "Editora teste",
      250, 
      "teste cm x teste cm", 
      2021, 
      "Teste edição"
    );

    product.getCategories().add(new CategoryEntity(1L, null, true));
    product.getImages().add(new ImageEntity(1L,null, true, true));

    return product;
  }

  public static ProductDTO createproductDTO() {
    ProductEntity product = createProduct();

    return new ProductDTO(product);
  }

  public static ProductDTO createproductDTO(Long id) {
    ProductDTO dto = createproductDTO();
    dto.setId(id);
    return dto;
  }
}
