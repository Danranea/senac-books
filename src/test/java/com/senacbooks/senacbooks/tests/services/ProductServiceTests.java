package com.senacbooks.senacbooks.tests.services;

import java.util.Optional;

import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.categories.CategoryRepository;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.products.ProductRepository;
import com.senacbooks.senacbooks.products.ProductService;
import com.senacbooks.senacbooks.products.images.ImageEntity;
import com.senacbooks.senacbooks.products.images.ImageRepository;
import com.senacbooks.senacbooks.products.images.ImageService;
import com.senacbooks.senacbooks.tests.factory.ProductFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Como é um teste unitário dos services, e os mesmos dependem do repository,
 * devemos mocar o repository para simular seus comportamentos.
 * 
 */
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

  //objeto prinicpal a ser testado = ProductService
  @InjectMocks
  private ProductService service;

  //Objeto que iremos simular o comportamento
  @Mock
  private ProductRepository repository;

  @Mock
  private ImageRepository imageRepository;

  @Mock
  private ImageService imageService;

  @Mock
  private CategoryRepository categoryRepository;

  private long existingId;
  private long nonExistingId;
  private ProductEntity product;
  private long imageId;

  @BeforeEach
  void setUp() throws Exception {
    existingId=1L;
    nonExistingId=33245L;
    product = ProductFactory.createProduct();
    imageId=1L;

    Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
    Mockito.when(repository.save(product)).thenReturn(product);
    Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).findById(nonExistingId);
    Mockito.when(repository.getOne(existingId)).thenReturn(product);
    Mockito.when(imageRepository.getOne(existingId)).thenReturn(new ImageEntity(1L, "image.teste.com", true, true));
    Mockito.when(categoryRepository.getOne(existingId)).thenReturn(new CategoryEntity(1L, "teste_category", true));
    Mockito.when(imageService.getImage(imageId)).thenReturn(new ImageEntity(1L, "image.teste.com", true, true));
  }

  @Test
  public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists(){
    Assertions.assertThrows(EmptyResultDataAccessException.class, ()->{
      service.delete(nonExistingId);
    });

    Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
  }

  @Test
  public void deleteShouldTurnStatusToFalseWhenIdExistsAndStatusIsTrue(){
    String result = service.delete(existingId);

    Assertions.assertEquals("Livro " + product.getTitle() + " inativado com sucesso.", result);

    Mockito.verify(repository, Mockito.times(1)).findById(existingId);
    Mockito.verify(repository, Mockito.times(1)).save(product);
  }

  @Test
  public void deleteShouldTurnStatusToTrueWhenIdExistsAndStatusIsFalse(){
    product.setStatus(false);
    String result = service.delete(existingId);

    Assertions.assertEquals("Livro " + product.getTitle() + " reativado com sucesso.", result);

    Mockito.verify(repository, Mockito.times(1)).findById(existingId);
    Mockito.verify(repository, Mockito.times(1)).save(product);
  }

  @Test
  public void findByIdShouldReturnProductWhenIdExists(){

    ProductDTO result = service.findById(existingId);

    Assertions.assertEquals(existingId, result.getId());
    Mockito.verify(repository, Mockito.times(1)).findById(existingId);
  }

  @Test
  public void findByIdShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists(){

    Assertions.assertThrows(EmptyResultDataAccessException.class, ()->{
      service.findById(nonExistingId);
    });

    Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
  }

  @Test
  public void updateShouldReturnProductWhenIdExists(){
    ProductDTO dto = ProductFactory.createproductDTO();

    ProductDTO result = service.update(existingId, dto);

    Assertions.assertEquals(result.getTitle(), dto.getTitle());
    Mockito.verify(repository, Mockito.times(1)).getOne(existingId);
    Mockito.verify(imageRepository, Mockito.times(1)).getOne(imageId);
    Mockito.verify(imageService, Mockito.times(1)).getImage(imageId);
    Mockito.verify(categoryRepository, Mockito.times(1)).getOne(existingId);

  }
  
}
