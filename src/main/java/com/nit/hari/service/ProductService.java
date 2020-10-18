package com.nit.hari.service;

import java.util.List;
import java.util.Optional;

import com.nit.hari.model.product.Product;


public interface ProductService {

	public Integer createProduct(Product product);
	
	public void updateProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public Optional<Product> getOneProduct(Integer id);
	
	public boolean isProductExist(Integer id);
	
	public void deleteProductById(Integer id);
	
	
};
