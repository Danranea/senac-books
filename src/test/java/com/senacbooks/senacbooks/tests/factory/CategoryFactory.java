package com.senacbooks.senacbooks.tests.factory;

import com.senacbooks.senacbooks.categories.CategoryDTO;
import com.senacbooks.senacbooks.categories.CategoryEntity;

public class CategoryFactory {
    public static CategoryEntity createCategory() {
        CategoryEntity category = new CategoryEntity(
                1L,
                "Teste",
                true
        );

        return category;
    }

    public static CategoryDTO createCategoryDTO() {
        CategoryEntity category = createCategory();

        return new CategoryDTO(category);
    }

    public static CategoryDTO createCategoryDTO(Long id) {
        CategoryDTO dto = createCategoryDTO();
        dto.setId(id);
        return dto;
    }
}
