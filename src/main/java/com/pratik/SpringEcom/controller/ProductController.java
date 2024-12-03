package com.pratik.SpringEcom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pratik.SpringEcom.model.Product;
import com.pratik.SpringEcom.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product=productService.getProductById(id);
		if(product.getId()>0 )
			return new ResponseEntity<>(product, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestBody Product product, @RequestPart MultipartFile image){
		Product savedProduct=null;
		
		try {
			savedProduct=productService.addProduct(product,image);
			return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
		}catch(IOException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
