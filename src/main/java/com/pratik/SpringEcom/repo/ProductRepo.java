package com.pratik.SpringEcom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pratik.SpringEcom.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
