package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.flipcommerce.Model.Item;
import com.example.flipcommerce.Model.OrderEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseSto(OrderEntity orderEntity){

        List<ItemResponseDto> list = new ArrayList<>();

        for(Item item : orderEntity.getItems()){
            ItemResponseDto responseDto = ItemTransformer.ItemToItemResponseDto(item);
            list.add(responseDto);
        }
        Date date = new Date();

        return OrderResponseDto.builder()
                .orderId(orderEntity.getOrderId())
                .orderTotal(orderEntity.getOrderTotal())
                .customerName(orderEntity.getCustomer().getName())
                .cardUsed(orderEntity.getCardUsed())
                .items(list)
                .orderDate(date)
                .build();
    }
}
