package com.example.flipcommerce.Dto.ResponseDto;

import com.example.flipcommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {

    String customerName;

    String cardNumber;

    CardType cardType;

    Date validTill;
}
