package com.ezen.mybatis_pizza.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PizzaManager {
	//@formatter:off
	
	public static void main(String[] args) {
		/**
		 * 고객이름, 메뉴이름 입력 받아 주문 및 주문 아이템 저장
		 */
		String cust_name = "장민석";
		String menu_name = "마카리타";

		try (Connection conn = DBCPDataSource.getConnection()) {
			conn.setAutoCommit(false);
			
			int order_Id = createPizzaOrder(cust_name, conn);

			if (order_Id > 0) {
				createOrderItem(order_Id, menu_name, conn);
				conn.commit();
				System.out.println("주문 저장 완료!");
			} else {
				throw new SQLException("주문 저장 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int createOrderItem(int order_Id, 
			String menu_name, Connection conn) 
					throws SQLException {
		StringBuilder sb = new StringBuilder();

		sb.append("insert into order_item ");
		sb.append("(order_id, menu_id) select ");
		sb.append(order_Id);
		sb.append(", menu_id ");
		sb.append("from pizza_menu pm ");
		sb.append("where pm.menu_name = '");
		sb.append(menu_name);
		sb.append("'");

		try (Statement stmt = conn.createStatement()) {
			return stmt.executeUpdate(sb.toString());
		} catch (SQLException e) {
			throw new SQLException("주문 항목 저장 실패!");
		}
	}

	private static int createPizzaOrder(String cust_name, 
			Connection conn) throws SQLException {

		var sb = new StringBuilder("insert into pizza_order ");

		sb.append("(cust_id) select cust_id from customer c ");
		sb.append("where cust_name = '");
		sb.append(cust_name);
		sb.append("'");

		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sb.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					int order_id = (int) generatedKeys.getLong(1);
					return order_id;
				} else {
					throw new SQLException("주문 번호 채취 실패");
				}
			}
		} catch (SQLException e) {
			conn.rollback();
			throw new SQLException("주문 정보 저장 실패");
		}
	}
}
