package com.cognixia.jump.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.model.Orders;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.OrderRepository;
import com.cognixia.jump.service.OrderService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

	@MockBean
	OrderService service;
	@MockBean
	OrderRepository repo;
	
	@InjectMocks
	OrderController controller;
	
	@Autowired
	MockMvc mockMvc;
	
	
	@Test
	void testgetAllOrder() throws Exception{
		
		
		List<Orders> order = new ArrayList<Orders>();
		
		String uri = "/students/all";
		
		
		// when getAllStudents() from the service class is called,
		// the actual code isn't run, just return the dummy data we
		// created instead
		when( service.orderList() ).thenReturn( order );
		
		
		
		mockMvc.perform( get(uri) )
				.andDo( print() )
				.andExpect( status().isOk() );
		
		// verify can check how many times a method is called during a test
		verify( service, times(1) ).orderList(); // check this method is only called once
		
		// make sure no more methods from service are being called
		verifyNoMoreInteractions( service );
		
	}
	
	@Test
	void testcreateOrder() {
		fail("Not yet implemented");
	}
	@Test
	void testgetOrder() {
		fail("Not yet implemented");
	}
	@Test
	void testdeleteOrder() {
		fail("Not yet implemented");
	}
	@Test
	void testupdateOrder() {
		fail("Not yet implemented");
	}

}
