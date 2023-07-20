package com.example.flipcommerce.transformer;

import com.example.flipcommerce.Dto.RequestDto.ProductRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.flipcommerce.Enum.ProductStatus;
import com.example.flipcommerce.Model.Product;

public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .productCategory(productRequestDto.getProductCategory())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .price(productRequestDto.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .availableQuantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .productName(product.getProductName())
                .productStatus(product.getProductStatus())
                .build();
    }
}
