package com.product.service;

import java.util.List;

import com.product.dto.ProductDTO;
import com.product.entity.Product;

public interface ProductService {
	
	public ProductDTO addProduct(Product product);
	
	public List<ProductDTO> findAll();
	
	public String updateProduct(int id,ProductDTO productDTO);
	
	public ProductDTO getById(int id);
	
	public boolean deleteProduct(int id);
	
	public List<ProductDTO> getProductByBrand(String brandName);
	
	

}
