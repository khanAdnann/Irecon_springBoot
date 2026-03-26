package com.irecon.innovation.services;



import java.util.ArrayList;


public interface MastercardPosRawDataService {
	
	public void _read1251(ArrayList<String> packData,String file_name,String filedate) throws Exception;
	
	public void _read125100(ArrayList<String> packData,String file_name) throws Exception;

}
