package com.ezen.mybatis_pizza.spring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderPriceDVO {
	private int 주문ID;
	private int 고객ID;
	private String 주문일;
	private int 소계;

	@Override
	public String toString() {
		String 소계컴마 = String.format("%,d원", 소계);
		var sb = new StringBuilder("주문가격 [주문번호=");
		
		sb.append(주문ID);
		sb.append(", 고객번호=");
		sb.append(고객ID);
		sb.append(", 주문일=");
		sb.append(주문일);
		sb.append(", 소계=");
		sb.append(소계컴마 + "]");
		
		return sb.toString();
	}

}
