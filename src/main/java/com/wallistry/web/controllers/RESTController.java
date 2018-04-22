package com.wallistry.web.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	@RequestMapping(value="/uploadBannerImage",method = RequestMethod.POST)
	public @ResponseBody String uploadBannerImage(MultipartHttpServletRequest request) throws IOException{
		 Iterator<String> itrator = request.getFileNames();
	        MultipartFile multiFile = request.getFile(itrator.next());
	        System.out.println(multiFile);
	        
	        //making directories for our required path.
            byte[] bytes = multiFile.getBytes();
            String path=request.getServletContext().getRealPath("/");
            System.out.println(path);
            File directory=    new File("Downloads/wallistry-terms/src/main/resources/static/images/BannerImage");
            if (!directory.exists())
    			directory.mkdirs();
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
