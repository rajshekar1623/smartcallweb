package com.aakhya.smartcall.application.product.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aakhya.smartcall.application.admin.entity.RecordStatusType;
import com.aakhya.smartcall.application.product.entity.Product;
import com.aakhya.smartcall.application.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(String searchTerm){
		if(null != searchTerm && searchTerm.length() > 0)
			return productRepository.findAllProducts(searchTerm);
		else
			return productRepository.findAll();
	}
	
	public void save(Product product) {
		if(null != product) {
			productRepository.save(product);
		}
	}
	
	public void delete(Product product) {
		if(null != product) {
			product.setStatus(RecordStatusType.DELETED.getValue());
			productRepository.save(product);
		}
	}
	
	public void deleteProducts(Set<Product> products) {
		if(null != products && !products.isEmpty()) {
			for(Product product:products) {
				delete(product);
			}
		}
	}
}
