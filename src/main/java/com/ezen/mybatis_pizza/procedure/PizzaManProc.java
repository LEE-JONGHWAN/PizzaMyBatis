package com.ezen.mybatis_pizza.procedure;

import com.ezen.mybatis_pizza.core.PizzaOrderDVO;

public class PizzaManProc {

	public static void main(String[] args) throws Exception {
		var main = new PizzaManProc();
		main.calldelete_old_orders();
	}
	
	private void calldelete_old_orders(){
		PizzaOrderDVO pizzaOdvo = new PizzaOrderDVO();
		int order_id = 7;
		
		pizzaOdvo.setOrder_id(order_id);
		
		OrderDAO orderDao = new OrderDAOanno();
		try {
			orderDao.calldelete_old_orders(pizzaOdvo);
			System.out.println("삭제된 최대 주문 번호 : " +  order_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
