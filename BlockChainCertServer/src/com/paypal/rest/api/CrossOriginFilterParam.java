package com.paypal.rest.api;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class CrossOriginFilterParam implements ContainerResponseFilter{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.
	 * container.ContainerRequestContext,
	 * javax.ws.rs.container.ContainerResponseContext) Allowing only POST REST
	 * access for appending AndroidFeature Trust Signals into the blockchain and
	 * encrypting with SHA 256 . For Now Restricting the Block-chain API to just
	 * 
	 * http://trustServerIP address.
	 * 
	 * More Info here: https://www.baeldung.com/cors-in-jax-rs
	 */

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://ipaddress:8080");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Accept-Language", "en-us");
		responseContext.getHeaders().add("Connection", "Keep-Alive");
		responseContext.getHeaders().add("Keep-Alive", 20000);
		responseContext.getHeaders().add("Access-Control-Allow-Methods", "POST");
	}

}
