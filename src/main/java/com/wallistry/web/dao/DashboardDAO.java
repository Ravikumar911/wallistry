package com.wallistry.web.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wallistry.model.Subscription;

@Repository
public class DashboardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL = "select * from subscription";

	public List<Subscription> getSubscriptionList() {

		List<Subscription> subscriptions = new ArrayList<Subscription>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);

		for (Map<String, Object> row : rows) {
			Subscription subscription = new Subscription();
			subscription.setId((int) row.get("id"));
			subscription.setEmail((String) row.get("email"));
			subscription.setDate(row.get("date").toString());

			subscriptions.add(subscription);
		}

		return subscriptions;
	}

}
