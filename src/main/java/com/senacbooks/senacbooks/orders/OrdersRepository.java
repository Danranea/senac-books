package com.senacbooks.senacbooks.orders;

import java.util.List;

import com.senacbooks.senacbooks.categories.CategoryEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long>{

    @Query("SELECT DISTINCT obj FROM OrdersEntity obj")
    Page<OrdersEntity> find(Pageable pageable);
    
    public List<OrdersEntity> findByClientId(Long clientId);
    
}
