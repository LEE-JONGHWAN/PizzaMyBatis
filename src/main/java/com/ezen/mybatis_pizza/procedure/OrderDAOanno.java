package com.ezen.mybatis_pizza.procedure;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;

import com.ezen.mybatis_pizza.annotation.MenuMapper;
import com.ezen.mybatis_pizza.core.PizzaOrderDVO;

public class OrderDAOanno implements OrderDAO {

	@Override
	public void calldelete_old_orders(PizzaOrderDVO pizzaOrderDVO) throws Exception {
		SqlSession sqlSession = getSqlSession();
		var mapper = sqlSession.getMapper(OrderMapper.class);
		
		mapper.calldelete_old_orders(pizzaOrderDVO);
	}
	
	private static SqlSession getSqlSession() throws Exception {
		String resource = "procedure/java-mybatis-config.xml";
		InputStream inputStream = 
				Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		sqlSessionFactory.getConfiguration().addMapper(OrderMapper.class);
		
		return sqlSessionFactory.openSession();
	}
}
