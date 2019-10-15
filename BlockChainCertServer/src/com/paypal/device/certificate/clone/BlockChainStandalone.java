package com.paypal.device.certificate.clone;

import java.util.Collections;
import java.util.List;

import com.paypal.device.certificate.core.BlockChainCustomLinkedList;
import com.paypal.device.certificate.core.BlockChainValidator;
import com.paypal.device.certificate.core.BlockEntity;

public class BlockChainStandalone {
	
	/***********************************************************************
	 * Block Chain Custom LinkedList -> Permits only append only operation.*
	 * Read Only Operation is permitted to generate the device OAuth Token *
	 ***********************************************************************/
	final static List<BlockEntity> blockchain = Collections.synchronizedList(new BlockChainCustomLinkedList<BlockEntity>()); 
	
	public static void main(String args[]){
	     
	      blockchain.add(new BlockEntity("Nexus5:123e4567-e89b-12d3-a456-426655440000:uuid:c21c905c-8359-4bd6-b864-844709e05754","0" ));
	      blockchain.add(new BlockEntity("Huawei U8230/ Pulse. :AndoirdPie:User:MichealRayappan:Kernel:LinuxRedhat:Internal5GB:Xiami:4", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("Samsung GalaxyS5:uuid:4123e4567-e89b-12d3-a456-426655440000", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("HTC Tattoo/HTC Click:123e4567-e89b-12d3-a456-w343924243293482","0" ));
	      blockchain.add(new BlockEntity("AndoirdPie:User:AhamedShakeel:Kernel:LinuxRedhat:Internal5GB:Xiami:4", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("Motorola CLIQ::UUID:kjsndbdas-e89b-12d3-a456-426655440000", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("MotorolaDroid::UUID:123e4567-e89b-12d3-a456-213123123123:123e4567-e89b-12d3-a456-426655440000","0" ));
	      blockchain.add(new BlockEntity("GooglePixel3::AndoirdPie:User:JeganAlagrswamy:Kernel:LinuxRedhat:Internal50GB:Xiomi65", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("HTC10::UUID:123e4567-e89b-12d3-a456-42665412313::Kernel:Ubuntu:Version:4", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("SamsungGalaxyS7Edge::UUID:5123e4567-e89b-12d3-a456-426655440000:GalaxyS7","0" ));
	      blockchain.add(new BlockEntity("SonyXperiaXZ2::AndoirdOS:GingerBread:UUID:asdfsdf=sadfasdfs-asdfasdfasd=fasdfasdfa:User:SanjeevSharma:Kernel:LinuxRedhat:InternalMemory:15GB", blockchain.get(size()-1).calculateHash()));
	      blockchain.add(new BlockEntity("SamsungGlaaxyTabS2:AndroidOS:Orion:UUID:IAHSDASD-YERWERUER-WRQWQKIFY-NCBVCXZZBM-NMSDFRTHFSER:User:KrithivasanChandran:InternalMemory:64GB", blockchain.get(size()-1).calculateHash()));
	      
	      
	      blockchain.parallelStream().forEach((t) -> {
		      System.out.println("[ " + t.toString() + " ] ");
	      });
	      
	      System.out.println(" Total Size of the block chain is :: " + blockchain.size());
	      
	      System.out.println(" Is chain valid :: " + BlockChainValidator.isChainValid(blockchain));
	      
	      //Listener or Observable --> Fires on creating on new Block.
	      System.out.println(" Trigger Event :: Append Only Block Created "  + BlockEntity.objectsOfCurrClassCreated);
	}
	
	public static int size(){
		return blockchain.size();
	}
	
}