package com.nit.hari.service;

import java.util.List;
import java.util.Optional;

import com.nit.hari.model.customer.Customer;


public interface CustomerService {

	public Integer createCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
	
	public List<Customer> getAllCustomers();
	
	public Optional<Customer>getOneCustomerById(Integer custId);
	
	public boolean isCustomerExist(Integer custId);
	
	public void deleteCustomerByCustId(Integer custId);
}
