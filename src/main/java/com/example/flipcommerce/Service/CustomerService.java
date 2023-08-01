package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.flipcommerce.Enum.Gender;
import com.example.flipcommerce.Enum.OrderStatus;
import com.example.flipcommerce.Model.Cart;
import com.example.flipcommerce.Model.Customer;
import com.example.flipcommerce.Model.OrderEntity;
import com.example.flipcommerce.Repository.CustomerRepository;
import com.example.flipcommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<CustomerResponseDto> customerWithMoreThanFiveOrder() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto> responseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            if(customer.getOrders().size() >= 5){
                responseDtoList.add(CustomerTransformer.CustomerTOCustomerResponseDto(customer));
            }
        }

        return responseDtoList;
    }

    public List<CustomerResponseDto> getAllCustomerWithMoreThanFiveItemsIncart() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto> responseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            if(customer.getCart().getItems().size() >= 5){
                responseDtoList.add(CustomerTransformer.CustomerTOCustomerResponseDto(customer));
            }
        }

        return responseDtoList;
    }

    public List<CustomerResponseDto> getAllCustomerWhoCancelledMoreThanTwoOrders() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto> responseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            List<OrderEntity> orders = customer.getOrders();
            int count = 0;
            for(OrderEntity order : orders){
                if(order.getOrderStatus() == OrderStatus.CANCELLED){
                    count++;
                }
            }
            if(count >= 2){
                responseDtoList.add(CustomerTransformer.CustomerTOCustomerResponseDto(customer));
            }
        }

        return responseDtoList;
    }

    public List<CustomerResponseDto> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto> responseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            responseDtoList.add(CustomerTransformer.CustomerTOCustomerResponseDto(customer));
        }

        return responseDtoList;
    }
}
