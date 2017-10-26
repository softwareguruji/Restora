package com.example.htl.W1.model;

import java.util.List;

/*@Component("cart")
@ComponentScan*/
/*@Configuration("cart")
@ComponentScan("com.example.htl.W1.model")*/
/*@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.INTERFACES)*/
public class Cart {

	private List<CartItem> cartItems;

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
}
