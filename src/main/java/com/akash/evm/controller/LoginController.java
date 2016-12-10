package com.akash.evm.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	@RequestMapping(value = { "/", "/login", "/logout" }, method = RequestMethod.GET)
	public String getIndexPage() {
		logger.info("Inside the class " + this.getClass().getName());
		return "Login";
	}

}