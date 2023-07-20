package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.RequestDto.SellerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.flipcommerce.Model.Seller;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){

        return Seller.builder()
                .panNo(sellerRequestDto.getPanNo())
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){

        return SellerResponseDto.builder()
                .emailId(seller.getEmailId())
                .name(seller.getName())
                .build();
    }
}
