package com.senacbooks.senacbooks.orders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long>{

    @Query("SELECT DISTINCT obj FROM OrdersEntity obj")
    Page<OrdersEntity> find(Pageable pageable);
    
    public Page<OrdersEntity> findByClientId(Pageable pageable, Long clientId);
    
}
