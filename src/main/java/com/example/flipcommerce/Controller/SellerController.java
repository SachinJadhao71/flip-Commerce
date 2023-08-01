package com.example.flipcommerce.Controller;

import com.example.flipcommerce.Dto.RequestDto.SellerRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.flipcommerce.Service.SellerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/top_five_cheapest_seller")
    public ResponseEntity getTopFiveCheapestSeller(){
        List<String> sellers = sellerService.getTopFiveCheapestSeller();

        return new ResponseEntity(sellers,HttpStatus.ACCEPTED);
    }

//    update email of seller

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail, @RequestParam("newEmail") String newEmail){
        try {
            String ans = sellerService.updateEmail(oldEmail, newEmail);
            return new ResponseEntity(ans,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

//    get all seller

    @GetMapping("/get_all")
    public ResponseEntity getAllSeller(){
        List<SellerResponseDto> responseDtoList = sellerService.getAllSeller();

        return new ResponseEntity(responseDtoList,HttpStatus.ACCEPTED);
    }
}
