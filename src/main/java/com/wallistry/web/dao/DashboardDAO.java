package com.wallistry.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wallistry.model.ProductItem;
import com.wallistry.model.Subscription;

@Repository
public class DashboardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL = "select * from subscription";
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
        this.jdbcTemplate = jdbcTemplate;  
    }
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
	public String getDashboardInfo() throws JSONException{
		String sqlCustomerCount = "select count(email) from customer_details";
		String sqlSubscriberCount = "select count(email) from subscription";
		String sqlYesterdayrevenue = "select coalesce(sum(total_price),0) from bill_details where date(DATE)=date(date_sub(now(),interval 1 day))";
		String sqlTodayRevenue = "select coalesce(sum(total_price),0) from bill_details where date(DATE) = curdate() ";
		String sqlLast7daysRevenue = "select coalesce(sum(total_price),0) from bill_details where date(DATE) between date_sub(NOW(), INTERVAL DAYOFWEEK(NOW())+6 DAY) AND date_sub(NOW(), INTERVAL DAYOFWEEK(NOW())-1 DAY)";
		String sqlLastmonthRevenue = "select coalesce(sum(total_price),0) from bill_details where  `date` >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
		
		int sqlYesterdayrevenueCount = jdbcTemplate.queryForObject(sqlYesterdayrevenue, new Object[] {}, int.class);
		int sqlTodayRevenueCount = jdbcTemplate.queryForObject(sqlTodayRevenue, new Object[] {}, int.class);
		int sqlLast7daysRevenueCount = jdbcTemplate.queryForObject(sqlLast7daysRevenue, new Object[] {}, int.class);
		int sqlLastmonthRevenueCount = jdbcTemplate.queryForObject(sqlLastmonthRevenue, new Object[] {}, int.class);
		int customerCount = jdbcTemplate.queryForObject(sqlCustomerCount, new Object[] {}, int.class);
		int subscriberCount = jdbcTemplate.queryForObject(sqlSubscriberCount, new Object[] {}, int.class);
		JSONArray countArr = new JSONArray();
		JSONObject countObj = new JSONObject();
		countObj.put("customerCount", customerCount);
		countObj.put("subscriberCount", subscriberCount);
		countObj.put("revenueToday", sqlTodayRevenueCount);
		countObj.put("revenueYesterday", sqlYesterdayrevenueCount);
		countObj.put("revenue7days", sqlLast7daysRevenueCount);
		countObj.put("revenue1month", sqlLastmonthRevenueCount);
		countArr.put(countObj);
		return countArr.toString();
	}
	
	public String fetchPendingOrders() throws JSONException{
		String text_sql = "select * from customerlogview where status= 'pending'";
		JSONArray ordersArr = new JSONArray();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(text_sql);
		for(Map<String,Object>row:rows){
			JSONObject obj = new JSONObject();
			obj.put("orderno", row.get("orderno"));
			obj.put("date", row.get("billdate"));
			obj.put("customer_name", row.get("name"));
			obj.put("price", row.get("itemprice"));
			obj.put("quantity", row.get("totalquantity"));
			obj.put("pincode", row.get("pincode"));
			obj.put("paymentmode", row.get("paymentmode"));
			ordersArr.put(obj);
		}
		return ordersArr.toString();
		
	}
	public String fetchOrders(HttpServletRequest request) throws JSONException{
		String text_sql = "select * from customerlogview where status= ?";
		JSONArray orderArr = new JSONArray();
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(text_sql,request.getParameter("status"));
		for(Map<String,Object>row:rows){
			JSONObject obj = new JSONObject();
			System.out.println("St"+ row.get("status"));
			obj.put("orderno", row.get("orderno"));
			obj.put("date", row.get("billdate"));
			obj.put("customer_name", row.get("name"));
			obj.put("price", row.get("itemprice"));
			obj.put("quantity", row.get("totalquantity"));
			obj.put("pincode", row.get("pincode"));
			obj.put("paymentmode", row.get("paymentmode"));
			obj.put("status", row.get("status"));
			obj.put("cancelReason", row.get("Reason"));
			orderArr.put(obj);
		}
        System.out.println(orderArr);
		return orderArr.toString();
	}
	public String updateOrderStatus(HttpServletRequest request){
		String reason ="";
		if(request.getParameter("status").equals("cancelled")){
			reason = request.getParameter("reason");
		}
		String orderUpdateSql = "update bill_details set status=?,reason=? where order_no=?";
		int confirmOrderStatus = jdbcTemplate.update(orderUpdateSql, request.getParameter("status") , reason, request.getParameter("orderno"));
		if(confirmOrderStatus == 1){
			System.out.println("confirmOrderStatus"+  confirmOrderStatus);
			return "success";
		}else{
			System.out.println("confirmOrderStatus"+  confirmOrderStatus);
			return "failed";
		}
	}
	 
	public String fetchBannerText(HttpServletRequest request){
		String text_sql = "select text from banner_text where textname = ?";
		String selectedText = jdbcTemplate.queryForObject(text_sql, new Object[] { request.getParameter("paramId") }, String.class);
		System.out.println(selectedText);
		return selectedText;
	}
	public String uploadBannerText(HttpServletRequest request){
		String text_sql = "update banner_text set text = ? where textname = ?";
		int uploadTextStatus = jdbcTemplate.update(text_sql, request.getParameter("uploadText") ,request.getParameter("paramId"));
		if(uploadTextStatus == 1){
			return "success";
		}else{
			return "failure"; 
		}
	}
	
	public String getCustomerList(){
		String sql = "select * from customer_details";
		JSONArray custdetails = new JSONArray();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for(Map<String,Object> row : rows){
			JSONObject obj = new JSONObject();
			try {
				obj.put("customer_name", row.get("name"));
				obj.put("customer_id",row.get("customer_id"));
				Long totalPrice = jdbcTemplate.queryForObject("select sum(totalprice) from customerlogview where customerid = ? ", new Object[] { row.get("customer_id") }, Long.class);
				Long totalQty = jdbcTemplate.queryForObject("select sum(totalquantity) from customerlogview where customerid = ? ", new Object[] { row.get("customer_id") }, Long.class);
				obj.put("cutomer_totPrice", totalPrice);
				obj.put("customer_totQty", totalQty);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			custdetails.put(obj);
		}	
		return custdetails.toString();
	}
	public String getCustomerBillingDetail(HttpServletRequest request) throws JSONException {
		JSONArray arr = new JSONArray();
		String customerId = request.getParameter("cur_customerId");
		String sql = "select * from customerlogview where customerid = ?"; 
		String sql2 = "select * from customer_details where customer_id = ?";
		JSONObject infoObj = new JSONObject();
		String pattern = "dd/mm/yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		List<Map<String, Object>> cust = jdbcTemplate.queryForList(sql2,customerId);
		for (Map<String, Object> customer : cust){
			infoObj.put("customer_name", customer.get("name"));
			infoObj.put("customer_address", customer.get("address"));
			infoObj.put("customer_email", customer.get("email"));
			infoObj.put("customer_phone", customer.get("phone_number"));
		}
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,customerId);
		
		JSONArray billArr = new JSONArray ();
		for (Map<String, Object> row : rows){
			JSONObject billObj = new JSONObject();
			
			String date = simpleDateFormat.format(row.get("billdate"));
			billObj.put("date", date); 
			billObj.put("price",row.get("itemprice"));
			billObj.put("quantity", row.get("totalquantity"));
			billObj.put("orderno", row.get("orderno"));
			billObj.put("itemname", row.get("itemname"));
			//infoObj.put("billInfo", billObj);
			billArr.put(billObj);
			
		}
		infoObj.put("billInfo", billArr);
		arr.put(infoObj);
		System.out.println(arr);
		return arr.toString();
	}
	public List<ProductItem> getinventoryDetails(){
		String sql ="select * from product_item";
		List<ProductItem> details = new ArrayList<ProductItem>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		System.out.println("rowssss"+rows);
		for (Map<String, Object> row : rows){
			ProductItem productItem = new ProductItem();
			productItem.setId((int)row.get("id"));
			productItem.setName((String) row.get("name"));
			productItem.setQuantity((int) row.get("quantity"));
			productItem.setPrice((int) row.get("price"));
			productItem.setCategory((String) row.get("category"));
			productItem.setStatus((String) row.get("status"));
			System.out.println(productItem);
			details.add(productItem);
			
		}
		return details;
	}
	public String updateProductDetails(HttpServletRequest request){

		String query= "update product_item set price = ?,quantity = ?,status = ? where id= ? ";
		int status =jdbcTemplate.update(query,request.getParameter("prodPrice"),request.getParameter("prodQty"),request.getParameter("status"),request.getParameter("prodId"));
		if(status == 1){
			return "success";
		}else{
			return "failure";
		}
		
	}
	public String bulkOrderAction(HttpServletRequest request){
		String query = "update bill_details set status=? where order_no = ?";
		String orderStatus = request.getParameter("status");
		String[] ordernos = request.getParameterValues("ordernos[]");
		System.out.println("ord status :::"+orderStatus);
		
		int status = 0;
		for (int i = 0; i < ordernos.length; i++) {
			System.out.println(ordernos[i]);
			status =jdbcTemplate.update(query,orderStatus,ordernos[i]);
		}
		if(status == 1){
			return "success";
		}else{
			return "failure";
		}
	}

}
