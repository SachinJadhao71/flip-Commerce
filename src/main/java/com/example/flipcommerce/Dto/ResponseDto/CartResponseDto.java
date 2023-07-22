package com.example.flipcommerce.Dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    String customerName;

    int cartTotal;

    List<ItemResponseDto> items = new ArrayList<>();
}
