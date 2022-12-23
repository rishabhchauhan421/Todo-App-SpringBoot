package com.springboot.mywebapp.hello;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! How are you!";
	}
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		return "Hello! How are you!";
	}
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
	
	
}
