package com.xiaohei.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaohei.entity.OrderDo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/3 23:53
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDo> {
}
