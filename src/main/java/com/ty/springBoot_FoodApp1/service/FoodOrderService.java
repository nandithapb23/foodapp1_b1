package com.ty.springBoot_FoodApp1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_FoodApp1.config.ResponseStructure;
import com.ty.springBoot_FoodApp1.dao.FoodOrderDao;
import com.ty.springBoot_FoodApp1.dto.FoodOrder;
import com.ty.springBoot_FoodApp1.dto.Items;
import com.ty.springBoot_FoodApp1.dto.Menu;

@Service
public class FoodOrderService {

	@Autowired
	private FoodOrderDao dao;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder) {
		ResponseStructure<FoodOrder> responseStructure=new ResponseStructure<>();
		List<Items> list = foodOrder.getItems();
		double totalprice = 0;
		for (Items items : list) {
			totalprice += items.getCost() * items.getQuantity();
			foodOrder.setTotalprice(totalprice);
		}
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(dao.saveFoodOrder(foodOrder));
		return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(FoodOrder foodOrder, int id) {
		FoodOrder foodOrder2 = dao.getFoodOrder(id);
		ResponseStructure<FoodOrder> responseStructure=new ResponseStructure<>();
		if (foodOrder2 != null) {
			List<Items> list = foodOrder2.getItems();
			double totalprice = 0;
			for (Items items : list) {
				totalprice += items.getCost() * items.getQuantity();
			}
			foodOrder.setTotalprice(totalprice);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(dao.updateFoodOrder(foodOrder2, id));
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.OK);
		} else {
			return null;
		}

	}

	public ResponseEntity<ResponseStructure<FoodOrder>> deleteFoodOrder(int id) {
		FoodOrder foodOrder = dao.deleteFoodOrder(id);
		ResponseStructure<FoodOrder> responseStructure=new ResponseStructure<>();
		if (foodOrder != null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(foodOrder);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.OK);
		} else {
			return null;
		}

	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrder(int id) {
		FoodOrder foodOrder = dao.getFoodOrder(id);
		ResponseStructure<FoodOrder> responseStructure=new ResponseStructure<>();
		if (foodOrder != null) {
			List<Items> list = foodOrder.getItems();
			double totalprice = 0;
			for (Items items : list) {
				totalprice += items.getCost() * items.getQuantity();
				foodOrder.setTotalprice(totalprice);
			}
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(foodOrder);
			return new ResponseEntity<ResponseStructure<FoodOrder>>(responseStructure,HttpStatus.OK);		
			} else {
			return null;
		}

	}

}
