package com.ezen.mybatis_pizza.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PizzaManSpring {

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext appContext =
				new ClassPathXmlApplicationContext(
				  new String[]{"spring/applicationContext-myBatis.xml"});
			
			OrderDAO orderDaoImpl =
					(OrderDAO)appContext.getBean("orderDAOimpl");
			/**
			 * 애완동물 목록 길이 출력
			 */
			Map<String, Object> custMap = new HashMap<>();
			
			custMap.put("cust_name", "장민석");
			
			List <OrderPriceDVO> orders = 
					orderDaoImpl.getOrderPrices(custMap);
			System.out.println("--- 장민석 주문 목록 ---");
			orders.stream().forEach(System.out::println);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
