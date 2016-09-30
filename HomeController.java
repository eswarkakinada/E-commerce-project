package com.niit.frontend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.niit.shoppingcart.dao.UserDetailsDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;
import com.niit.shoppingcart.model.UserDetails;

@Controller
public class HomeController {

	@Autowired
	UserDetailsDAO userdetailsDAO;
	@Autowired
	UserDetails userDetails;
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

	@ModelAttribute
	public UserDetails returnObject() {

		return new UserDetails();
	}

	@RequestMapping("/")
	public ModelAndView showIndex() {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("message", "thank for visit");
		return mv;
		// return new ModelAndView("bootstrap");
	}

	@RequestMapping("/category")
	public ModelAndView category() {
		ModelAndView mv = new ModelAndView("category");
		System.out.println("in category page");
		return mv;
	}

	

	@RequestMapping("/supplier")
	public ModelAndView supplier() {
		ModelAndView mv = new ModelAndView("supplier");
		System.out.println("in supplier page");
		return mv;
	}

	/*@RequestMapping(value = "/asup", method = RequestMethod.POST)
	public ModelAndView category(@ModelAttribute("supplier") Supplier supplier, BindingResult result,
			HttpServletRequest request) throws IOException {

		System.out.println("processing to add supplier");
		System.out.println(supplier.getId());
		System.out.println(supplier.getName());
		System.out.println(supplier.getAddress());
		ModelAndView mv = new ModelAndView("supplier");
		supplierDAO.save(supplier);
		return mv;
	}
	


	

	@RequestMapping("/deletesupplier")
	public ModelAndView deletesup() {
		System.out.println("delete supplier");
		ModelAndView mv = new ModelAndView("deletesupplier");
		return mv;
	}

	@ModelAttribute
	public Supplier returndelete1() {
		return new Supplier();
	}

	@RequestMapping("dsup")
	public ModelAndView deleteSupplier(@ModelAttribute("supplier") Supplier supplier, BindingResult result,
			HttpServletRequest request) throws IOException {

		ModelAndView mv = new ModelAndView("deletesupplier");
		if (category == null) {
			mv.addObject("errorMessage", "could not delete the supplier");
		} else {
			supplierDAO.delete(supplier);
		}
		return mv;
	}
*/
	@RequestMapping("/signup")
	public ModelAndView Signup() {

		ModelAndView mv = new ModelAndView("signup");
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView signup(@ModelAttribute("userdDetails") UserDetails user, BindingResult result,
			HttpServletRequest request) throws IOException {
		System.out.println("In signup page");
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		System.out.println(user.getPassword());
		System.out.println(user.getCpassword());
		if (user.getCpassword().equals(user.getPassword())) {
			userdetailsDAO.save(user);
		} else {
			System.out.println("Password and confirm password not matches ");
		}
		return new ModelAndView("signin");
	}

	
	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");

		return mv;
	}

	@RequestMapping("/signin")
	public ModelAndView Signin() {
		ModelAndView mv = new ModelAndView("signin");
		System.out.println("in sign in page");
		return mv;
	}

	@RequestMapping(value = "/login_session_attributes")
	public String login_session_attributes(HttpSession session, Model model,@RequestParam(value="username")String id) {
		Object name = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("inside security check");

		session.setAttribute("name", name);
		System.out.println(name);

		userDetails = userdetailsDAO.get(id);
		session.setAttribute("loggedInUser", userDetails.getName());
		session.setAttribute("loggedInUserID", userDetails.getId());

		session.setAttribute("LoggedIn", "true");

		@SuppressWarnings("unchecked")
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();

		String role = "ROLE_USER";
		for (GrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals(role)) {
				System.out.println(role);
				return "home";
			} else {
				session.setAttribute("isAdmin",1);
			}
		}
		return "adminhome";

	}

	@RequestMapping("/perform_logout")
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		ModelAndView mv = new ModelAndView("home");
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute("category", category);
		session.setAttribute("categoryList", categoryDAO.list());
        
		mv.addObject("logOutMessage", "You have successfully logged out!");
		mv.addObject("loggedout", "true");

		return mv;
	}
	
	
}
