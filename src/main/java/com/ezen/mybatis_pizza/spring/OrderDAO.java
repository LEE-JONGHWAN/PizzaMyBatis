package com.ezen.mybatis_pizza.spring;

import java.util.List;
import java.util.Map;

public interface OrderDAO {
	List<OrderPriceDVO> getOrderPrices(Map<String,Object> custMap);
}
