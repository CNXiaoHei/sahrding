package com.xiaohei.controller;


import com.xiaohei.entity.OrderVo;
import com.xiaohei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/3 23:51
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@RequestBody OrderVo orderVo) {
        orderService.createOrder(orderVo);
    }
}
