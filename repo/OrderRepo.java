package com.zuhlke.apachecalcitedemo.repo;

import com.zuhlke.apachecalcitedemo.model.Order;
import com.zuhlke.apachecalcitedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderRepo extends JpaRepository<Order, Long> {

    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    @Override
    List<Order> findAll();
}
