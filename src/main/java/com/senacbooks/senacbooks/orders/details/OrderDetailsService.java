package com.senacbooks.senacbooks.orders.details;

import com.senacbooks.senacbooks.orders.OrdersEntity;
import com.senacbooks.senacbooks.orders.OrdersRepository;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.products.ProductRepository;
import com.senacbooks.senacbooks.products.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailsService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductService productService;

  @Transactional
  public Page<OrderDetailsDTOOut> findAllPaged(PageRequest pageRequest) {
    Page<OrderDetailsEntity> list = orderDetailsRepository.find(pageRequest);

    return list.map(x -> new OrderDetailsDTOOut(x));
  }

  @Transactional
  public OrderDetailsDTO insert(OrderDetailsDTO dto) {
    OrderDetailsEntity entity = new OrderDetailsEntity();
    copyDTOToEntity(dto, entity);
    updateProductQuantity(dto.getProduct(), dto.getQuantity());
    entity = orderDetailsRepository.save(entity);
    
    return new OrderDetailsDTO(entity); 
  }

  private void copyDTOToEntity(OrderDetailsDTO dto, OrderDetailsEntity entity) {
    OrdersEntity ordersEntity = ordersRepository.getOne(dto.getOrder().getId());
    entity.setOrder(ordersEntity);
    
    ProductEntity productEntity = productRepository.getOne(dto.getProduct().getId());
    entity.setProduct(productEntity);

    entity.setQuantity(dto.getQuantity());
  }

  private void updateProductQuantity(ProductDTO product, Integer quantity) {
    if (product.getQuantity() >= quantity) {
      Integer result = product.getQuantity() - quantity;
      product.setQuantity(result);
    } else {
      return;
    }
    productService.update(product.getId(), product);
  }

}
