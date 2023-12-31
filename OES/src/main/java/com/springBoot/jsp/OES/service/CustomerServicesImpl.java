package com.springBoot.jsp.OES.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.jsp.OES.dao.CustomerRepository;
import com.springBoot.jsp.OES.entity.Customer;

@Service
public class CustomerServicesImpl implements CustomerServices {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomerById(int aid) {
		Optional<Customer> getCustomer = customerRepository.findById(aid);
		Customer customer = getCustomer.get();
		return customer;
	}

	@Override
	public String getCustomerName(String aid) {
		return customerRepository.getCustomerName(aid);
	}

	@Override
	public List<Customer> getAllCustomerWithAddress(int id) {
		return customerRepository.findAll();
	}

	@Override
	public void insertCustomer(Customer customer) {
		customerRepository.save(customer);
		
	}

}
