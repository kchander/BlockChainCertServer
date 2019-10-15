package com.paypal.device.certificate.core;

public class Utility {
	
	public static boolean isStringEmpty(Object t){
		
		if( t instanceof String){
			String temp = (String)t;
			return (temp == null || temp.isEmpty()
					 || temp.length() == 0) ? true : false;
		}
		return false;
	}
	
}
