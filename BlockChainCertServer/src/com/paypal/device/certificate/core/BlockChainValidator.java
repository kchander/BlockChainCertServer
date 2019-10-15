package com.paypal.device.certificate.core;

import java.util.List;

public class BlockChainValidator {
	
	public static Boolean isChainValid(List<BlockEntity> blockchain) {
		
	    BlockEntity currentBlock;
	    BlockEntity previousBlock;
	    
	    for(int i=1; i < blockchain.size(); i++) {
	    	
	        currentBlock = blockchain.get(i);
	        previousBlock = blockchain.get(i-1);
	        
	        if(!currentBlock.calculateHash().equals(currentBlock.calculateHash()) ){
	            System.out.println("Current Hashes not equal");            
	            return false;
	        }
	        
	        if(previousBlock.calculateHash().equals(currentBlock.calculateHash()) ) {
	            System.out.println("Previous Hashes are equal");
	            return false;
	        }
	    }
	    return true;
	}

}
