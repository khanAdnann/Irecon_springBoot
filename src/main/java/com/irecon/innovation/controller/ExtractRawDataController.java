package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ExtractRawDataController {

	@GetMapping("/extract")
    public String extractRawData(@RequestParam("category") String category, @RequestParam("option") String option , ModelAndView modelAndView) {
        
       String heading=category + " "+ option;
       System.out.println(heading);
       modelAndView.addObject("heaading",heading);
       return "ExtractRawData";
       
    }
	
	
	
}



