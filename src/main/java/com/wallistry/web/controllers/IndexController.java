package com.wallistry.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/bottle")
	public String bottle() {
		return "bottle";
	}
	
	@RequestMapping("/homeware")
	public String homeware(){
		return "homeware";
	}
	
	@RequestMapping("/coasters")
	public String coasters() {
		return "coasters";
	}

	@RequestMapping("/notebook")
	public String notebook() {
		return "notebook";
	}

	@RequestMapping("/buynow")
	public String buynow() {
		return "buy-now";
	}

	@RequestMapping("/privacy-policy")
	public String privacy() {
		return "privacy-policy";
	}

	@RequestMapping("/return-policy")
	public String returnPolicy() {
		return "return-policy";
	}

	@RequestMapping("/contact-us")
	public String contactUs() {
		return "contact-us";
	}
	@RequestMapping("/about-us")
	public String aboutUs() {
		return "about-us";
	}
	@RequestMapping("/terms-conditions")
	public String termsAndCondition() {
		return "terms-conditions";
	}
	@RequestMapping("/faqs")
	public String faqs(){
		return "faqs";
	}
	@RequestMapping("/corporate-gifting")
		public String corpGift(){
			return "corporategifting";
	}
	@RequestMapping("/event-gifting")
		public String eventGift(){
		return "eventgifting";
	}
	@RequestMapping("/stationery")
		public String stationery(){
		return "stationery";
	}
	@RequestMapping("/diybook")
		public String diybook(){
		return "diynote";
	}
	@RequestMapping("/seller-home")
		public String sellerHome(){
			return "sellerhome";
	}
	@RequestMapping("/seller-dashboard")
	public String sellerDashboard(){
		return "seller-dashboard";
	}
	@RequestMapping("/seller-inventory")
	public String sellerInventory(){
		return "seller-inventory";
	}
	@RequestMapping("/seller-subscriberlog")
	public String subscriberlog(){
		return "subscriberlog";
	}
	@RequestMapping("/seller-customerlog")
	public String customerlog(){
		return "customerlog";
	}
	@RequestMapping("/seller-orders")
	public String sellerOrders(){
		return "seller-orders";
	}
	@RequestMapping("/seller-login")
	public String login(){
		return "seller-login";
	}
}
