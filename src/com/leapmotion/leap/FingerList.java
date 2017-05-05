package com.leapmotion.leap;

import java.util.Iterator;

public class FingerList extends Interface implements Iterable<Finger> {
	private long swigCPtr;

	public FingerList(long paramLong, boolean paramBoolean) {
		super(LeapJNI.FingerList_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(FingerList paramFingerList) {
		return paramFingerList == null ? 0L : paramFingerList.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_FingerList(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Iterator<Finger> iterator() {
		return new FingerListIterator();
	}

	public FingerList() {
		this(LeapJNI.new_FingerList(), true);
	}

	public int count() {
		return LeapJNI.FingerList_count(this.swigCPtr, this);
	}

	public boolean isEmpty() {
		return LeapJNI.FingerList_isEmpty(this.swigCPtr, this);
	}

	public Finger get(int paramInt) {
		return new Finger(LeapJNI.FingerList_get(this.swigCPtr, this, paramInt), true);
	}

	public FingerList append(FingerList paramFingerList) {
		return new FingerList(LeapJNI.FingerList_append(this.swigCPtr, this, getCPtr(paramFingerList), paramFingerList), false);
	}

	public Finger leftmost() {
		return new Finger(LeapJNI.FingerList_leftmost(this.swigCPtr, this), true);
	}

	public Finger rightmost() {
		return new Finger(LeapJNI.FingerList_rightmost(this.swigCPtr, this), true);
	}

	public Finger frontmost() {
		return new Finger(LeapJNI.FingerList_frontmost(this.swigCPtr, this), true);
	}

	public class FingerListIterator implements Iterator<Finger> {
		int index = 0;

		public FingerListIterator() {  } 
		public boolean hasNext() { return this.index < FingerList.this.count(); }

		public Finger next() {
			return FingerList.this.get(this.index++);
		}

		public void remove() {
		}
	}
}