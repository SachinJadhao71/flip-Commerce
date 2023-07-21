package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.RequestDto.CardRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CardResponseDto;
import com.example.flipcommerce.Model.Card;

public class CardTronsformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNumber(cardRequestDto.getCardNumber())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card){

        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .build();

//        we are not seting card number here becasuse we to set with mask
    }
}
