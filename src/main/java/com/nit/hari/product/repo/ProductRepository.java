package com.nit.hari.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.hari.model.product.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
