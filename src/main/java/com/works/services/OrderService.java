package com.works.services;


import com.works.entities.JoinProductOrder;
import com.works.entities.Order;
import com.works.repositories.JoinProductOrderRepository;
import com.works.repositories.OrderRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OrderService {
    final OrderRepository orderRepository;
    final JoinProductOrderRepository joinProductOrderRepository;
    final ProductService productService;
    final UserService userService;

    public OrderService(OrderRepository orderRepository, JoinProductOrderRepository joinProductOrderRepository, UserService userService,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.joinProductOrderRepository = joinProductOrderRepository;
        this.userService=userService;
        this.productService=productService;
    }


    public ResponseEntity save (Order order){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            hm.put(REnum.status,true);
            hm.put(REnum.result,orderRepository.save(order));
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch(Exception ex){
            hm.put(REnum.status,false);
            hm.put(REnum.error,ex.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity listOrders (){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result,joinProductOrderRepository.listOrders());
        return new ResponseEntity(hm, HttpStatus.OK);
    }


    public ResponseEntity delete (Integer orderId){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isPresent()){
                Order order = new Order();
                orderRepository.deleteById(orderId);
                hm.put(REnum.status,true);
                hm.put(REnum.message,"Delete Success");
                hm.put(REnum.result,order);
                return new ResponseEntity(hm, HttpStatus.OK);
            }else {
                hm.put(REnum.status,false);
                hm.put(REnum.message,"Delete Fail!");
                return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            hm.put(REnum.status,false);
            hm.put(REnum.error,ex.getMessage());
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update(Order order){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        Optional<JoinProductOrder> optionalJoinProductOrder = joinProductOrderRepository.findById(order.getOrderId());
        if (optionalJoinProductOrder.isPresent()){
            orderRepository.saveAndFlush(order);
            hm.put(REnum.status,true);
            hm.put(REnum.result,order);
            return new ResponseEntity(hm,HttpStatus.OK);
        }else {
            hm.put(REnum.status,false);
            hm.put(REnum.message,"User not found");
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity getOrderById(Integer userId){
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, joinProductOrderRepository.getOrderById(userId));
        return new ResponseEntity(hm, HttpStatus.OK);
    }
}
