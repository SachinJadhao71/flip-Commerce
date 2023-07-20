package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.flipcommerce.Model.Customer;

public class CustomerTransformer {

    public static Customer CustomerRequestDtoTOCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .mobile(customerRequestDto.getMobile())
                .gender(customerRequestDto.getGender())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }

    public static CustomerResponseDto CustomerTOCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .mobile(customer.getMobile())
                .gender(customer.getGender())
                .emailId(customer.getEmailId())
                .build();
    }
}
