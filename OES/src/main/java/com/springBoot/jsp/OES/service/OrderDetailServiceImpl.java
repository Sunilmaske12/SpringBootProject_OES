package com.springBoot.jsp.OES.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.jsp.OES.dao.OrderDetailsRepository;
import com.springBoot.jsp.OES.entity.OrderDetails;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailsRepository odr;
	
	@Override
	public OrderDetails savaProductDetails(OrderDetails orderdetail) {
		return odr.save(orderdetail);
		
	}


}
