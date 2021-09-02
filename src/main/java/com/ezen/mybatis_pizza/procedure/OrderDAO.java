package com.ezen.mybatis_pizza.procedure;

import com.ezen.mybatis_pizza.core.PizzaOrderDVO;

public interface OrderDAO {
	void calldelete_old_orders(PizzaOrderDVO pizzaOrderDVO) throws Exception;
}
