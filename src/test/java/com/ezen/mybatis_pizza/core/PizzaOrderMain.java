package com.ezen.mybatis_pizza.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class PizzaOrderMain {
	public static void main(String[] args) {
		try {
			PizzaOrderMain main = new PizzaOrderMain();
			main.createPizzaOrder();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private int createPizzaOrder() {
		String cust_name = "장민석";
		String menu_name = "마카리타";
		
		try {
			var sqlSession = getSqlSession();
			Map<String, Object> orderMap = new HashMap<>();
			
			// createPizzaOrder mapper의 insert문 {주문한 사람 입력<시간은 실시간으로 입력됨>}
			orderMap.put("cust_name", cust_name);
			sqlSession.insert("createPizzaOrder", orderMap);
			sqlSession.commit();
			Long order_Id = (Long) orderMap.get("order_id");
			
			System.out.println("삽입된 주문 ID: " + order_Id);
			
			// createOrderItem mapper의 insert문 <주문 정보 입력>
			orderMap.clear();
			orderMap.put("order_id", order_Id);
			orderMap.put("menu_name", menu_name);
			sqlSession.insert("createOrderItem", orderMap);
			Long menu_Id = (Long) orderMap.get("item_id");
			System.out.println("주문된 항목 ID: " + menu_Id);
			
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static SqlSession getSqlSession() throws Exception {
		String resource = "core-mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
			new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory.openSession();
	}
}
