package com.senacbooks.senacbooks.orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long>{

    static Page<OrdersEntity> findAllPaged(PageRequest pageRequest) {
        return null;
    }
    
    
}
