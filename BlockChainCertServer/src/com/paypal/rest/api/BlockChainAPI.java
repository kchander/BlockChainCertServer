package com.paypal.rest.api;

import java.security.PublicKey;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.tomcat.util.codec.EncoderException;

import com.paypal.device.certificate.clone.BlockChainAppenderService;
import com.paypal.server.AndroidClientUniqueIdGenerator;
import com.paypal.server.KeyPairGeneration;

@Path("/v1/blockserver")
public class BlockChainAPI implements DeviceCertificateRestAPIImplementation{
	public static final String delimeter = "$$$";
	
	@POST
	@Path("/createdevicecert")
	@Produces(MediaType.TEXT_PLAIN)
	
	public Response createCertificateStore(@QueryParam ("username") String username,
			@QueryParam("deviceName") String deviceName,@QueryParam("uniqueID") String uniqueID,
			@QueryParam("deviceId") String deviceId,@QueryParam("kernelinformation") String kernelinformation,
			@QueryParam("phoneversion") String phoneversion,@QueryParam("useragent") String useragent,
			@QueryParam("systemFileDirectory") String systemFileDirectory,@QueryParam("displaymetrics") String displaymetrics,
			@QueryParam("subscriberId") String subscriberId) {
		
	 	
		final KeyPairGeneration keypairCreator = new KeyPairGeneration();
		
		try {
			final List<Object> keypairGenerated = keypairCreator.generateKeys("RSA", 2048);
			
			StringBuffer uniqueDeviceIdentifiers = new StringBuffer();
			uniqueDeviceIdentifiers.append("deviceName=").append(deviceName+delimeter).append("uniqueID=").append(uniqueID+delimeter)
					.append("deviceId=").append(deviceId+delimeter).append("kernelinformation=").append(kernelinformation+delimeter)
					.append("phoneversion=").append(phoneversion+delimeter).append("useragent=").append(useragent+delimeter)
					.append("systemFileDirectory=").append(systemFileDirectory+delimeter).append("displaymetrics=")
					.append(displaymetrics+delimeter).append("subscriberId=").append(subscriberId+delimeter);
			
			/*
			 * Generating Server Unique Id for every Android Client
			 */
			Long clientUniqueId = AndroidClientUniqueIdGenerator.generateUniqueId();
			
			uniqueDeviceIdentifiers.append("clientUniqueId=").append(clientUniqueId).append(delimeter);
			
			/*
			 * Generated PublicKey Key is used for encryption and sent
			 * over to the client to store in their local file system.
			 * File will be chunked into different file paths to prevent
			 * file misuse and is RSA 1024 encoded.
			 * During the subsequent client access the file will be 
			 * read by the server every time and will decrypt the contents
			 * using the private key for integrity and device Identity . 
			 */
			
		    byte[] encryptedBytesToBeSentToClient = null;
		    Cipher encryptCipher = null;
		    
		    for(int i=0;i<keypairGenerated.size();i++){
		    	if(keypairGenerated.get(i) instanceof PublicKey){
		    		
		    		try {
						
		    		encryptCipher = Cipher.getInstance("RSA");
					encryptCipher.init(Cipher.ENCRYPT_MODE, (PublicKey) keypairGenerated.get(i));
					
		    		} catch (Exception e) {
						System.out.println(e.getMessage());
					}
		    	}
		    }
		    
			if (encryptCipher != null){
				try {
					encryptedBytesToBeSentToClient = encryptCipher
							.doFinal(uniqueDeviceIdentifiers.toString().getBytes());
				} catch (IllegalBlockSizeException | BadPaddingException e) {
					System.out.println(e.getMessage());
				}
			}
			
			//Write to Protected BlockChain
			BlockChainAppenderService blockObject = new BlockChainAppenderService();
			String encryptedDevicedata = Base64.getEncoder().encodeToString(encryptedBytesToBeSentToClient);
			boolean isBlockChainAppended = blockObject.writeDeviceToBlockChain(encryptedDevicedata);
			
			if(isBlockChainAppended){
				System.out.println("Awesome !! the Encrypted Device Data is appended to the blockchain ");
			}
			
			if(encryptedBytesToBeSentToClient != null && encryptedBytesToBeSentToClient.length > 0){
				return Response.ok(encryptedBytesToBeSentToClient, MediaType.APPLICATION_OCTET_STREAM_TYPE).build();
			}else{
				return Response.serverError().build();
			}
			
		} catch (EncoderException e) {
			
			e.printStackTrace();
			e.getMessage();
		}
		return Response.status(404).build();
	}

	@GET
	@Path(value = "/deviceAuth")
	@Produces(MediaType.TEXT_PLAIN)
	
	public Response validateAndroidMobileAppDeviceCertificate(@QueryParam ("username") String username,
			@QueryParam("deviceName") String deviceName) {
		
		//Do DB call for the Username
		//Retreive the public key based on the username from the google cloud spanner
		//Check the file contents unique id with that of the device data stored 
		//in our blockchain server.
		//Apply naive bayes model to find the trust score. 
		return Response.ok("Status=" + APIConstants.SUCCESS).build();
	}

}
