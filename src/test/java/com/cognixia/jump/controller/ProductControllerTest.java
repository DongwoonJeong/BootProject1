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
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognixia.jump.filter.JwtRequestFilter;
import com.cognixia.jump.model.Products;
import com.cognixia.jump.repository.ProductRepository;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.service.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

	@MockBean
	ProductService service;
	@MockBean
	ProductRepository repo;
	@Autowired
	MockMvc mockMvc;
	@MockBean
	MyUserDetailsService service2;
	@MockBean
	JwtRequestFilter filter;
	
	@Test
	@WithMockUser
	void testGetAllproducts() throws Exception {
		String uri = "/products/all";
		String name="gasoline";
		List<Products> product = new ArrayList<Products>();
		
		product.add(new Products(1L, name, 3.14, 1, "89"));
		when( service.getAllproduct() ).thenReturn( product );
	
		mockMvc.perform( get(uri) )
		.andDo( print() )
		.andExpect( status().isOk() )
		.andExpect( jsonPath("$.length()").value( product.size() ) )
		.andExpect( jsonPath("$[0].product_id").value( product.get(0).getProduct_id() ) )
		.andExpect( jsonPath("$[0].name").value( product.get(0).getName() ) )
		;

		// verify can check how many times a method is called during a test
		verify( service, times(1) ).getAllproduct(); // check this method is only called once
		
		// make sure no more methods from service are being called
		verifyNoMoreInteractions( service );
	
	
	}
	

	@Test
	void testCreateProduct() {
		fail("Not yet implemented");
	}

	@Test
	void testGetProductById() throws Exception {
		String uri = "/products/product/{id}";
		String name="gasoline";
		Long id = 1L;
		List<Products> product = new ArrayList<Products>();
		
		product.add(new Products(id, name, 3.14, 1, "89"));
		ResponseEntity<Products> re = ResponseEntity.status(200).body(product.get(0));
//		ResponseEntity<?> re = new ResponseEntity<Product>(product.get(0), 200);
		when( service.getProductById(id)).thenReturn(re);
	
		mockMvc.perform( get(uri,id ) )
		.andDo( print() )
		.andExpect( status().isOk() )
		
		.andExpect( jsonPath("$.length()").value( product.size() ) )
		.andExpect( jsonPath("$[0].product_id").value( product.get(0).getProduct_id() ) )
		.andExpect( jsonPath("$[0].name").value( product.get(0).getName() ) )
		;

		// verify can check how many times a method is called during a test
		verify( service, times(1) ).getProductById(id); // check this method is only called once
		
		// make sure no more methods from service are being called
		verifyNoMoreInteractions( service );
	
	}

	@Test
	void testDeleteProduct() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateProduct() {
		fail("Not yet implemented");
	}

}
