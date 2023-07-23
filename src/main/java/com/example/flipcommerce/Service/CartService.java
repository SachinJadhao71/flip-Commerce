package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.CheckoutRequestDto;
import com.example.flipcommerce.Dto.RequestDto.ItemRequestDto;
import com.example.flipcommerce.Dto.RequestDto.OrderRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CardResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.CartResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.flipcommerce.Exception.CustomerNotFoundException;
import com.example.flipcommerce.Exception.EmptyCartException;
import com.example.flipcommerce.Exception.InvalidCardException;
import com.example.flipcommerce.Model.*;
import com.example.flipcommerce.Repository.*;
import com.example.flipcommerce.transformer.CartTransformer;
import com.example.flipcommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderEntityRepository orderEntityRepository;

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

    public OrderResponseDto checkoutCart(CheckoutRequestDto checkoutRequestDto) {

        Customer customer = customerRepository.findByEmailId(checkoutRequestDto.getCustomerEmail());
        if(customer==null){
            throw new CustomerNotFoundException("customer doesn't exists");
        }

        Card card = cardRepository.findByCardNumber(checkoutRequestDto.getCardNo());
        Date currDate = new Date();
        if(card==null || card.getCvv() != checkoutRequestDto.getCvv() || currDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid Card");
        }


        Cart cart = customer.getCart();
        if(cart.getItems().size() == 0){
            throw new EmptyCartException("cart is empty.");
        }


        OrderEntity order = orderService.placeOrder(cart,card);

        reset(cart);

        OrderEntity savedOrder = orderEntityRepository.save(order);

//        prepare response dto
        OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseSto(savedOrder);

        return orderResponseDto;

    }
    public void reset(Cart cart){

        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }

        cart.setItems(new ArrayList<>());
    }
}
