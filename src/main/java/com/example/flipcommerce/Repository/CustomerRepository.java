package com.example.flipcommerce.Repository;

import com.example.flipcommerce.Enum.Gender;
import com.example.flipcommerce.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    public Customer findByEmailId(String email);

}
