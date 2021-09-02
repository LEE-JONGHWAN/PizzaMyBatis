package com.ezen.mybatis_pizza.core;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter @RequiredArgsConstructor
public class PizzaOrderDVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int order_id;
	private int cust_id;
	private String cust_name;
	private Date order_time;
}
