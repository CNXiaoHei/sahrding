package com.xiaohei.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/3 23:53
 */
@Data
@TableName("t_order")
public class OrderDo {

    @TableId
    private String orderId;

    private String userId;

    private Timestamp createdTime;
}
