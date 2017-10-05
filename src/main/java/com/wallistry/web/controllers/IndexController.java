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
}
