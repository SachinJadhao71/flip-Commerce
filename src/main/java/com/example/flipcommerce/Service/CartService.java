package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.ItemRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CardResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.CartResponseDto;
import com.example.flipcommerce.Model.Cart;
import com.example.flipcommerce.Model.Customer;
import com.example.flipcommerce.Model.Item;
import com.example.flipcommerce.Model.Product;
import com.example.flipcommerce.Repository.CartRepository;
import com.example.flipcommerce.Repository.CustomerRepository;
import com.example.flipcommerce.Repository.ItemRepository;
import com.example.flipcommerce.Repository.ProductRepository;
import com.example.flipcommerce.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {


        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());

        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + product.getPrice() * itemRequestDto.getRequiredQuantity());

        item.setCart(cart);
        item.setProduct(product);
        Item savedItem = itemRepository.save(item);

        cart.getItems().add(savedItem);
        product.getItems().add(savedItem);

        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

//        prepaer cart response dto

        return CartTransformer.CartToCartResponseDto(savedCart);




    }
}
