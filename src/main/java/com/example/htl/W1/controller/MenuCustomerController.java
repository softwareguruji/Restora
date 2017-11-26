package com.example.htl.W1.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.htl.W1.model.BaseItem;
import com.example.htl.W1.model.CartItem;
import com.example.htl.W1.model.CustomMenuCartItemSelection;
import com.example.htl.W1.model.CustomMenuItem;
import com.example.htl.W1.model.CustomMenuItemOptions;
import com.example.htl.W1.model.FixedMenuItems;
import com.example.htl.W1.model.Menu;
import com.example.htl.W1.model.Order;
import com.example.htl.W1.model.OrderStatus;
import com.example.htl.W1.service.BaseItemService;
import com.example.htl.W1.service.CartItemService;
import com.example.htl.W1.service.CustomMenuItemService;
import com.example.htl.W1.service.FixedMenuItemService;
import com.example.htl.W1.service.MenuService;
import com.example.htl.W1.service.OrderStatusService;
import com.example.model.User;

@Controller
public class MenuCustomerController extends BaseController{

	@Autowired
	FixedMenuItemService fixedMenuItemService;

	@Autowired
	CustomMenuItemService customMenuItemService;

	@Autowired
	MenuService menuService;
	
	@Autowired
	CartItemService cartItemService;

	@Autowired
	BaseItemService baseItemService;

	@Autowired
	OrderStatusService orderStatusService;
	
	@RequestMapping(value={"/", "/fixedMenuList"}, method = RequestMethod.GET)
	public ModelAndView fixedMenuListShow(Principal principal){
		ModelAndView modelAndView = new ModelAndView();

		setupBaseParameter(modelAndView, principal); 
		
		List<FixedMenuItems> fixedMenuItemsList = fixedMenuItemService.getByAll();
		modelAndView.addObject("fixedMenuItemsList", fixedMenuItemsList);
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.FIXED_MENU_ITEM.getText());
		
