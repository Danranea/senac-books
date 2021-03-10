package com.senacbooks.senacbooks.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> list = repository.findAll();

        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<CategoryEntity> obj = repository.findById(id);
        CategoryEntity entity = obj.orElseThrow();
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);

        return new CategoryDTO(entity);
    }

    private void copyDTOToEntity(CategoryDTO dto, CategoryEntity entity) {
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
    }

}
