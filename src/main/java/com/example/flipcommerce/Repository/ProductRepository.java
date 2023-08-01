package com.example.flipcommerce.Repository;

import com.example.flipcommerce.Enum.ProductCategory;
import com.example.flipcommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.price >= :price and p.productCategory = :category")
    public List<Product> findByCategoryAndPrice(int price, ProductCategory category);

}
