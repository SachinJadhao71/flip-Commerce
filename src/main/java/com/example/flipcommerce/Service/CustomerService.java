package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.flipcommerce.Model.Cart;
import com.example.flipcommerce.Model.Customer;
import com.example.flipcommerce.Repository.CustomerRepository;
import com.example.flipcommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

//        make entity from dto
        Customer customer = CustomerTransformer.CustomerRequestDtoTOCustomer(customerRequestDto);

//        set cart
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer); // save both cart and customer

//        prepare response dto

        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerTOCustomerResponseDto(savedCustomer);

        return customerResponseDto;
    }
}