		modelAndView.setViewName("item_for_customer/fixed_menu_list");
		return modelAndView;
	}
	
	@RequestMapping(value="/customMenuList", method = RequestMethod.GET)
	public ModelAndView customMenuListShow(Principal principal, Long customMenuItemForCustomization){
		ModelAndView modelAndView = new ModelAndView();

		setupBaseParameter(modelAndView, principal); 
		
		List<CustomMenuItem> customizedMenuItemsList = customMenuItemService.getByAll();
		modelAndView.addObject("customMenuItemsList", customizedMenuItemsList);
		modelAndView.addObject("activeHeaderMenu", HeaderLinks.CUSTOM_MENU_ITEM.getText());
		
		modelAndView.addObject("cmiObj1", new CustomMenuItem());
		//modelAndView.addObject("customMenuItemForCustomization", customMenuItemForCustomization);
		if(customMenuItemForCustomization != null){
			modelAndView.addObject("singleCmiObj", customMenuItemService.getByCustomMenuItemId(customMenuItemForCustomization));
		}
		
		modelAndView.setViewName("item_for_customer/customize_menu_list");
		return modelAndView;
	}
	
	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	public ModelAndView addToCart(Principal principal, @ModelAttribute("menuId") String menuId){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = userService.findUserByEmail(principal.getName());
		
		List<Order> orders = orderService.findUnOrderedCartItemsByUser(userObj);
		if(orders != null && !orders.isEmpty()){
			List<CartItem> listCartItem = orders.get(0).getCartItems();
			boolean sameItemFoundInCart = false;
			
			CartItem sameCartItemObj = null;
			
			if(!listCartItem.isEmpty()){
				for (CartItem cartItem : listCartItem) {
					if(cartItem.getMenuId().getMenuId() == Long.parseLong(menuId)){
						sameItemFoundInCart = true;
						sameCartItemObj = cartItem;
					}
				}
			}

			if(sameItemFoundInCart){
				sameCartItemObj.setQuantity(sameCartItemObj.getQuantity()+1);
				cartItemService.save(sameCartItemObj);
			}else{
				
				Menu menuObj = menuService.getByPK(Long.parseLong(menuId));
				
				CartItem item = new CartItem();
				item.setOrder(orders.get(0));
				item.setQuantity(1);
				item.setMenuId(menuObj);
				item.setPrice(menuObj.getFixedMenuItemObj().getPrice());
				cartItemService.save(item);
			}
		}else{
			Order order = new Order();
			order.setUser(userObj);
			order.setOrderDate(new Date());
			
			//Setting up cart Item
			Menu menuObj = menuService.getByPK(Long.parseLong(menuId));
			CartItem item = new CartItem();
			item.setOrder(order);
			item.setQuantity(1);
			item.setMenuId(menuObj);
			item.setPrice(menuObj.getFixedMenuItemObj().getPrice());
			
			List<CartItem> listCartItems = new ArrayList<>();
			listCartItems.add(item);
			order.setCartItems(listCartItems);
			
			orderService.saveNewOrder(order);
		}
		
		//modelAndView.addObject("username",principal.getName());
		
		RedirectView redirectView = new RedirectView("fixedMenuList");
		modelAndView.setView(redirectView);
		return modelAndView;
	}

	
	@RequestMapping(value="/showCustomizationOption", method = RequestMethod.POST)
	public ModelAndView addToCartCustom(Principal principal, @ModelAttribute("menuId") String menuId){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("customMenuItemForCustomization",Long.parseLong(menuId));
		
		RedirectView redirectView = new RedirectView("customMenuList");
		modelAndView.setView(redirectView);
		return modelAndView;
	}
	
	@RequestMapping(value="/addToCartCustom", method = RequestMethod.POST)
	public ModelAndView addToCartCustom(Principal principal, @ModelAttribute("menuId") String menuId, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println("menuId: "+menuId);
		
		Map<String, String[]> parameters = request.getParameterMap();

	    /*for(String key : parameters.keySet()) {
	        System.out.println(key);
	        String[] vals = parameters.get(key);
	        for(String val : vals)
	            System.out.println(" -> " + val);
	    }*/

	    User userObj = userService.findUserByEmail(principal.getName());
		
		List<Order> orders = orderService.findUnOrderedCartItemsByUser(userObj);
		
		Order order = null;
		if(orders == null || orders.isEmpty()){
			order = new Order();
			order.setUser(userObj);
			order.setOrderDate(new Date());
		}else{
			order = orders.get(0);
		}
		
		//List<CartItem> listCartItem = orders.get(0).getCartItems();
		
		CartItem ciobj = new CartItem();
		Menu menuObj = menuService.getByPK(Long.parseLong(menuId));
		
		ciobj.setMenuId(menuObj);
		ciobj.setOrder(order);
		ciobj.setQuantity(1);
		
		List<CustomMenuItemOptions> listCustomMenuItemOptions = menuObj.getCustomMenuItemObj().getMenuItemQuestions();
		
		List<CustomMenuCartItemSelection> lstCustomMenuCartItemSelections = new ArrayList<>();
		
		double totalPrice = 0.0f;
		for (CustomMenuItemOptions cmiobj : listCustomMenuItemOptions) {
			long optionId = cmiobj.getCustomMenuItemOptionId();

			String[] values = parameters.get("custom_selection_"+optionId);
			
			for (String selectedVal : values) {
				
				CustomMenuCartItemSelection cCISObj = new CustomMenuCartItemSelection();
				cCISObj.setRefCartItem(ciobj);
				cCISObj.setRefCustomMenuItemOption(cmiobj);
				BaseItem baseItemObj = baseItemService.getById(Long.parseLong(selectedVal));
				cCISObj.setRefSelectionBaseItem(baseItemObj);
				double currPrice = baseItemObj.getPrice();
				totalPrice += currPrice;
				cCISObj.setPrice(baseItemObj.getPrice());
				
				lstCustomMenuCartItemSelections.add(cCISObj);
			}
			
		}
		
		ciobj.setCustomCartItemSelections(lstCustomMenuCartItemSelections);
		ciobj.setPrice(totalPrice);

		if(order.getOrderId() == 0){
			List<CartItem> cartItems = new ArrayList<CartItem>();
			cartItems.add(ciobj);
			order.setCartItems(cartItems);
			
			orderService.saveNewOrder(order);
		}else{
			cartItemService.save(ciobj);
		}
		
		
		//modelAndView.addObject("customMenuItemForCustomization",1L);
		
		RedirectView redirectView = new RedirectView("customMenuList");
		modelAndView.setView(redirectView);
		return modelAndView;
	}
	
	@RequestMapping(value="/showCart", method = RequestMethod.GET)
	public ModelAndView showCart(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		List<Order> unorderList = orderService.findUnOrderedCartItemsByUser(userObj);
		
		
		if(unorderList != null && !unorderList.isEmpty()){
			double grandTotalPrice = 0.0f;
			
			for (CartItem cartItem : unorderList.get(0).getCartItems()) {
				grandTotalPrice += (cartItem.getPrice() * cartItem.getQuantity());
			}
			
			modelAndView.addObject("grandTotalPrice",grandTotalPrice);
			modelAndView.addObject("unOrderedOrder",unorderList.get(0));
		}
		
		modelAndView.setViewName("item_for_customer/show_user_cart");
		return modelAndView;
	}

	@RequestMapping(value="/cartItemAddRemoveDelete", method = RequestMethod.POST)
	public ModelAndView cartItemAddRemoveDelete(Principal principal, 
												@ModelAttribute("cartItemId") String cartItemId, 
												@ModelAttribute("type") String type){
		ModelAndView modelAndView = new ModelAndView();
		
		if(type != null){
			if(type.equals("ADD")){
				CartItem cartItemObj = cartItemService.getById(Long.parseLong(cartItemId));
				cartItemObj.setQuantity(cartItemObj.getQuantity()+1);
				cartItemService.save(cartItemObj);
			}else if(type.equals("REMOVE")){
				CartItem cartItemObj = cartItemService.getById(Long.parseLong(cartItemId));
				cartItemObj.setQuantity(cartItemObj.getQuantity()-1);
				cartItemService.save(cartItemObj);
			}else if(type.equals("DELETE")){
				cartItemService.deleteById(Long.parseLong(cartItemId));
			} 
		}
		
		RedirectView redirectView = new RedirectView("showCart");
		modelAndView.setView(redirectView);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/orderPlaceCancel", method = RequestMethod.POST)
	public ModelAndView orderPlaceCancel(Principal principal, 
												@ModelAttribute("orderId") String orderId, 
												@ModelAttribute("type") String type){
		ModelAndView modelAndView = new ModelAndView();
		
		if(type != null){
			if(type.equals("PLACE")){
				Order orderObj = orderService.getById(Long.parseLong(orderId));
				orderService.savePlacedOrder(orderObj);
			}else if(type.equals("CANCEL")){
				/*CartItem cartItemObj = cartItemService.getById(Long.parseLong(cartItemId));
				cartItemObj.setQuantity(cartItemObj.getQuantity()-1);
				cartItemService.save(cartItemObj);*/
			} 
		}
		
		RedirectView redirectView = new RedirectView("showCart");
		modelAndView.setView(redirectView);
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/orderComplete", method = RequestMethod.POST)
	public ModelAndView orderComplete(Principal principal, 
												@ModelAttribute("orderId") String orderId, 
												@ModelAttribute("type") String type){
		ModelAndView modelAndView = new ModelAndView();
		
		if(type != null){
			if(type.equals("COMPLETE")){
				Order orderObj = orderService.getById(Long.parseLong(orderId));
				orderService.completeOrder(orderObj);
			} 
		}
		
		RedirectView redirectView = new RedirectView("orderMgmt");
		modelAndView.setView(redirectView);
		return modelAndView;
	}
	
	@RequestMapping(value="/showOrder", method = RequestMethod.GET)
	public ModelAndView showOrder(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		User userObj = setupBaseParameter(modelAndView, principal);
		List<Order> allOrderList = orderService.findAllOrderedCartItemsByUser(userObj);
		
		modelAndView.addObject("allOrderList",allOrderList);
		
		modelAndView.setViewName("item_for_customer/show_order_placed");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/orderMgmt", method = RequestMethod.GET)
	public ModelAndView showAdminOrder(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		setupBaseParameter(modelAndView, principal);
		
		//Open Orders List
		OrderStatus openOrderStatus = orderStatusService.getById(2L);
		List<Order> allOpenOrderList = orderService.findAllOrdersByOrderStatus(openOrderStatus);
		modelAndView.addObject("allOpenOrderList",allOpenOrderList);
		
		//Completed Orders List
		OrderStatus completedOrderStatus = orderStatusService.getById(5L);
		List<Order> allCompletedOrderList = orderService.findAllOrdersByOrderStatus(completedOrderStatus);
		modelAndView.addObject("allCompletedOrderList",allCompletedOrderList);

		modelAndView.addObject("activeHeaderMenu", HeaderLinks.ORDER_MGMT.getText());
		
		modelAndView.setViewName("admin/show_order_admin");
		return modelAndView;
	}
}
