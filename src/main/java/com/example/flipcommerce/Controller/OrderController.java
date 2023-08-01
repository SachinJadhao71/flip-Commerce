package com.example.flipcommerce.Controller;

import com.example.flipcommerce.Dto.RequestDto.OrderRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.CancelOrderResponseDto;
import com.example.flipcommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.flipcommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){

        try {
            OrderResponseDto responseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/cancel")
    public ResponseEntity cancelOrder(@RequestParam("orderId") String orderId){
        CancelOrderResponseDto responseDto = orderService.cancelOrder(orderId);
        return new ResponseEntity<>(responseDto,HttpStatus.ACCEPTED);

    }


    @GetMapping("/get_top_three")
    public ResponseEntity getFiveHighest(){
        List<OrderResponseDto> responseDtoList = orderService.getFiveHighest();
        return new ResponseEntity<>(responseDtoList,HttpStatus.ACCEPTED);
    }


}
