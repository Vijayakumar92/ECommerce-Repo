package com.product.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductDTO addProduct(Product product) {
		productRepository.save(product);
		
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setBrand(product.getBrand());
		productDTO.setCategoryId(product.getCategoryId());
		productDTO.setProductId(product.getProductId());
		productDTO.setProductImage(product.getProductImage());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductPrice(product.getProductPrice());

		
		return productDTO;
	}

	public ProductDTO getById(int id) {
		Product product = productRepository.findById(id).get();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setBrand(product.getBrand());
		productDTO.setCategoryId(product.getCategoryId());
		productDTO.setProductId(product.getProductId());
		productDTO.setProductImage(product.getProductImage());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductPrice(product.getProductPrice());

		return productDTO;

	}

	public String updateProduct(int id, ProductDTO productDTO) {

		Product product;
		try {
			product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not found by the given Id"));

			if (product.getProductName() != null)
				product.setProductName(productDTO.getProductName());
			if (product.getBrand() != null)
				product.setBrand(productDTO.getBrand());
			if (!(product.getCategoryId() <=0) )
				product.setCategoryId(productDTO.getCategoryId());
			if (product.getProductImage() != null)
				product.setProductImage(productDTO.getProductImage());
			if (product.getProductPrice() != 0)
				product.setProductPrice(productDTO.getProductPrice());

			productRepository.save(product);
		} catch (ProductNotFoundException e) {
			System.out.println(e);
			return "Product data not updated";
		}
		return "Product updated Successfully";

	}

	public boolean deleteProduct(int id) {

		productRepository.deleteById(id);
		return true;

	}

	public List<ProductDTO> findAll() {
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setBrand(product.getBrand());
			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}
		return productDTOs;

	}

	public List<ProductDTO> getProductByBrand(String brandName) {

		List<Product> products = productRepository.findByBrand(brandName.toLowerCase());

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();

			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setBrand(brandName);
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	public List<ProductDTO> getAllProducts() {

		List<Product> products = productRepository.findAll();

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();

			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setBrand(product.getBrand());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	public List<ProductDTO> getByCategoryId(int id) {
		List<Product> products = productRepository.findByProductsByCatogory(id);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {

			ProductDTO productDTO = new ProductDTO();
			productDTO.setBrand(product.getBrand());
			productDTO.setCategoryId(product.getCategoryId());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	public void linkImageToProduct(int productId, String imagePath) {
		Product product = productRepository.findById(productId).get();
		product.setProductImage(imagePath);

		productRepository.save(product);

	}

}

