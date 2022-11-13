package com.xiaohei.sharding.algorithm;

import com.google.common.collect.Range;
import com.xiaohei.utils.DateUtil;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Xiaohei
 * @CreateTime: 2022/11/3 0:05
 */
public class DateSharding implements StandardShardingAlgorithm<Timestamp> {

    private Properties properties;

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Timestamp> preciseShardingValue) {
        Timestamp createdTime = preciseShardingValue.getValue();
        String dateStr = DateUtil.timestampToString(createdTime.getTime(), "yyyy_MM");
        Optional<String> optional = collection.stream().filter(e -> e.endsWith(dateStr)).findAny();
        if (optional.isEmpty()) {
            return "t_order_exception";
        }
        return  optional.get();
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Timestamp> rangeShardingValue) {
        Range<Timestamp> valueRange = rangeShardingValue.getValueRange();
        Timestamp lower = null;
        if (valueRange.hasLowerBound()) {
            lower = valueRange.lowerEndpoint();
        }
        Timestamp upper = null;
        if (valueRange.hasUpperBound()) {
            upper = valueRange.upperEndpoint();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
        Set<String> tables = new HashSet<>();
        for (String logicalTable : collection) {
            String substring = logicalTable.substring(logicalTable.length() - 7);
            Date date = null;
            try {
                date = sdf.parse(substring);
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }
            long firstDayOfMonth = DateUtil.firstDayOfMonth(date);
            long lastDayOfMonth = DateUtil.lastDayOfMonth(date);
            if (lower != null && upper !=null) {
                if ((lower.getTime() >= firstDayOfMonth && lower.getTime() <= firstDayOfMonth)
                        || (lower.getTime() <= firstDayOfMonth && upper.getTime() >= lastDayOfMonth)
                        || (upper.getTime() >= firstDayOfMonth && upper.getTime() <= lastDayOfMonth)) {
                    tables.add(logicalTable);
                }
            } else if (lower != null) {
                if ((lower.getTime() >= firstDayOfMonth && lower.getTime() <= lastDayOfMonth) || lower.getTime() <= firstDayOfMonth) {
                    tables.add(logicalTable);
                }
            } else if (upper != null){
                if ((upper.getTime() >= firstDayOfMonth && upper.getTime() <= lastDayOfMonth) || upper.getTime() >= lastDayOfMonth) {
                    tables.add(logicalTable);
                }
            }
        }
        return tables;
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
