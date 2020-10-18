package com.nit.hari.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.hari.model.customer.Customer;
import com.nit.hari.service.CustomerService;

@RestController
@RequestMapping("/rest/customer")
public class CustomerController {

	@Autowired(required = true)
	private CustomerService custService;

	@PostMapping("/saveCustomer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		try {
			if (customer != null && customer.getCustId() != null && custService.isCustomerExist(customer.getCustId())) {
				return new ResponseEntity<String>("Customer Already Existed", HttpStatus.BAD_REQUEST);
			} else {
				Integer customerId = custService.createCustomer(customer);
				return new ResponseEntity<String>("Customer Created Successfully with Id : " + customerId,
						HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Unable To Create Customer", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getOneCustomer/{custId}")
	public ResponseEntity<?> getOneCustomerById(@PathVariable("custId") Integer custId) {
		try {
			Optional<Customer> optCustomer = custService.getOneCustomerById(custId);
			if (optCustomer != null) {
				Customer customer = optCustomer.get();
				return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Customer Doesn't Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem while fetching Customer Details",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		try {
			List<Customer> custList = custService.getAllCustomers();
			if (custList != null && custList.size() > 0) {
				return new ResponseEntity<List<Customer>>(custList, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No Data Found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem While fecthing Customer Details",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateCustomers")
	public ResponseEntity<?> updateCustomers(@RequestBody Customer customer) {
		try {
			if (customer != null && customer.getCustId() != null && custService.isCustomerExist(customer.getCustId())) {
				custService.updateCustomer(customer);
				return new ResponseEntity<String>("Customer Details Updated Successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Customer Doesn't Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem while updating Customer Details",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteCustomer/{custId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("custId") Integer custId) {
		try {
			if (custService.isCustomerExist(custId)) {
				custService.deleteCustomerByCustId(custId);
				return new ResponseEntity<String>("Customer Deleted Successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Customer Doesn't Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem while Deleting Customer ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
