package com.xiaohei.sharding.algorithm;


import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Properties;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/3 0:05
 */
public class DateSharding implements StandardShardingAlgorithm<Timestamp> {

    private Properties properties;

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Timestamp> preciseShardingValue) {
        return collection.iterator().next();
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Timestamp> rangeShardingValue) {
        return collection;
    }

    @Override
    public Properties getProps() {
        return this.properties;
    }

    @Override
    public void init(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getType() {
        return "CLASS_BASED";
    }
}
