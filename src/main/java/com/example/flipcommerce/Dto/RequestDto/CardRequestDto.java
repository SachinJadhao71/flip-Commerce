package com.example.flipcommerce.Dto.RequestDto;

import com.example.flipcommerce.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    String customerEmail;

    String cardNumber;

    int cvv;

    CardType cardType;

    Date validTill;
}
