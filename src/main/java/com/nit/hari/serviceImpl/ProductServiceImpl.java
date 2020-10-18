package com.nit.hari.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.hari.model.product.Product;
import com.nit.hari.product.repo.ProductRepository;
import com.nit.hari.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired(required = true)
	private ProductRepository prodRepo;
	
	@Override
	public Integer createProduct(Product product) {
		return prodRepo.save(product).getPrdId();
	}

	@Override
	public void updateProduct(Product product) {
		prodRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return prodRepo.findAll();
	}

	@Override
	public Optional<Product> getOneProduct(Integer id) {
		return prodRepo.findById(id);
	}

	@Override
	public boolean isProductExist(Integer id) {
		return prodRepo.existsById(id);
	}

	@Override
	public void deleteProductById(Integer id) {
		prodRepo.deleteById(id);
	}

}
