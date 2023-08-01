package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.SellerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.flipcommerce.Exception.SellerNotFoundException;
import com.example.flipcommerce.Model.Product;
import com.example.flipcommerce.Model.Seller;
import com.example.flipcommerce.Repository.ProductRepository;
import com.example.flipcommerce.Repository.SellerRepository;
import com.example.flipcommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

//        convert dto to entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }

    public List<String> getTopFiveCheapestSeller() {

        List<Product> products = productRepository.findTopFiveCheapest();

        List<String> names = new ArrayList<>();

        for(Product product : products){
            names.add(product.getSeller().getName());
        }

        return names;
    }

    public String updateEmail(String oldEmail, String newEmail) {

        Seller seller = sellerRepository.findByEmailId(oldEmail);

        if(seller == null){
            throw new SellerNotFoundException("Mail doesn't exists");
        }

        seller.setEmailId(newEmail);

        Seller savedSeller = sellerRepository.save(seller);

        return "Congrats..! " + savedSeller.getName() + ". your Email has been Updated";
    }

    public List<SellerResponseDto> getAllSeller() {

        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> responseDtoList = new ArrayList<>();

        for(Seller seller : sellers){
            responseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
        }

        return responseDtoList;
    }
}
