package com.niit.frontend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;
@Controller
public class ProductController {
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	Category category;
	@Autowired
	SupplierDAO supplierDAO;
	@Autowired
	Product product;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	Supplier supplier;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public String listSuppliers(Model model) {

		model.addAttribute("product", new Product());
		model.addAttribute("productList", this.productDAO.list());
		model.addAttribute("category", category);
		model.addAttribute("categoryList", this.categoryDAO.list());
		model.addAttribute("supplier", supplier);
		model.addAttribute("supplierList", this.supplierDAO.list());


		return "product";
	}

	
	@ModelAttribute
	public Product returnObject1() {
		return new Product();
	}

	@RequestMapping(value = "/aprod", method = RequestMethod.POST)
	public ModelAndView createProduct(@ModelAttribute("product") Product p1, @RequestParam("image") MultipartFile file,
			Model model, BindingResult result, HttpServletRequest request) throws IOException {

		System.out.println("in add prod");
		ModelAndView mv = new ModelAndView("product");
		@SuppressWarnings("unused")
		String filename;
		@SuppressWarnings("unused")
		byte[] bytes;
		System.out.println("image");
		MultipartFile image = p1.getImage();
		Path path;
		path = Paths.get("F:/DT_Java/Frontend/src/main/webapp/resources/images/" + p1.getId() + ".jpg");

		System.out.println("Path = " + path);
		System.out.println("File name = " + p1.getImage().getOriginalFilename());
		if (image != null && !image.isEmpty()) {
			try {
				image.transferTo(new File(path.toString()));
				System.out.println("Image Saved in:" + path.toString());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Image not saved");
			}
		}

		System.out.println(p1.getSupplier().getId());
		Category category = categoryDAO.get(p1.getCategory().getId());
		System.out.println(p1.getCategory().getId());

		Supplier supplier = supplierDAO.get(p1.getSupplier().getId());

		System.out.println("2");
       
		p1.setCategory(category);
		p1.setSupplier(supplier);
		System.out.println("3");
		p1.setCid(category.getId());
		p1.setSid(supplier.getId());
		productDAO.save(p1);
		return mv;
	}
	@RequestMapping(value = "/eproduct{id}")
	public ModelAndView UpdatecatPage(@PathVariable("id") String id) throws Exception {
		ModelAndView mv = new ModelAndView("editproduct");
		System.out.println(id);
		product = productDAO.get(id);
		mv.addObject("product", product);
		mv.addObject("categoryList", categoryDAO.list());
		mv.addObject("supplierList", supplierDAO.list());
		
		return mv;
	}
	@RequestMapping(value = "/updateprod", method = RequestMethod.POST)
	public ModelAndView Updateproduct(@Valid @ModelAttribute("product") Product prod, BindingResult result,
			HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView("adminhome");
		category = categoryDAO.get(prod.getCategory().getId());
		supplier = supplierDAO.get(prod.getSupplier().getId());
		prod.setSupplier(supplier);
		prod.setCategory(category);
		prod.setCid(category.getId());
		prod.setSid(supplier.getId());
		productDAO.update(prod);
		//mv.addObject("listprod", 0);
		return mv;
	}
	@RequestMapping("dproduct/{id}")
	public String removeSupplier(@ModelAttribute("product") Product prod) throws Exception {
	

		
		ModelAndView mv = new ModelAndView("product");
		
		if(product==null)
			
		{
			mv.addObject("error messaege","could not delete the supplier");
		}
		else
		{
			productDAO.delete(prod);
		}
		return "product";
	}
	@RequestMapping("/productdisplay")
	public ModelAndView viewItems() throws JsonGenerationException, JsonMappingException, IOException {
		List<Product> list = productDAO.list();
		System.out.println("user list=" + list);
		ObjectMapper om = new ObjectMapper();
		String listjson = om.writeValueAsString(list);
		System.out.println(listjson);
		return new ModelAndView("productdisplay", "listofitem", listjson);
	}

	@RequestMapping("/viewdetails")
	public ModelAndView productViewDetails(@RequestParam("id") String id,Model model) {
		System.out.println("I am in productViewDetails");
		System.out.println("ID:" + id);
		
		model.addAttribute("productList", this.productDAO.list());
		Product product = productDAO.get(id);
		return new ModelAndView("viewdetails", "product", product);
	}

	String setName;
	List<Product> plist;

	@SuppressWarnings("unchecked")
	@RequestMapping("/GsonCon")
	public @ResponseBody String getValues() throws Exception {
		String result = "";
		plist = productDAO.list();
		Gson gson = new Gson();
		result = gson.toJson(plist);
		return result;

	}
	

	


}
