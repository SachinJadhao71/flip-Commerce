package com.example.flipcommerce.Dto.ResponseDto;


import com.example.flipcommerce.Enum.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {

    String itemName;

    int price;

    int quantityAdded;

    ProductCategory category;
}
