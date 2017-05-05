package com.leapmotion.leap;

import java.util.Iterator;

public class HandList extends Interface implements Iterable<Hand> {
	private long swigCPtr;

	public HandList(long paramLong, boolean paramBoolean) {
		super(LeapJNI.HandList_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(HandList paramHandList) {
		return paramHandList == null ? 0L : paramHandList.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_HandList(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Iterator<Hand> iterator() {
		return new HandListIterator();
	}

	public HandList() {
		this(LeapJNI.new_HandList(), true);
	}

	public int count() {
		return LeapJNI.HandList_count(this.swigCPtr, this);
	}

	public boolean isEmpty() {
		return LeapJNI.HandList_isEmpty(this.swigCPtr, this);
	}

	public Hand get(int paramInt) {
		return new Hand(LeapJNI.HandList_get(this.swigCPtr, this, paramInt), true);
	}

	public HandList append(HandList paramHandList) {
		return new HandList(LeapJNI.HandList_append(this.swigCPtr, this, getCPtr(paramHandList), paramHandList), false);
	}

	public Hand leftmost() {
		return new Hand(LeapJNI.HandList_leftmost(this.swigCPtr, this), true);
	}

	public Hand rightmost() {
		return new Hand(LeapJNI.HandList_rightmost(this.swigCPtr, this), true);
	}

	public Hand frontmost() {
		return new Hand(LeapJNI.HandList_frontmost(this.swigCPtr, this), true);
	}

	public class HandListIterator implements Iterator<Hand> {
		int index = 0;

		public HandListIterator() {  } 
		public boolean hasNext() { return this.index < HandList.this.count(); }

		public Hand next() {
			return HandList.this.get(this.index++);
		}

		public void remove() {
		}
	}
}