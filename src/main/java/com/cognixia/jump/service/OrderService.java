package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.Products;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.OrderRepository;


@Service
public class OrderService {

	@Autowired
	OrderRepository repo;
	
	
	
	
	//create order.
	public ResponseEntity<?> createOrder(Orders orders){
		orders.setOrder_id(null);
		Orders create = repo.save(orders);
		return ResponseEntity.status(201).body(create);
    }
	
	//get all the orderlist.
	public List<Orders> getAllOrder(){
		return repo.findAll();
	}
	// find order by order number.
	public ResponseEntity<Orders> getOrder(Long id) throws ResourceNotFoundException{
		Optional<Orders> found = repo.findById(id);

		if (found.isEmpty()) {
			throw new ResourceNotFoundException("Product with id = " + id + " was not found");
		}

		return ResponseEntity.status(200).body(found.get());
    }
	
	// update the order.
	public void orderUpdate(Long id, Orders order){
        Orders tempOrder = repo.findById(id).get();
        tempOrder.setOrder_status(order.getOrder_status());

        repo.save(tempOrder);
    }
	
	public boolean deleteOrderById(Long id) {
		
		Optional<Orders> found = repo.findById(id);
		
		if (!found.isEmpty()) {
			repo.delete(found.get());
			return true;
		}
		
		
		return false;
		
	}
	// update
	public Orders get(Long id) {
		return repo.findById(id).get();

	}

	public Orders save(Orders order) {
		return repo.save(order);

	}
	
	public ResponseEntity<?> updateOrder(Orders order, Long id) throws ResourceNotFoundException{
		Optional<Orders> orderOptional = repo.findById(id);
		if(orderOptional.isPresent()) {
		Orders update = get(id);
	        save(order);
	        return ResponseEntity.status(201).body(update);
	    }else
	    	throw new ResourceNotFoundException("Order id does not exist.");
	}
	
	

}


