package com.senacbooks.senacbooks.orders.details;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long>{
    
    @Query("SELECT DISTINCT obj FROM OrderDetailsEntity obj")
    Page<OrderDetailsEntity> find(Pageable pageable);
}
