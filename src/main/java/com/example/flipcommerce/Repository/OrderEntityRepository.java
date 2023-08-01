package com.example.flipcommerce.Repository;

import com.example.flipcommerce.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {

    @Query("SELECT o FROM OrderEntity o ORDER BY o.orderTotal DESC LIMIT 3")
    public List<OrderEntity> findByOrder();

    public OrderEntity findByOrderId(String orderId);

}
