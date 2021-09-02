package com.ezen.mybatis_pizza.annotation;

import org.apache.ibatis.annotations.Update;

public interface MenuMapper {
	/**
	 * 갱신 - 메뉴 가격
	 * @param petDVO
	 * 
	 */
	@Update("update pizza_menu set menu_price = #{menu_price}"
			+ " where menu_id = #{menu_id};")
	public int updateMenuData(PizzaMenuDVO pizzaMenuDVO);

}
