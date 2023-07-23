package com.example.flipcommerce.Controller;

import com.example.flipcommerce.Dto.RequestDto.ProductRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.flipcommerce.Enum.ProductCategory;
import com.example.flipcommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto responseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    get all the product of category and price grater than

    @GetMapping("/find_by_price_and_category")
    public ResponseEntity getProductByCategoryAndPriceGreaterThan(@RequestParam("price") int price, @RequestParam("category")ProductCategory category){

        List<ProductResponseDto> dtos = productService.getProductByCategoryAndPriceGreaterThan(price,category);
        return new ResponseEntity(dtos,HttpStatus.FOUND);
    }


}
