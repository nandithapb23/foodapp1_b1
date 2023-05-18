package com.ty.springBoot_FoodApp1.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_FoodApp1.config.ResponseStructure;
import com.ty.springBoot_FoodApp1.dao.Productdao;
import com.ty.springBoot_FoodApp1.dto.Product;
import com.ty.springBoot_FoodApp1.dto.User;
import com.ty.springBoot_FoodApp1.exception.ProductIdNotFound;

@Service
public class ProductService {

	@Autowired
	private Productdao productdao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(productdao.saveProduct(product));
		return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int id) {
		Product product2 = productdao.updateProduct(product, id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		if (product2 != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		} else {
			throw new ProductIdNotFound("product id is not present");
		}

	}

	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int id) {
		Product product = productdao.deleteProduct(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		if (product != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deleted");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {
		Product product = productdao.getProductById(id);
		ResponseStructure<Product> responseStructure = new ResponseStructure<>();
		if (product != null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(product);
			return new ResponseEntity<ResponseStructure<Product>>(responseStructure, HttpStatus.OK);
		}
		return null;
	}

//	public ResponseEntity<ResponseStructure<List<Product>>> getAllProduct(int mid) {
//		List<Product> product=productdao.findallproducts(mid);
//		ResponseStructure<List<Product>> responseStructure=new ResponseStructure<List<Product>>();
//		if(product!=null) {
//			responseStructure.setStatus(HttpStatus.OK.value());
//			responseStructure.setMessage("found");
//			responseStructure.setData(product);
//			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure,HttpStatus.OK);			
//	}else {
//		throw new NoSuchElementException();
//	}
//	}
}
