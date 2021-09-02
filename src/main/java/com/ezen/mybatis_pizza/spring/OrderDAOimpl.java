package com.ezen.mybatis_pizza.spring;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

public class OrderDAOimpl implements OrderDAO {

	@Override
	public List<OrderPriceDVO> getOrderPrices(Map<String, Object> custMap) {
		List<OrderPriceDVO> result = 
				sqlSessionTemplate.selectList("getOrderPrices", custMap); 
		return result;
	}
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate
			sqlSessionTemplate) { 
		this.sqlSessionTemplate = sqlSessionTemplate; 
	}
}
