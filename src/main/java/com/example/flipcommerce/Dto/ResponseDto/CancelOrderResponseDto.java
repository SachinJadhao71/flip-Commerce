package com.example.flipcommerce.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelOrderResponseDto {

    String orderId;

    int orderTotal;

    List<ItemResponseDto> items;
}
