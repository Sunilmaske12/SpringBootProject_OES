package com.springBoot.jsp.OES.service;

import java.util.List;

import com.springBoot.jsp.OES.entity.Order;
import com.springBoot.jsp.OES.entity.OrderDetails;

public interface OrderServices {

	public List<Order> getAllOrder();

	public List<OrderDetails> getOrderDetailById(int oid);

	public List<Order> getAllOrderById(int id);

	public int getTotalOrders();

	public int getTotalSell();

	public int getNewOrdersCount();

	public void updateStatus(String action, int oid);

	public int[] getPercentageStatus();

	public Order saveOrder(String razopayOrderId,String addressId, int uid, int totalPrice, String paymentMode);

	public void saveOrderDetails(OrderDetails orderdetail);

	public void updateSeenOrder(int oid);

	public OrderDetails savaProductDetails(OrderDetails orderdetail);

}
