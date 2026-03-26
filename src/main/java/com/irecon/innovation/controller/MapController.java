package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

	@GetMapping("/mapSwitchData")
	public String mapSwitchData() {
		
		return "MapSwitchData";
	}

	@GetMapping("/map_cbs_data")
	public String mapCbsData() {
		return "MapCbsData";
	}

}
