package com.ezen.mybatis_pizza.annotation;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class PizzaMenuDAOimpl implements PizzaMenuDAO {

	@Override
	public int updateMenuData(int menu_id, int menu_price) throws Exception {
		SqlSession sqlSession = getSqlSession();
		var mapper = sqlSession.getMapper(MenuMapper.class);
		
		PizzaMenuDVO dvo = new PizzaMenuDVO();
		dvo.setMenu_id(menu_id);
		dvo.setMenu_price(menu_price);
		
		int cnt = mapper.updateMenuData(dvo);
		
		sqlSession.commit();
		
		return cnt;
	}
	
	/**
	 * 프로젝트 구성 설정 값을 읽어 적용하고, sqlSession 을 생성하여 반환한다.
	 * @return SqlSession 객체
	 * @throws Exception
	 */
	private static SqlSession getSqlSession() throws Exception {
		String resource = "annotation/java-mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(inputStream);
		
		sqlSessionFactory.getConfiguration().addMapper(MenuMapper.class);
		
		return sqlSessionFactory.openSession();
	}

}
