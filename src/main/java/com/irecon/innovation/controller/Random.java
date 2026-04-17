package com.irecon.innovation.controller;

import java.util.HashMap;
import java.util.Map;

public class Random {
	
	public static void main(String args[]) {
		System.out.println("hello");
		
		
		Map<String,Integer> map =new HashMap<String, Integer>();
		
		
		
		map.put("Adnan", 3);
		map.put("Zara", 1);
		map.put("Hurain", 1);
		map.put("sir", 10);
		
		
		System.out.println("Map"+map); 
		
		for(Map.Entry<String, Integer> entry : map.entrySet()){
			
			int a = entry.getValue();
			
			for(int b=0;b<a;b++) {

				System.out.println(entry.getKey());
				
			}
			
			
			//System.out.println(entry.getKey());
		}
	}

}
