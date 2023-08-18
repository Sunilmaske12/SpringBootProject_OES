package com.springBoot.jsp.OES.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springBoot.jsp.OES.dao.OrderDetailsRepository;
import com.springBoot.jsp.OES.dao.OrderRepository;
import com.springBoot.jsp.OES.entity.Order;
import com.springBoot.jsp.OES.entity.OrderDetails;

@Service
public class OrderServicesImpl implements OrderServices {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	
	@Override
	public List<Order> getAllOrder() {
		List<Order> result = orderRepository.findAll();
		return result;
	}

	@Override
	public List<OrderDetails> getOrderDetailById(int oid) {
		List<OrderDetails> orderDetail=orderDetailsRepository.findByOid(oid);
		return orderDetail;
	}

	@Override
	public List<Order> getAllOrderById(int id) {
		return orderRepository.findByUserId(id);
	}

	@Override
	public int getTotalOrders() {
		return orderRepository.findAll().size();
	}

	@Override
	public int getTotalSell() {
		String totalSell= orderRepository.getTotalSells();
		if(totalSell == null) {
			return 0;
		}
		return Integer.parseInt(totalSell);
	}

	@Override
	public int getNewOrdersCount() {
		return orderRepository.countBySeen("No");
	}

	@Override
	public void updateStatus(String action, int oid) {
		Optional<Order> getOrder = orderRepository.findById(oid);
		Order order = getOrder.get();
		order.setStatus(action);
		orderRepository.save(order);
	
		
	}

	@Override
	public int[] getPercentageStatus() {
		int[] statusPer=new int[4];
		String[] allStatus= orderRepository.getAllStatus();
			statusPer[0] = allStatus.length;
		for(int i=0; i<allStatus.length; i++) {
			if(allStatus[i].equals("Approved")) {
				statusPer[1]+=1;
			}
			else if(allStatus[i].equals("Rejected")) {
				statusPer[2]+=1;
			}
			else {
				statusPer[3]+=1;
			}

		}
		
		return statusPer;
	}

	@Override
	public Order saveOrder(String razopayOrderId, String addressId, int uid, int totalPrice, String paymentMode) {
		int charges=0;
		int priceWithCharge = totalPrice;
		if(totalPrice <= 999) {
			charges = 100;
			priceWithCharge = totalPrice+100;
		}
		
		Order order=new Order();
			order.setAddress_Id(addressId);
			order.setAmount(totalPrice);
			order.setCharges(charges);
			order.setPayment_Mode(paymentMode);
			order.setRazorpay_Order_Id(razopayOrderId);
			order.setTotal_Amount(priceWithCharge);
			order.setUserId(uid);
			order.setStatus("Waiting");
		Order o = orderRepository.save(order);
		return o;
	}

	@Override
	public void saveOrderDetails(OrderDetails orderdetail) {
		orderDetailsRepository.save(orderdetail);		
	}

	
	@Override
	public void updateSeenOrder(int oid) {
		Order order = orderRepository.findById(oid).get();
		order.setSeen("Yes");
		orderRepository.save(order);
	}

}
