package com.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.dto.ProductDTO;
import com.product.entity.Product;
import com.product.serviceimpl.ProductServiceImpl;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")	//Frontend Connection
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping("/addProduct")
	public ProductDTO addNewProduct(@RequestBody Product product) {
		return productServiceImpl.addProduct(product);
	}

	@GetMapping("/getById/{p_id}")
	public ProductDTO getProductById(@PathVariable(value = "p_id") int id) {
		return productServiceImpl.getById(id);
	}

	@PutMapping("/updateProduct/{p_id}")
	public String updateProduct(@PathVariable(value = "p_id") int id, @RequestBody ProductDTO product) {

		return productServiceImpl.updateProduct(id, product);
	}

	@DeleteMapping("/deleteProduct/{p_id}")
	public boolean deleteProduct(@PathVariable(value = "p_id") int id) {
		productServiceImpl.deleteProduct(id);

		return true;
	}

	@GetMapping("/getByBrand/{brandName}")
	public List<ProductDTO> getProductByBrandName(@PathVariable(value = "brandName") String name) {
		return productServiceImpl.getProductByBrand(name);
	}
	
	@GetMapping("/getAllProducts")
	public List<ProductDTO> getAllProducts() {
		return productServiceImpl.getAllProducts();
	}
	@GetMapping("/getByCategoryId/{id}")
	public List<ProductDTO> getProductByCategoryId(@PathVariable(value = "id") int id) {
		return productServiceImpl.getByCategoryId(id);
	}

	
	
	 @PostMapping("/{productId}/upload-image")
	    public String handleImageUpload(
	        @PathVariable int productId,
	        @RequestParam("image") MultipartFile image) {

	        if (image != null) {
	            try {
	                // Generate a unique filename for the image to avoid naming conflicts
	                String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();

	                // Set the path where you want to save the image
	                String imagePath = "C:/ECommerceImages/" + filename;

	                File imageFile = new File(imagePath);

	                // Save the image file
	                image.transferTo(imageFile);

	                // Link the image path to the product in your database
	                productServiceImpl.linkImageToProduct(productId, imagePath);

	                return "Image uploaded successfully";
	            } catch (IOException e) {
	                return "Failed to upload image";
	            }
	        } else {
	            return "No image file provided";
	        }
	    }
	
}
