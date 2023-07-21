package com.example.flipcommerce.Service;

import com.example.flipcommerce.Dto.RequestDto.ProductRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.flipcommerce.Enum.ProductCategory;
import com.example.flipcommerce.Exception.SellerNotFoundException;
import com.example.flipcommerce.Model.Product;
import com.example.flipcommerce.Model.Seller;
import com.example.flipcommerce.Repository.ProductRepository;
import com.example.flipcommerce.Repository.SellerRepository;
import com.example.flipcommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmail());

        if(seller==null){
            throw new SellerNotFoundException("Seller Not Exist.");
        }

//        convert dto to entity

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller);

        List<Product> products = savedSeller.getProducts();

        Product latestProduct = products.get(products.size()-1);

//        prepare response dto
        ProductResponseDto responseDto = ProductTransformer.ProductToProductResponseDto(latestProduct);

        return responseDto;

    }

    public List<ProductResponseDto> getProductByCategoryAndPriceGreaterThan(int price, ProductCategory category) {

        List<Product> productList = productRepository.findByCategoryAndPrice(price,category);

//        we have to create list of product response dto
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : productList){
            ProductResponseDto responseDto = ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtos.add(responseDto);
        }

        return productResponseDtos;
    }
}
