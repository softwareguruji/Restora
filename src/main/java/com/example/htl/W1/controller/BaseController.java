package com.example.htl.W1.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.example.htl.W1.model.CartItem;
import com.example.htl.W1.model.Order;
import com.example.htl.W1.service.OrderService;
import com.example.model.User;
import com.example.service.UserService;

public class BaseController {

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;
	
	User setupBaseParameter(ModelAndView modelAndView, Principal principal){
		
		User userObj = null;
		
		//Adding UserName and Cart Item
		if(principal != null){
			//find and setup user object
			userObj = userService.findUserByEmail(principal.getName());
			
			List<Order> listOrders = orderService.findUnOrderedCartItemsByUser(userObj);
			int cartItemsCount = 0;
			if(listOrders != null && !listOrders.isEmpty()){
				Order orderObj = listOrders.get(0);
				for (CartItem cartItem : orderObj.getCartItems()) {
					cartItemsCount += cartItem.getQuantity();
				}
			}
			modelAndView.addObject("cartItemsCount", cartItemsCount);

			//Setup User to display on screen
			modelAndView.addObject("username",principal.getName());
			
		}
		
		return userObj;
	}
}
