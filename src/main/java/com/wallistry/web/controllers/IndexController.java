package com.wallistry.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
<<<<<<< HEAD
	 @RequestMapping("/")
		public String home(){
			return "index";
		}
	 @RequestMapping("/bottle")
		public String bottle(){
			return "bottle";
		}
	 @RequestMapping("/coasters")
		public String coasters(){
			return "coasters";
		}
=======
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/bottle")
	public String bottle() {
		return "bottle";
	}

	@RequestMapping("/notebook")
	public String notebook() {
		return "notebook";
	}
>>>>>>> develop
}
