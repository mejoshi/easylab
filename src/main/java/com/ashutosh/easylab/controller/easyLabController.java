package com.ashutosh.easylab.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashutosh.easylab.entity.User;
import com.ashutosh.easylab.services.UserService;
import com.google.gson.Gson;

@Controller
public class easyLabController {
	
	
	private HttpURLConnection connection;
	
	@Autowired
	UserService  userService;
	
	List<User> userList;

	BufferedReader reader;
	StringBuffer responseContent = new StringBuffer();
	String line;
	
	static String username;
	static String password;;
	static String url_;;
	
	@GetMapping("/")
	public String getIndex(Model model, HttpSession session) {
		model.addAttribute("user", new User());
		userList = userService.findAll();
		 String user_name= (String)session.getAttribute("user_name");
		 if(user_name == null) {
			 System.out.println(user_name);
			 	return "index";
		 }else {
			 System.out.println(user_name);
			 return "home";
		 }		 
	}
	
	
	@GetMapping("/start")
	public String getStartContainer(Model model) {
		System.out.println("Working.........");
		try {
			URL url = new URL("http://127.0.0.1:5000/start");
			connection = (HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("POST");
			
			int status = connection.getResponseCode();
			System.out.println(status);
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = reader.readLine()) != null) {
				responseContent.append(line);
			}
			System.out.println(responseContent.toString());
			username = "easylab";
			password = "easylab@123";
			url_ =  responseContent.toString();
			url_ = url_.substring(64,90);
			model.addAttribute("username",username);
			model.addAttribute("password",password);
			model.addAttribute("url",url_);	
			responseContent.delete(0, responseContent.length());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		
		return "startContainer";
}
	
	@GetMapping("/stop")
	public String getStopContainer() {
		System.out.println("Working.........");
		try {
			URL url = new URL("http://127.0.0.1:5000/stop");
			connection = (HttpURLConnection)url.openConnection();
			
			connection.setRequestMethod("POST");
			
			int status = connection.getResponseCode();
			System.out.println(status);
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			connection.disconnect();
		}
		
		return "redirect:/";
		
}
	
	@GetMapping("/logout")
	public String logout( HttpSession session) {
		session.invalidate();
		easyLabController controller = new easyLabController();
		controller.getStopContainer();
		System.out.println("Hello........................................");
		return "redirect:/";
	}
	
	@PostMapping("/registered")
	public String setRegisterd(@ModelAttribute("user") User user) {
		userService.save(user);
		userList = userService.findAll();
		return "index";
	}
	
	@PostMapping("/login")
	public String setLogin(@ModelAttribute("user") User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user_name", user.getEmail());
		return "redirect:/";
	}
	
}
