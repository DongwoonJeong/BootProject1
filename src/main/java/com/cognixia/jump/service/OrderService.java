package com.cognixia.jump.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository repo;
	
	//create order.
	public void createOrder(User user){
        Orders order = new Orders();
        order.setUser(user);
        repo.save(order);
    }
	
	//get all the orderlist.
	public List<Orders> orderList(){
		return repo.findAll();
	}
	// find order by order number.
	public Orders orderView(Long id){
        return repo.findById(id).get();
    }
	
	// update the order.
	public void orderUpdate(Long id, Orders order){
        Orders tempOrder = repo.findById(id).get();
        tempOrder.setOrder_status(order.getOrder_status());

        repo.save(tempOrder);
    }
	
}


