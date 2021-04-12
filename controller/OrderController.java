package com.zuhlke.apachecalcitedemo.controller;

import com.zuhlke.apachecalcitedemo.dto.OrderDto;
import com.zuhlke.apachecalcitedemo.dto.UserDto;
import com.zuhlke.apachecalcitedemo.model.Order;
import com.zuhlke.apachecalcitedemo.model.User;
import com.zuhlke.apachecalcitedemo.repo.OrderRepo;
import com.zuhlke.apachecalcitedemo.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/data-service")
public class OrderController {

    private final OrderRepo orderRepo;

    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/orders")
    public Iterable<OrderDto> index() {

        final List<Order> orders = orderRepo.findAll();
        return orders.stream().map(user -> {
            OrderDto dto = new OrderDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
