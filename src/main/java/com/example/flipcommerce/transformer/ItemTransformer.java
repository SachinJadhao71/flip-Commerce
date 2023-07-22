package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.flipcommerce.Model.Item;

public class ItemTransformer {

    public static ItemResponseDto ItemToItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .itemName(item.getProduct().getProductName())
                .category(item.getProduct().getProductCategory())
                .quantityAdded(item.getRequiredQuantity())
                .price(item.getProduct().getPrice())
                .build();
    }
}
