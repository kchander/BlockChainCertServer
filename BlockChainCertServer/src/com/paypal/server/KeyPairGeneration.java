package com.paypal.server;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import org.apache.tomcat.util.codec.EncoderException;

public class KeyPairGeneration{
	
    static List<Object> keys = new LinkedList<Object>();
	
	public static List<Object> generateKeys(String keyAlgorithm, int numBits) throws EncoderException {

        try {
        	
             /*
              * Get the public/private key pair- RSA 2048 is secure.
              * RSA 4096 takes more time for the computation.
              * Will lead to Read Timeout or connection timeout error
              * as our instance is located remotely.
              * 
              */
        	
             KeyPairGenerator keyGen = KeyPairGenerator.getInstance(keyAlgorithm);
             keyGen.initialize(numBits);
             KeyPair keyPair = keyGen.genKeyPair();
             PrivateKey privateKey = keyPair.getPrivate();
             PublicKey publicKey = keyPair.getPublic();

             System.out.println("Generating key/value pair using " + privateKey.getAlgorithm() + " algorithm");

             // Get the bytes of the public and private keys
             byte[] privateKeyBytes = privateKey.getEncoded();
             byte[] publicKeyBytes = publicKey.getEncoded();

             // Get the formats of the encoded bytes
             String formatPrivate = privateKey.getFormat(); // PKCS#8
             String formatPublic = publicKey.getFormat(); // X.509   

             System.out.println("Private Key : " + new String(Base64.getEncoder().encode(privateKeyBytes)));
             System.out.println("Public Key : " +  new String(Base64.getEncoder().encode(publicKeyBytes)));

             // The bytes can be converted back to public and private key objects
             KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
             EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
             
             PrivateKey privateKey2 = keyFactory.generatePrivate(privateKeySpec);

             EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
             PublicKey publicKey2 = keyFactory.generatePublic(publicKeySpec);

             // The original and new keys are the same
             System.out.println("  Validate if the private keys are equal after and before casting " + iskeyCompare(privateKey,privateKey2));
             System.out.println("  Validate if both the public keys are equal before and after object casting " + isPublicKeyCompare(publicKey,publicKey2));
             
             keys.add(privateKey);
             keys.add(publicKey);
             
             return keys;
            		 
        } catch (InvalidKeySpecException specException) {
        	
        	specException.printStackTrace();
        
            System.out.println(KeyPairGeneration.class.getName() + "Invalid Key Spec Exception");
             
        } catch (NoSuchAlgorithmException e) {
        	
             System.out.println("RSA : 2048 Algorithm is not supported ::> No such algorithm: " + keyAlgorithm);
        }
		return keys;

   }
	
	public static boolean iskeyCompare(PrivateKey pk , PrivateKey pk2){
		return pk.equals(pk2);
	}
	
	public static boolean isPublicKeyCompare(PublicKey pb, PublicKey pb2){
		return pb.equals(pb2);
	}
}
