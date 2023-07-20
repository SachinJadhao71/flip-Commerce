package com.example.flipcommerce.Dto.ResponseDto;


import com.example.flipcommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDto {

    String name;


    String emailId;


    String mobile;


    Gender gender;
}
