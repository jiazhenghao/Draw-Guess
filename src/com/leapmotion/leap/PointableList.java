package com.leapmotion.leap;

import java.util.Iterator;

public class PointableList extends Interface implements Iterable<Pointable> {
	private long swigCPtr;

	public PointableList(long paramLong, boolean paramBoolean) {
		super(LeapJNI.PointableList_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(PointableList paramPointableList) {
		return paramPointableList == null ? 0L : paramPointableList.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_PointableList(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Iterator<Pointable> iterator() {
		return new PointableListIterator();
	}

	public PointableList() {
		this(LeapJNI.new_PointableList(), true);
	}

	public int count() {
		return LeapJNI.PointableList_count(this.swigCPtr, this);
	}

	public boolean isEmpty() {
		return LeapJNI.PointableList_isEmpty(this.swigCPtr, this);
	}

	public Pointable get(int paramInt) {
		return new Pointable(LeapJNI.PointableList_get(this.swigCPtr, this, paramInt), true);
	}

	public PointableList append(PointableList paramPointableList) {
		return new PointableList(LeapJNI.PointableList_append__SWIG_0(this.swigCPtr, this, getCPtr(paramPointableList), paramPointableList), false);
	}

	public PointableList append(FingerList paramFingerList) {
		return new PointableList(LeapJNI.PointableList_append__SWIG_1(this.swigCPtr, this, FingerList.getCPtr(paramFingerList), paramFingerList), false);
	}

	public PointableList append(ToolList paramToolList) {
		return new PointableList(LeapJNI.PointableList_append__SWIG_2(this.swigCPtr, this, ToolList.getCPtr(paramToolList), paramToolList), false);
	}

	public Pointable leftmost() {
		return new Pointable(LeapJNI.PointableList_leftmost(this.swigCPtr, this), true);
	}

	public Pointable rightmost() {
		return new Pointable(LeapJNI.PointableList_rightmost(this.swigCPtr, this), true);
	}

	public Pointable frontmost() {
		return new Pointable(LeapJNI.PointableList_frontmost(this.swigCPtr, this), true);
	}

	public class PointableListIterator implements Iterator<Pointable> {
		int index = 0;

		public PointableListIterator() {  } 
		public boolean hasNext() { return this.index < PointableList.this.count(); }

		public Pointable next() {
			return PointableList.this.get(this.index++);
		}

		public void remove() {
		}
	}
}