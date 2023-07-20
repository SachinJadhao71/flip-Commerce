package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.SellerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.flipcommerce.Model.Seller;
import com.example.flipcommerce.Repository.SellerRepository;
import com.example.flipcommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

//        convert dto to entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
