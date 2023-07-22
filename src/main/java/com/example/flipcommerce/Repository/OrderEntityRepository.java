package com.example.flipcommerce.Repository;

import com.example.flipcommerce.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity,Integer> {
}
