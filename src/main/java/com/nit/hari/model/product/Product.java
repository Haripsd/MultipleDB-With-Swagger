package com.nit.hari.model.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_TAB")
public class Product {

	@Id
	@GeneratedValue
	private Integer prdId;
	
	private String prdName;
	
	private String prdBrand;
	
	private Double prdCost;
}
