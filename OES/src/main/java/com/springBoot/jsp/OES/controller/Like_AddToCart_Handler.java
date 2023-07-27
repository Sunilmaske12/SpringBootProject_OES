package com.springBoot.jsp.OES.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Like_AddToCart_Handler {
	
	@GetMapping("/likeProduct")
	public String showLike() {
		return "like_product";
	}
	
	@GetMapping("/shoppingCart")
	public String getCart() {
		return "shoping-cart";
	}
}
