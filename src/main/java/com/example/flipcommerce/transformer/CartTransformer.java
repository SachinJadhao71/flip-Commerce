package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.ResponseDto.CartResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.flipcommerce.Model.Cart;
import com.example.flipcommerce.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> responseDtos = new ArrayList<>();

        for(Item item : cart.getItems()){
            ItemResponseDto itemResponseDto = ItemTransformer.ItemToItemResponseDto(item);
            responseDtos.add(itemResponseDto);
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(responseDtos)
                .build();
    }
}
