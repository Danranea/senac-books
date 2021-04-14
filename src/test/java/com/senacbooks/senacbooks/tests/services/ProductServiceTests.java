package com.senacbooks.senacbooks.tests.services;

import java.util.Optional;

import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.products.ProductRepository;
import com.senacbooks.senacbooks.products.ProductService;
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

  private long existingId;
  private long nonExistingId;
  private ProductEntity product;

  @BeforeEach
  void setUp() throws Exception {
    existingId=1L;
    nonExistingId=33245L;
    product = ProductFactory.createProduct();

    Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));
    Mockito.when(repository.save(product)).thenReturn(product);
    Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).findById(nonExistingId);
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
  
}
