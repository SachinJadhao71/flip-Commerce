package com.example.flipcommerce.Controller;

import com.example.flipcommerce.Dto.RequestDto.SellerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.flipcommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto responseDto = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

//    top 5 seller with cheapest product

//    update email of seller

//    get all seller
}
