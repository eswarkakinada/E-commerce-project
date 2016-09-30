package com.niit.frontend.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDetailsDAO;
import com.niit.shoppingcart.model.Cart;
import com.niit.shoppingcart.model.Product;

public class CartController {
	@Autowired
	CartDAO cartDAO;
	@Autowired
	Cart cart;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	Product product;
	@Autowired
	UserDetailsDAO userDetailsDAO;

	@ModelAttribute
	public Cart returnObject() {
		return new Cart();
	}

	
	@RequestMapping(value = "/buy{id}&{pid}", method = RequestMethod.POST)
	public ModelAndView buyproductPage(@Valid @PathVariable("id") String id, @PathVariable("pid") String pid,@RequestParam("quantity")String quantity,
			HttpSession session) throws Exception {
		int k = Integer.parseInt(quantity);
		ModelAndView mv = new ModelAndView("mycart");
		cart.setQuantity(k);
		cart.setUser_Id(id);
		cart.setProd_Id(pid);
		cart.setCartuser(userDetailsDAO.get(id));
		product = productDAO.get(pid);
		cart.setCartproduct(product);
		cart.setPrice(cart.getQuantity()*product.getPrice());
		cartDAO.save(cart);
		mv.addObject("mycartList", cartDAO.mycartproducts(id));
		session.setAttribute("cartvalue", cartDAO.totalproducts(id));
		return mv;
	}

	@RequestMapping(value = "/viewmycart{id}")
	public ModelAndView viewmycart(@PathVariable("id") String id) throws Exception {
		ModelAndView mv = new ModelAndView("mycart");
		mv.addObject("mycartList", cartDAO.mycartproducts(id));
		System.out.println(cartDAO.totalprice(id));
		return mv;
	}

	@RequestMapping(value = "/cartitemdelete{id}&{pid}")
	public ModelAndView deleteCategory(@PathVariable("id") String id, @PathVariable("pid") String pid,
			HttpSession session) throws Exception {
		System.out.println(id);
		System.out.println(pid);
		cartDAO.delete(id, pid);
		ModelAndView mv = new ModelAndView("mycart");
		mv.addObject("mycartList", cartDAO.mycartproducts(id));
		session.setAttribute("cartvalue", cartDAO.totalproducts(id));
		System.out.println(cartDAO.totalprice(id));
		return mv;
	}
}
