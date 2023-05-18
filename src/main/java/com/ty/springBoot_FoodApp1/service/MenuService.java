package com.ty.springBoot_FoodApp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_FoodApp1.config.ResponseStructure;
import com.ty.springBoot_FoodApp1.dao.Menudao;
import com.ty.springBoot_FoodApp1.dto.Menu;
import com.ty.springBoot_FoodApp1.dto.User;

@Service
public class MenuService {

	@Autowired
	private Menudao menudao;
	
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu) {
		ResponseStructure<Menu> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("saved");
		responseStructure.setData(menudao.saveMenu(menu));
		return new ResponseEntity<ResponseStructure<Menu>>(responseStructure,HttpStatus.CREATED);
		
	}

	public ResponseEntity<ResponseStructure<Menu>> updateMenu(Menu menu, int id) {
		Menu menu2=menudao.updateMenu(menu,id);
		ResponseStructure<Menu> responseStructure=new ResponseStructure<>();
		if(menu2!=null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("updated");
			responseStructure.setData(menu);
			return new ResponseEntity<ResponseStructure<Menu>>(responseStructure,HttpStatus.OK);
		}else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int mid) {
		Menu menu=menudao.deletemenu(mid);
		ResponseStructure<Menu> responseStructure=new ResponseStructure<>();
		if(menu!=null) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("deletd");
			responseStructure.setData(menu);
			return new ResponseEntity<ResponseStructure<Menu>>(responseStructure,HttpStatus.OK);
		}else {
			return null;
		}

	}
	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int mid) {
		Menu menu=menudao.getMenuById(mid);
		ResponseStructure<Menu> responseStructure=new ResponseStructure<>();
		if(menu!=null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(menu);
			return new ResponseEntity<ResponseStructure<Menu>>(responseStructure,HttpStatus.OK);
		}else {
			return null;
		}
		
	}
	public  ResponseEntity<ResponseStructure<Menu>> getMenuByName(String mname) {
		Menu menu=menudao.getMenuByname(mname);
		ResponseStructure<Menu> responseStructure=new ResponseStructure<>();

		if(menu!=null) {
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setMessage("found");
			responseStructure.setData(menu);
			return new ResponseEntity<ResponseStructure<Menu>>(responseStructure,HttpStatus.OK);
		}else {
			return null;
		}
		
	}
	
	
	
	
	
	
	
	
	
}
