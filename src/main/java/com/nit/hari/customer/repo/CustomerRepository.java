package com.nit.hari.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.hari.model.customer.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
