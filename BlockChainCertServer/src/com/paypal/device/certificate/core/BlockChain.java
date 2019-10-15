package com.paypal.device.certificate.core;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.Block;

/*
 * Global Device Warehouse Server that is a distributed system globally. The Server hosts
 * the device certificates of all the corresponding devices of the end user. Blockchain Datastructure is
 * used for certificate signing and the certificates is viewable from the Admin Server
 * dashboard.AES 256 - SHA 256 or 512 is used for signing the certificate of the device across the
 * end client and a copy is stored in the server side for performing additional verification
 * cycle.
 * 
 * BackEnd Storage - Google Cloud Spanner - Multiregional distributed host.
 * Docs => https://cloud.google.com/spanner
 * 
 */

public class BlockChain {
	
	/***********************************************
	 * Creating append only array Block Class List *
	 ***********************************************/
	private int maxSize = 100;
	private BlockEntity[] blockchainarr;
	
	BlockChain(){
		String.format("Initializing BlockChain Array with Append Only Array of Size : %d", maxSize);
		blockchainarr = new BlockEntity[maxSize];
	}
	

}
