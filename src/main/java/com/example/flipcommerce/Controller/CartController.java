package com.example.flipcommerce.Controller;

import com.example.flipcommerce.Dto.RequestDto.CheckoutRequestDto;
import com.example.flipcommerce.Dto.RequestDto.ItemRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CardResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.CartResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.flipcommerce.Model.Item;
import com.example.flipcommerce.Service.CartService;
import com.example.flipcommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto responseDto = cartService.addToCart(item, itemRequestDto);
            return new ResponseEntity(responseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckoutRequestDto checkoutRequestDto){
        try{
            OrderResponseDto responseDto = cartService.checkoutCart(checkoutRequestDto);
            return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
