package com.niit.frontend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;

@Controller
public class AdminController {

	@Autowired
	private Category category;

	@Autowired
	private Product product;

	@Autowired
	private Supplier supplier;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private SupplierDAO supplierDAO;


	@RequestMapping("/editcategory")
	public ModelAndView update() {
		System.out.println("In edit prod page");
		ModelAndView mv = new ModelAndView("editcategory");
		return mv;
	}

	@ModelAttribute
	public Category returnUpdate() {
		return new Category();
	}

	@RequestMapping(value = "/ecat", method = RequestMethod.POST)
	public ModelAndView updateCategory(@ModelAttribute("category") Category objct, BindingResult result,
			HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("adminhome");
		categoryDAO.update(objct);
		return mv;
	}

	@RequestMapping("/editsupplier")
	public ModelAndView update1() {
		System.out.println("In edit prod page");
		ModelAndView mv = new ModelAndView("editsupplier");
		return mv;
	}

	@ModelAttribute
	public Supplier returnUpdate1() {
		return new Supplier();
	}

	@RequestMapping(value = "/esup", method = RequestMethod.POST)
	public ModelAndView updateSupplier(@ModelAttribute("supplier") Supplier objct, BindingResult result,
			HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("adminhome");
		supplierDAO.update(objct);
		return mv;
	}

}
