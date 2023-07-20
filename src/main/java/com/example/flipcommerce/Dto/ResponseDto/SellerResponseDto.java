package com.example.flipcommerce.Dto.ResponseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerResponseDto {

    String name;

    String emailId;
}
