package com.ezen.mybatis_pizza.annotation;

import java.sql.SQLException;


public class PizzaManAnnotation {
	public static void main(String[] args) {
		try {
			PizzaManAnnotation main = new PizzaManAnnotation();
			/**
			 *  4번 메뉴의 가격을 32000원으로 변경.
			 */
			main.updateMenuData(4, 30_000);
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void updateMenuData(int menu_id, int menu_price) throws Exception {
		var pizzaDAOimpl = new PizzaMenuDAOimpl();
		
			int cnt = pizzaDAOimpl.updateMenuData(
					menu_id, menu_price);
			
			System.out.println("갱신된 행 수: " + cnt);
		
	}
}
