package com.senacbooks.senacbooks.products;

import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.categories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Long categoryId, String title, PageRequest pageRequest){
        List<CategoryEntity> categories = (categoryId == 0)? null : Arrays.asList(categoryRepository.getOne(categoryId));
        Page<ProductEntity> page = repository.find(categories,title, pageRequest);
        return page.map(x -> new ProductDTO(x));
    }
}
