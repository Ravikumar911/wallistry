package com.wallistry.web.controllers;

import java.awt.image.BufferedImage;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.wallistry.model.Customer;
import com.wallistry.model.ProductItem;
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
	public void subscriptionInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Subscription> subscriptions = dao.getSubscriptionList();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(subscriptions, new TypeToken<List<ProductItem>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);

	}
	@RequestMapping("/dashboardInfo")
	public String getDashboardInfo() throws JSONException{
		String dahboardInfo = dao.getDashboardInfo();
		return dahboardInfo;
	}
	@RequestMapping("/fetchBannerText")
	public String fetchBannerText(HttpServletRequest request){
		String fetchText = dao.fetchBannerText(request);
		return fetchText;
	}
	@RequestMapping("/uploadBannerText")
	public String uploadBannerText(HttpServletRequest request){
		String uploadText = dao.uploadBannerText(request);
		return uploadText;
	}
	@RequestMapping("/getCustomerinfo")
	public String customerInformation(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String subscriptions = dao.getCustomerList();
		return subscriptions;
	}
	@RequestMapping("/getInventorydetails")
	public void inventoryDetails(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<ProductItem> details = dao.getinventoryDetails();
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(details, new TypeToken<List<ProductItem>>() {
		}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		//return details.toString();
	}
	@RequestMapping("/getCustomerBillinginfo")
	public String getCustomerBillingDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException, ParseException{
		String result = dao.getCustomerBillingDetail(request);
		return result;
	}
	@RequestMapping("/updateProductDetails")
	public String updateProductDetails(HttpServletRequest request){
		//System.out.println(request+"   88888888888   " +request.getParameter("prodName"));
		String result = dao.updateProductDetails(request);
		return result;
	}
	@RequestMapping("/fetchPendingOrders")
	public String updatePendingOrders() throws JSONException{
		String result = dao.fetchPendingOrders();
		return result;
	}
	@RequestMapping("/fetchOrdersStatus")
	public String updateOrders(HttpServletRequest request) throws JSONException{
		System.out.println("fetchorders  "+request.getParameter("status"));
		String result = dao.fetchOrders(request);
		return result;
	}
	@RequestMapping("/updateOrderStatus")
	public String updateOrderStatus(HttpServletRequest request){
		String result = dao.updateOrderStatus(request);
		return result;
	}
	@RequestMapping("/bulkorderaction")
	public String bulkOrderAction(HttpServletRequest request){
		String result = dao.bulkOrderAction(request);
		return result;
	}
	@RequestMapping(value="/uploadBannerImage",method = RequestMethod.POST)
	public @ResponseBody String uploadBannerImage(MultipartHttpServletRequest request) throws IOException{
		 Iterator<String> itrator = request.getFileNames();
	        MultipartFile multiFile = request.getFile(itrator.next());
	        System.out.println(multiFile);
	        
	        //making directories for our required path.
            byte[] bytes = multiFile.getBytes();
            String path=request.getServletContext().getRealPath("/");
            System.out.println(path);
            File directory= new File("/Users/MAVJAY/Downloads/wallistry-terms/src/main/resources/static/images/BannerImage");
            if (!directory.exists())
    			directory.mkdirs();
            String ext = multiFile.getContentType();
            String ext1 = ext.substring(ext.indexOf("/")+1);
    		File destination1 = new File(directory + "/" + multiFile.getName()+".png");
    		BufferedImage src;
    		try {
    			src = ImageIO.read(new ByteArrayInputStream(bytes));
    			ImageIO.write(src, "png", destination1);
    			return "success";
    		} catch (Exception e) {
    			return "failure";
    		}
    		
    		
    		
    		
            //String  name= multiFile.getName();
            
		//System.out.println(allRequestParams);
		
		//data = allRequestParams.get("data");
		//System.out.println(data);
		
	}

}
