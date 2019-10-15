package com.paypal.server;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class AndroidClientUniqueIdGenerator {

	static List<Long> generatedValues = new LinkedList<Long>();

	public static Long generateUniqueId() {
		long val = -1;
		do {
			final UUID uid = UUID.randomUUID();
			final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
			buffer.putLong(uid.getLeastSignificantBits());
			buffer.putLong(uid.getMostSignificantBits());
			final BigInteger bi = new BigInteger(buffer.array());
			val = bi.longValue();

			for (int i = 0; i < generatedValues.size(); i++) {
				Long generator = generatedValues.get(i);
				if (generator.compareTo(val) == 0) {
					generateUniqueId();
				} else {
					generatedValues.add(val);
				}
			}
		} 
		// We also make sure that the ID is in positive space, if its not we
	    // simply repeat the process
		while (val < 0);
		return val;
	}
}