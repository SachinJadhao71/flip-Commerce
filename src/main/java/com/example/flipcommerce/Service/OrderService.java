package com.example.flipcommerce.Service;

import com.example.flipcommerce.Controller.CustomerController;
import com.example.flipcommerce.Dto.RequestDto.OrderRequestDto;
import com.example.flipcommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.flipcommerce.Enum.ProductStatus;
import com.example.flipcommerce.Exception.CustomerNotFoundException;
import com.example.flipcommerce.Exception.InsufficientQuantityException;
import com.example.flipcommerce.Exception.InvalidCardException;
import com.example.flipcommerce.Exception.ProductNotExistsException;
import com.example.flipcommerce.Model.*;
import com.example.flipcommerce.Repository.*;
import com.example.flipcommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Autowired
    OrderEntityRepository orderEntityRepository;

    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());

        if(customer==null){
            throw new CustomerNotFoundException("customer doesn't exists");
        }

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());

        if(optionalProduct.isEmpty()){
            throw new ProductNotExistsException("product doesn't exixsts");
        }

        Card card = cardRepository.findByCardNumber(orderRequestDto.getCardUsed());
        Date todaysDate = new Date();

        if(card == null || card.getCvv() != orderRequestDto.getCvv() || todaysDate.after(card.getValidTill())){
            throw new InvalidCardException("Invalid Card.");
        }

        Product product = optionalProduct.get();
        if(product.getAvailableQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("sorry...! Quantity is not available.");
        }

        int newQuantity = product.getAvailableQuantity() - orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }


        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.genetrateMaskedCard(orderRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(orderRequestDto.getRequiredQuantity() * product.getPrice());

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        orderEntity.setCustomer(customer);
        orderEntity.getItems().add(item);


        OrderEntity savedOrder = orderEntityRepository.save(orderEntity);


//        send mail
        sendEmail(savedOrder);

        product.getItems().add(savedOrder.getItems().get(0));
        customer.getOrders().add(savedOrder);

//       productRepository.save(product);
//       customerRepository.save(customer);

       return OrderTransformer.OrderToOrderResponseSto(savedOrder);

    }

    public OrderEntity placeOrder(Cart cart, Card card) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.genetrateMaskedCard(card.getCardNumber()));


        int orderTotal = 0;

        for(Item item : cart.getItems()){

            Product product = item.getProduct();

            if(product.getAvailableQuantity() < item.getRequiredQuantity()){
                throw new InsufficientQuantityException("Insufficient Qunatity available for : " + product.getProductName());
            }

            int newQuantity = product.getAvailableQuantity() - item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }


            orderTotal += product.getPrice() * item.getRequiredQuantity();
            item.setOrderEntity(orderEntity);
        }

        orderEntity.setOrderTotal(orderTotal);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }

    public void sendEmail(OrderEntity order){

        String text = "Congrats...! Your Order has been placed And Details as follow : '\n' " +
                "Order Id  : " + order.getOrderId() + "'\n'" +
                "Order Date : " + order.getOrderTotal() ;

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("VaccinationBooking123@gmail.com");
        mail.setTo(order.getCustomer().getEmailId());
        mail.setSubject("Your order placed");
        mail.setText(text);

        javaMailSender.send(mail);
    }
}
