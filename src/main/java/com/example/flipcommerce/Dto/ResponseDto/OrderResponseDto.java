package com.example.flipcommerce.Dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {

    String customerName;

    String orderId;

    Date orderDate;

    String cardUsed;

   int orderTotal;

    List<ItemResponseDto> items;
}
