package com.nit.hari.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.hari.customer.repo.CustomerRepository;
import com.nit.hari.model.customer.Customer;
import com.nit.hari.service.CustomerService;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired(required = true)
	private CustomerRepository custRepo;
	
	@Override
	public Integer createCustomer(Customer customer) {
		return custRepo.save(customer).getCustId();
	}

	@Override
	public void updateCustomer(Customer customer) {
		custRepo.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public Optional<Customer> getOneCustomerById(Integer custId) {
		return custRepo.findById(custId);
	}

	@Override
	public boolean isCustomerExist(Integer custId) {
		return custRepo.existsById(custId);
	}

	@Override
	public void deleteCustomerByCustId(Integer custId) {
		custRepo.deleteById(custId);
	}

}
