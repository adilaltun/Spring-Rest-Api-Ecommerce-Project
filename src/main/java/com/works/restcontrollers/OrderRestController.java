package com.works.restcontrollers;


import com.works.entities.Order;
import com.works.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/save")
    public ResponseEntity save (@RequestBody Order order){
        return orderService.save(order);
    }

    @GetMapping("/listOrders")
    public ResponseEntity listOrders(){
        return orderService.listOrders();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity delete (@PathVariable Integer orderId){
        return orderService.delete(orderId);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Order order){
        return orderService.update(order);
    }

    @GetMapping("/getOrderById/{userId}")
    public ResponseEntity getOrderById(@PathVariable int userId){
        return orderService.getOrderById(userId);
    }

}
