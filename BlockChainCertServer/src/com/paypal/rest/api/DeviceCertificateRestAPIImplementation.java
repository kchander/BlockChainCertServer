package com.paypal.rest.api;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface DeviceCertificateRestAPIImplementation {
	
	/*
	 * Accepts the Android Feature Data's AndroidFeatureData
	 * JSON String.
	 */
	public Response createCertificateStore(String username,String deviceName,
			String uniqueID,String deviceId,String kernelinformation,String phoneversion,
			String useragent,String systemFileDirectory,String displaymetrics,String subscriberId);
	
	/*
	 * Validates the certificate passed from the client along with 
	 * the session id for device OAuthentication.
	 */
	public Response validateAndroidMobileAppDeviceCertificate(String username, String DevicecertificateContents);
	
}
