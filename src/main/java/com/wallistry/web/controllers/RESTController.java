package com.wallistry.web.controllers;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallistry.model.Subscription;
import com.wallistry.web.dao.DashboardDAO;

@RestController
public class RESTController {
	@Autowired
	public DashboardDAO dao;

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/subscription")
	public Subscription subscription(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Subscription(counter.incrementAndGet(), String.format(template, name), new Date().toString());
	}

	@RequestMapping("/getsubscriptioninfo")
	public List<Subscription> subscriptionInformation() {
		List<Subscription> subscriptions = dao.getSubscriptionList();
		return subscriptions;
	}
}
