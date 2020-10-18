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

import com.nit.hari.model.product.Product;
import com.nit.hari.service.ProductService;

@RestController
@RequestMapping("/rest/product")
public class ProductController {

	@Autowired(required = true)
	private ProductService prodService;

	@PostMapping("/saveProduct")
	public ResponseEntity<?> saveProduct(@RequestBody Product product) {
		try {
			if (product.getPrdId() != null && prodService.isProductExist(product.getPrdId())) {
				return new ResponseEntity<String>("Product Already Existed", HttpStatus.BAD_REQUEST);
			} else {
				Integer productId = prodService.createProduct(product);
				return new ResponseEntity<String>("Product Created Successfully with Id :" + productId, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Unable TO Save Product", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<?> getAllProducts() {
		try {
			List<Product> productsList = prodService.getAllProducts();
			if (productsList != null && productsList.size() > 0) {
				return new ResponseEntity<List<Product>>(productsList, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("No Data Found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Unable to fetch Product Details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getOneProduct/{prdId}")
	public ResponseEntity<?> getOneProductById(@PathVariable("prdId") Integer prdId) {
		try {
			Optional<Product> optProduct = prodService.getOneProduct(prdId);
			if (optProduct != null) {
				Product product = optProduct.get();
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Product Doesn't Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Unable to fecth Product Details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody Product product) {
		try {
			if (product.getPrdId() != null && prodService.isProductExist(product.getPrdId())) {
				prodService.updateProduct(product);
				return new ResponseEntity<String>("Product Updated Successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Product Doesnot Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem While Updating Product.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteProduct/{prdId}")
	public ResponseEntity<?> deleteProduct(@PathVariable("prdId") Integer prdId) {
		try {
			if (prodService.isProductExist(prdId)) {
				prodService.deleteProductById(prdId);
				return new ResponseEntity<String>("Product Deleted Successfully.", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Product Doesn't Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Unable to Delete the Product", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
