package com.springBoot.jsp.OES.service;

import java.util.List;

import com.springBoot.jsp.OES.entity.Customer;

public interface CustomerServices {

	Customer getCustomerById(int aid);

	String getCustomerName(String aid);

	List<Customer> getAllCustomerWithAddress(int id);

}
