package com.ezen.mybatis_pizza.procedure;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.StatementType;

import com.ezen.mybatis_pizza.core.PizzaOrderDVO;


public interface OrderMapper {
	@Insert (value = "{ CALL delete_old_orders(" + 
			" #{ order_id, mode = IN, jdbcType = INTEGER})} ")
	@Options(statementType = StatementType.CALLABLE)
	public void calldelete_old_orders(PizzaOrderDVO pizzaOrderDVO);
}
