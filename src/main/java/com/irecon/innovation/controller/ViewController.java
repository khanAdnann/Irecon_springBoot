package com.irecon.innovation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	 @GetMapping("/view_switch_before_mapping")
	    public String viewSwitchBeforeMapping() {
	    	return "ViewSwitchBeforeMapping";
	    }
	    
	    @GetMapping("/view_file_upload")
	    public String ViewFileUpload() {
	    	return "ViewFileUpload";
	    }
	    
	    @GetMapping("/view_cbs_before_mapping")
	    public String ViewCbsBeforeMapping() {
	    	return "ViewCBSBeforeMapping";
	    }
	    
	    @GetMapping("/view_raw_data_count")
	    	public String ViewRawDataCount() {
	    		return "ViewRawDataCount";
	    	}
	    
	    @GetMapping("/delete_file")
	    public String DeleteFile() {
	    	return "DeleteFile";
	    }
	    
	    @GetMapping("/increase_file")
	    public String IncreaseFile() {
	    	return "IncreaseFile";
	    }
}
