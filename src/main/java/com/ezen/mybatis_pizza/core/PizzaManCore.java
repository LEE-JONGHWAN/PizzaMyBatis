package com.ezen.mybatis_pizza.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class PizzaManCore {

	public static void main(String[] args) {
		/**
		 * 고객이름, 메뉴이름 입력 받아 주문 및 주문 아이템 저장
		 */
		String cust_name = "장민석";
		String menu_name = "마카리타";

		PizzaManCore main = new PizzaManCore();
		main.createPizzaOrder(cust_name, menu_name);
	}

	private void createPizzaOrder(String cust_name, String menu_name) {
		Map<String, Object> orderMap = new HashMap<>();

		orderMap.put("cust_name", cust_name);

		SqlSession sqlSession;
		try {
			sqlSession = getSqlSession();
			sqlSession.insert("createPizzaOrder", orderMap);
			Long order_id = (Long) orderMap.get("order_id");
			System.out.println("삽입된 주문 ID: " + order_id);
			
			orderMap.clear();
			
			orderMap.put("order_id", order_id);
			orderMap.put("menu_name", menu_name);
			sqlSession.insert("createOrderItem", orderMap);
			Long item_id = (Long) orderMap.get("item_id");
			System.out.println("주문된 항목 ID: " + item_id);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@formatter:off
	private static SqlSession getSqlSession() throws Exception {
		String resource = "core-mybatis-config.xml";
		InputStream inputStream = 
				Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory.openSession();
	}
}
