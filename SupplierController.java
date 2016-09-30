package com.niit.frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Supplier;


@Controller
public class SupplierController {
	
	@Autowired
	private SupplierDAO supplierDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	
	private Supplier supplier;
	
	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	public String listSuppliers(Model model) {

		model.addAttribute("supplier", new Supplier());
		model.addAttribute("supplierList", this.supplierDAO.list());

		return "supplier";
	}
	@RequestMapping(value = "/sadd", method = RequestMethod.POST)
	public String supplier(@ModelAttribute("supplier") Supplier supplier, BindingResult result,
			HttpServletRequest request) {

		supplierDAO.save(supplier);
	
		return "admin";
	}
	@ModelAttribute
	public Supplier returnObject()
	{
		return new Supplier();
	}
	@RequestMapping(value = "/Deletesupplier{id}")
	public ModelAndView Deletesupplier(@Valid @PathVariable("id") String id) throws Exception {

		
		supplier = supplierDAO.get(id);
		ModelAndView mv = new ModelAndView("supplier");
		productDAO.orphanremoval(supplier.getId());
		supplierDAO.delete(supplier);
		mv.addObject("supplierList", supplierDAO.list());
		mv.addObject("listsupp", 0);
		return mv;
	}

	@RequestMapping("sup{id}")
	public ModelAndView sup(@PathVariable("id") String id, ModelMap model){
		ModelAndView mv = new ModelAndView("editsupplier");
		System.out.println("edit supplier");
		
		System.out.println("supplier list");
		model.addAttribute("supplier",this.supplierDAO.get(id));
		model.addAttribute("listSuppliers",this.supplierDAO.list());		
	
		return mv;
	
	}
	@RequestMapping(value = "/editsupplier", method = RequestMethod.POST)
	public ModelAndView editsup(@ModelAttribute("supplier") Supplier supp,BindingResult result,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("adminhome");
		System.out.println("hai");
		supplierDAO.update(supp);
		mv.addObject("supplierList", supplierDAO.list());
		
		return mv;
	}
}