package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@RequestMapping("hello")
	public @ResponseBody String index() {
		return "Hello World";
	}
	@RequestMapping("hello/tpl")
	public String HelloWithTemplate() {
		return "helloTemplate";
	}
	@RequestMapping("hello/dest/{who}")
	public String hello(@PathVariable String who,ModelMap modele) {
		modele.addAttribute("who",who);
		return "helloTemplate";
	}
}
