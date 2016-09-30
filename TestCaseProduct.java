package com.niit.ShoopingcartBack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.model.Product;

public class TestCaseProduct {

	// to write test case for product ,we need productDAO and Product object
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	Product product;
	
	
	AnnotationConfigApplicationContext context;
	
	// you can write a method to initialise the objects
	
	@Before
	public void init(){
		
		//in this context  weneed to create object
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		
		productDAO =(ProductDAO) context.getBean("productDAO");
		product =(Product)context.getBean("productDAO");
		
		}
	/*@Test
	public void deleteProductTestCase(){
		
		product.setId("Harika_1");
		
		boolean flag =ProductDAO.delete(product);
		
		assertEquals("deleteProductTestCase",flag, false);
	}*/
	
	@Test
	public void addProductTestCase(){
		
	product.setId("ABC12");
	product.setName("i phone");
	product.setDescription("this is i phone");
	product.setPrice(65000);
	
	//assertEquals("addProductTestCase" product.save(product), true);
	
	}
	@Test 
	public void listProductTestCase()
	{
		assertEquals("addProductTestCase",productDAO.list().size() ,2);
	
	}
	@Test
	public void updateProductTestCase(){
	   product.setId("Mob04");
	   product.setPrice(2000);
	   assertEquals("updateProductTestCase",productDAO.get("Mob004"),null);
	}
	}
