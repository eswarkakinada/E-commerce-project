package com.niit.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.model.Category;



@Controller

public class CategoryController {

	private static Logger Log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private Category category;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String listCategories(Model model) {

		Log.debug("starting of the method listCategories");
		model.addAttribute("category", category);
		model.addAttribute("categoryList", this.categoryDAO.list());
		Log.debug("End of the method listCategories");
		return "category";
	}
	@ModelAttribute
	public Category returncate()
	{
		return new Category();
	}

	@RequestMapping(value = "/categoryadd", method = RequestMethod.POST)
	public ModelAndView category(@ModelAttribute("category") Category cat,Model model) {
		Log.debug("starting of the method addCategory");

		ModelAndView mv = new ModelAndView("category");
        System.out.println("Test Case 2");
		//if(categoryDAO.get(category.getId()) == null)
		{
			System.out.println("Test case 3");
			categoryDAO.save(cat);
		} 
		//else 
		{
			mv.addObject("error message,if exists with this id" + category.getId());
		}
		Log.debug("ending of the method addcategories");
		model.addAttribute("categoryList", this.categoryDAO.list());
		return mv;

	}
	@RequestMapping(value = "/Deletecategory{id}")
	public ModelAndView deleteCategory(@PathVariable("id") String id) throws Exception {
		System.out.println(id);
		category = categoryDAO.get(id);
		productDAO.orphanremoval(id);
		categoryDAO.delete(category);
		ModelAndView mv = new ModelAndView("category");
		mv.addObject("categoryList", categoryDAO.list());
		
		return mv;
	}
	@RequestMapping(value = "update{id}")
	public ModelAndView editcategory(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("editcategory");
		System.out.println("update");
		category=categoryDAO.get(id);
		System.out.println("getid()");
		mv.addObject("cat", category);
		mv.addObject("categoryList", this.categoryDAO.list());
		
		return mv;
	}
	@RequestMapping(value = "/editc", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("category") Category cat,BindingResult result,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("adminhome");
		System.out.println("hai");
		categoryDAO.update(cat);
		mv.addObject("categoryList", categoryDAO.list());
		
		return mv;
	}

}
