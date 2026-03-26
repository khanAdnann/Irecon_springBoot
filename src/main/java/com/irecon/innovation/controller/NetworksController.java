package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//some common pages in networks and rupay controller 

@Controller
public class NetworksController {
	@GetMapping("/process")
	public String ReconProcessPage(@RequestParam("category") String category, @RequestParam("option") String option,
			ModelAndView modelAndView) {
		String heading = category + " " + option;
		// logger.info("ReconProcessPage called with category: {} and option: {}",
		// category, option);
		System.out.println(heading);
		modelAndView.addObject("heading", heading);
		return "ReconProcess"; // Same HTML page used for dynamic content
	}

	@GetMapping("/report")
	public String GenerateReportPage(@RequestParam("category") String category, @RequestParam("option") String option,
			ModelAndView modelAndView) {
		String heading = category + " " + option;
		System.out.println(heading);
		modelAndView.addObject("heading", heading);
		return "GenerateReports"; // SameHTML page used for dynamic content
	}

	@GetMapping("/TTUM")
	public String GenerateTTUMPage(@RequestParam("category") String category, @RequestParam("option") String option,
			ModelAndView modelAndView) {
		String heading = category + " " + option;
		System.out.println(heading);
		modelAndView.addObject("heading", heading);
		return "GenerateTTUM";
	}

	@GetMapping("/Adjustment_Process")
	public String AdjustmentProcess(@RequestParam("category") String category, @RequestParam("option") String option,
			ModelAndView modelAndView) {
		String heading = category + " " + option;
		System.out.println(heading);
		modelAndView.addObject("heading", heading);
		return "AdjustmentProcess";
	}

}
