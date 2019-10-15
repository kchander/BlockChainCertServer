package com.paypal.device.certificate.core;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BlockEntity {

	private String backPointerHash;
	private String data;
	private String currentHash;
	private Timestamp timestamp;
	public static Timestamp ts;
	private AtomicLong fallbackData = new AtomicLong();
	private String failbackDataFiller = "HJDAFQWEHANDJASKEQWHEQWEKAJADSH<<" 
										+ "JAKLSDNOIAH;QWEQWJEQWELKWQEQWEQWQE>>"
											+ "AHDAIODASLKDASDHASIODJKASDLKASJDAJSKDASJDA";

	/*
	 * Security enforcer to find out how many instances or objects of the class
	 * are created to avoid mis reading or mis appending. Trigger -> On Create
	 * ---> Event Fires <----
	 */
	public volatile static AtomicInteger objectsOfCurrClassCreated = new AtomicInteger(0);

	static {
		Date date = new Date();
		long time = date.getTime();
		ts = new Timestamp(time);
	}

	public BlockEntity(String data, String backhash) {
		this.data = data;
		backPointerHash = backhash;
		this.timestamp = ts;
		this.currentHash = calculateHash();
		objectsOfCurrClassCreated.incrementAndGet();
	}

	public synchronized String getCurrHash() {
		return currentHash;
	}

	public String calculateHash() {
		String calculatedhash = GenerateSHA256.encryptDataWithSha256(dataValidator());
		return calculatedhash;
	}

	/*
	 * Fall-back strategy in case the Android App Signal data received from the
	 * server is null or empty. The data is a dummy one and gets appended with
	 * the already existing one that has been incremented.
	 */

	private String dataValidator() {

		final StringBuffer datahash = new StringBuffer();
		datahash.append(Utility.isStringEmpty(backPointerHash) ? String.valueOf(fallbackData.getAndIncrement())
				: backPointerHash);
		datahash.append(ts);
		datahash.append(Utility.isStringEmpty(data) ? fallbackData : data);

		return datahash.toString();
	}

	protected void finalize() throws Throwable {
		objectsOfCurrClassCreated.decrementAndGet();
		super.finalize();
	}

	public String toString() {

		return new StringBuilder().append("[ ").append("backPointerHash => ").append(backPointerHash + ",")
				.append("data => ").append(data + ",").append("currentHash =>").append(currentHash + ",")
				.append("timestamp => ").append(ts).append(" ]").toString();
	}
}
