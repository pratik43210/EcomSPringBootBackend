package com.pratik.SpringEcom.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pratik.SpringEcom.model.Product;
import com.pratik.SpringEcom.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	public List<Product>  getAllProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(int id) {
		// we didn't use orElse here because we are
		// handling the not found case in controller
		return productRepo.findById(id).orElse(new Product(-1));
	}

	public Product addOrUpdateProduct(Product product, MultipartFile image) throws IOException{
		
		product.setImageName(image.getOriginalFilename());
		product.setImageType(image.getContentType());
		product.setImageData(image.getBytes());
		
		return productRepo.save(product);
	}

	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}

	public List<Product> searchProducts(String keyword) {
		return productRepo.searchProducts(keyword);
	}
}
