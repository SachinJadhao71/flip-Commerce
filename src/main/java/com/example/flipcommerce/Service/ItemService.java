package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.ItemRequestDto;
import com.example.flipcommerce.Exception.CustomerNotFoundException;
import com.example.flipcommerce.Exception.InsufficientQuantityException;
import com.example.flipcommerce.Exception.ProductNotExistsException;
import com.example.flipcommerce.Model.Customer;
import com.example.flipcommerce.Model.Item;
import com.example.flipcommerce.Model.Product;
import com.example.flipcommerce.Repository.CustomerRepository;
import com.example.flipcommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public Item createItem(ItemRequestDto itemRequestDto){

//        check customer exists or not
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());

        if(customer==null){
            throw new CustomerNotFoundException("Customer does Not exists");
        }

//        check product exists or not
        Optional<Product> optionalProduct = productRepository.findById(itemRequestDto.getProductId());

        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("Product does not exists");
        }

        Product product = optionalProduct.get();

//        check quantiy is available or not

        if(product.getAvailableQuantity() < itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("sorry..! quantity is not available");
        }


        Item item = Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();

        return item;
    }
}
