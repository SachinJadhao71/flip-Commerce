package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.CardRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CardResponseDto;
import com.example.flipcommerce.Exception.CustomerNotFoundException;
import com.example.flipcommerce.Model.Card;
import com.example.flipcommerce.Model.Customer;
import com.example.flipcommerce.Repository.CustomerRepository;
import com.example.flipcommerce.transformer.CardTronsformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

//        check customer exists or not
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getCustomerEmail());

        if(customer == null){
            throw new CustomerNotFoundException("Customer not Exists.");
        }

        Card card = CardTronsformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);

        List<Card> cards = savedCustomer.getCards();

        Card latestCard = cards.get(cards.size()-1);

//        prepare card response dto

        CardResponseDto cardResponseDto = CardTronsformer.CardToCardResponseDto(latestCard);
        cardResponseDto.setCardNumber(genetrateMaskedCard(latestCard.getCardNumber()));


        return cardResponseDto;
    }

    public String genetrateMaskedCard(String cardNo){

        int n = cardNo.length();

        String maskedCard = "";

        for(int i=0; i<n-4; i++){
            maskedCard += "X";
        }
        maskedCard += cardNo.substring(n-4);

        return maskedCard;
    }
}
