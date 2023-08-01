package com.example.flipcommerce.Controller;

import com.example.flipcommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.flipcommerce.Repository.CustomerRepository;
import com.example.flipcommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto responseDto = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }


//    get all customer who have order more than 5 orders

    @GetMapping("/more_than_five_orders")
    public ResponseEntity customerWithMoreThanFiveOrder(){
        List<CustomerResponseDto> customers = customerService.customerWithMoreThanFiveOrder();
        return new ResponseEntity(customers,HttpStatus.ACCEPTED);
    }


//    get all customer whose cart have more than 5 items

    @GetMapping("/more_than_five_items_in_cart")
    public ResponseEntity getAllCustomerWithMoreThanFiveItemsIncart(){
        List<CustomerResponseDto> customers = customerService.getAllCustomerWithMoreThanFiveItemsIncart();
        return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
    }


//    all customer who cancelled more than 2 order

    @GetMapping("/who_cancelled_more_than_2_orders")
    public ResponseEntity getAllCustomerWhoCancelledMoreThanTwoOrders(){
        List<CustomerResponseDto> customers = customerService.getAllCustomerWhoCancelledMoreThanTwoOrders();
        return new ResponseEntity<>(customers,HttpStatus.ACCEPTED);
    }



//    get all the customer
    @GetMapping("/get_all")
    public ResponseEntity getAllCustomers(){
        List<CustomerResponseDto> responseDtoList = customerService.getAllCustomers();
        return new ResponseEntity<>(responseDtoList,HttpStatus.ACCEPTED);
    }

}
