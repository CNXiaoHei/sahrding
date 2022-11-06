package com.xiaohei.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.xiaohei.dao.mapper.OrderMapper;
import com.xiaohei.entity.OrderDo;
import com.xiaohei.entity.OrderVo;
import com.xiaohei.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/3 23:52
 */
@DS("sharding")
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void createOrder(OrderVo orderVo) {

        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
        orderDo.setUserId(orderVo.getUserId());
        orderDo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        orderMapper.insert(orderDo);
    }
}
