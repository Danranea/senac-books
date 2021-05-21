package com.senacbooks.senacbooks.tests.services;

import com.senacbooks.senacbooks.categories.CategoryDTO;
import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.categories.CategoryRepository;
import com.senacbooks.senacbooks.categories.CategoryService;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.tests.factory.CategoryFactory;
import com.senacbooks.senacbooks.tests.factory.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Como é um teste unitário dos services, e os mesmos dependem do repository,
 * devemos mocar o repository para simular seus comportamentos.
 */
@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {

    //objeto prinicpal a ser testado = CategoryService
    @InjectMocks
    private CategoryService service;

    //Objeto que iremos simular o comportamento
    @Mock
    private CategoryRepository repository;

    private long existingId;
    private long nonExistingId;
    private CategoryEntity category;
    private CategoryEntity new_category;
    private PageRequest pageRequest;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 33245L;
        category = CategoryFactory.createCategory();
        new_category = CategoryFactory.createCategory();
        new_category.setId(null);
        new_category.setName("Test Insert");


        pageRequest = PageRequest.of(0,10);
        List<CategoryEntity> listCategory = new ArrayList<>();
        listCategory.add(category);
        Page<CategoryEntity> list = new PageImpl(listCategory);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(category));
        Mockito.when(repository.save(category)).thenReturn(category);
        Mockito.when(repository.save(new_category)).thenReturn(new_category);
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).findById(nonExistingId);
        Mockito.when(repository.getOne(existingId)).thenReturn(category);
        Mockito.when(repository.findAll(pageRequest)).thenReturn(list);
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            service.delete(nonExistingId);
        });

        Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
    }

    @Test
    public void deleteShouldTurnStatusToFalseWhenIdExistsAndStatusIsTrue() {
        String result = service.delete(existingId);

        Assertions.assertEquals("Categoria " + category.getName() + " deletado com sucesso.", result);

        Mockito.verify(repository, Mockito.times(1)).findById(existingId);
        Mockito.verify(repository, Mockito.times(1)).save(category);
    }

    @Test
    public void findByIdShouldReturnCategoryWhenIdExists() {

        CategoryDTO result = service.findById(existingId);

        Assertions.assertEquals(existingId, result.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(existingId);
    }

    @Test
    public void findByIdShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            service.findById(nonExistingId);
        });

        Mockito.verify(repository, Mockito.times(1)).findById(nonExistingId);
    }

    @Test
    public void updateShouldReturnCategoryWhenIdExists() {
        CategoryDTO dto = CategoryFactory.createCategoryDTO();
        dto.setName("Teste update");

        CategoryDTO result = service.update(existingId, dto);

        Assertions.assertEquals(result.getName(), dto.getName());
        Mockito.verify(repository, Mockito.times(1)).getOne(existingId);

    }

    @Test
    public void findAllPAgedShouldReturnCategories() {
        Page<CategoryDTO> list = service.findAllPaged(pageRequest);

        Assertions.assertTrue(!list.isEmpty());
        Mockito.verify(repository, Mockito.times(1)).findAll(pageRequest);
    }

    @Test
    public void insertShouldReturnProductWhenInsert() {
        CategoryDTO dto = CategoryFactory.createCategoryDTO();

        dto.setId(null);
        dto.setName("Test Insert");

        CategoryDTO result = service.insert(dto);

        Assertions.assertEquals(result.getName(), dto.getName());
        Mockito.verify(repository, Mockito.times(1)).save(new_category);
    }

}
