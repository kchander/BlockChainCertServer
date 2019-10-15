package com.paypal.device.certificate.core;

import java.util.LinkedList;

import javax.management.RuntimeErrorException;

/*
 * Customized BlockChain List by enforcing only append only operation (add) on 
 * the LinkedList and overriding the other methods of the linkedlist to preserve
 * the Immutability of the Blockchain solution.
 */

public class BlockChainCustomLinkedList<E> extends LinkedList<E> {

	private static volatile int blockChainSecurityCounter = 0;

	@Override 
	public boolean add(E et) {
		
		if (et instanceof BlockEntity) {
			BlockEntity block = (BlockEntity) et;
			if (Utility.isStringEmpty(block)) {
				return false;
			}
			blockChainSecurityCounter += 1;
			return super.add((E) block);
		}
		return false;
	}

	@Override
	public E remove() {
		System.out.println("Enforcing BlockChain Rule :: Append Only Datas");
		System.out.println("Immutable Custom LinkedList");
		return null;
	}

	@Override
	public E removeFirst() {
		System.out.println("Enforcing BlockChain Rule :: Append Only List :: Cannot remove First element in the Queue");
		System.out.println("Immutable Custom LinkedList");
		return null;
	}

	@Override
	public E removeLast() {
		System.out.println("Enforcing BlockChain Rule :: Append Only List :: Cannot remove Last Elements in the Queue");
		System.out.println("Immutable Custom LinkedList");
		return null;
	}

	@Override
	public E set(int index, E element) {
		try {
			throw new Exception("Updates Strictly not Permitted in blockchain:: Append only block data allowed");
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public void addFirst(E m) {
		System.out.println("Append Only BlockChain List :: Strictly enforced");
	}

	@Override
	public void addLast(E m) {
		System.out.println("Append only BlockChain List :: Strictly enforced to avoid adding at last");
	}

	@Override
	public void clear() {
		System.out.println("No Clear Operation permitted :: Append only data");
	}

	@Override
	public Object clone() {
		try {
			throw new CloneNotSupportedException();
		} catch (CloneNotSupportedException e) {
			System.out.println(" Clone Not Supported Exception :: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean offer(E e) {
		return false;
	}

	@Override
	public boolean offerFirst(E e) {
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		return false;
	}

	@Override
	public E poll() {
		try {
			throw new Exception("Poll removes the first element of the linkedlist which is not permitted");
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public E pollFirst() {
		try {
			throw new Exception("Poll removes the first element of the linkedlist which is not permitted");
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public E pollLast() {
		try {
			throw new Exception("Poll removes the first element of the linkedlist which is not permitted");
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public void push(E n) {
		System.out.println("Push not permitted in blockchain list ::");
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		System.out.println("Remove Operation on Blockchain not premitted !! Append only logs.");
		return false;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		System.out.println("Remove Last Occurence on Blockchain not permitted !! Append Only BlockChain are allowed");
		return false;
	}

	@Override
	public Object[] toArray() {
		try {
			throw new RuntimeException(
					"Clone of blockchain data not permitted :: Security Rule is enforced Copy of the Blockchain is strictly prohibited");
		} catch (RuntimeErrorException ex) {
			ex.getMessage();
		}
		return null;
	}

}
