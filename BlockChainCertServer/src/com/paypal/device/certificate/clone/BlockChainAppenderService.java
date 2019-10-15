package com.paypal.device.certificate.clone;

import java.util.Collections;


import java.util.List;

import com.paypal.device.certificate.core.BlockChainCustomLinkedList;
import com.paypal.device.certificate.core.BlockChainValidator;
import com.paypal.device.certificate.core.BlockEntity;
import com.paypal.device.certificate.core.Utility;

/*
 * Global Device Warehouse Server that is a distributed system globally. The Server hosts
 * the device certificates of all the corresponding devices of the end user. Blockchain Datastructure is
 * used for certificate signing and the certificates is viewable from the Admin Server
 * dashboard.AES 256 - SHA 256 or 512 is used for signing the certificate of the device across the
 * end client and a copy is stored in the server side for performing additional verification
 * cycle.
 * 
 * BackEnd Storage - Google Cloud Spanner - Multi-Regional distributed host.
 * Docs => https://cloud.google.com/spanner
 * 
 */

public class BlockChainAppenderService {
	
	/***********************************************************************
	 * Block Chain Custom LinkedList -> Permits only append only operation.*
	 * Read Only Operation is permitted to generate the device OAuth Token *
	 ***********************************************************************/
	final protected static List<BlockEntity> blockchain = Collections.synchronizedList(new BlockChainCustomLinkedList<BlockEntity>()); 
	
	
	/*
	 * Encrypted Device Data, Unique Id and other features sets are added to the spanner database
	 * as well as to the current BlockChainCustomLinkedList. The value is being printed to the 
	 * view part of the JSP in the form of subsequent blocks.
	 */
	
	public boolean writeDeviceToBlockChain(String privateKeyEncryptedDevicedata){
		
		if(!Utility.isStringEmpty(privateKeyEncryptedDevicedata)){
			
			if(blockchain.size() == 0){
				
				final BlockEntity entity = new BlockEntity(privateKeyEncryptedDevicedata, "0");
				blockchain.add(entity);
				
			}else{
				
				final BlockEntity chainBlock = new BlockEntity(privateKeyEncryptedDevicedata, blockchain.get(size() - 1).calculateHash());
				blockchain.add(chainBlock);
				
			}
			
			 blockchain.parallelStream().forEach((t) -> {
			      System.out.println("Printing the Device Certificates in Blockhain ===> {{ " + t.toString());
			      System.out.println(" ==> END <== }} ");
		      });
			 
			 System.out.println(" Total Size of the block chain is :: " + blockchain.size());
		      
		     System.out.println(" Is chain valid :: " + BlockChainValidator.isChainValid(blockchain));
		      
		     System.out.println(" Trigger Event :: Append Only Block Created "  + BlockEntity.objectsOfCurrClassCreated);
		     
		     return true;
		}
		
	    return false; 
	}
	
	public int size(){
		return blockchain.size();
	}
	

}
