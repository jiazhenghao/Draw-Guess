package com.leapmotion.leap;

import java.util.Iterator;

public class ScreenList extends Interface implements Iterable<Screen> {
	private long swigCPtr;

	public ScreenList(long paramLong, boolean paramBoolean) {
		super(LeapJNI.ScreenList_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(ScreenList paramScreenList) {
		return paramScreenList == null ? 0L : paramScreenList.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_ScreenList(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Iterator<Screen> iterator() {
		return new ScreenListIterator();
	}

	public ScreenList() {
		this(LeapJNI.new_ScreenList(), true);
	}

	public int count() {
		return LeapJNI.ScreenList_count(this.swigCPtr, this);
	}

	public boolean isEmpty() {
		return LeapJNI.ScreenList_isEmpty(this.swigCPtr, this);
	}

	public Screen get(int paramInt) {
		return new Screen(LeapJNI.ScreenList_get(this.swigCPtr, this, paramInt), true);
	}

	public Screen closestScreenHit(Pointable paramPointable) {
		return new Screen(LeapJNI.ScreenList_closestScreenHit__SWIG_0(this.swigCPtr, this, Pointable.getCPtr(paramPointable), paramPointable), true);
	}

	public Screen closestScreenHit(Vector paramVector1, Vector paramVector2) {
		return new Screen(LeapJNI.ScreenList_closestScreenHit__SWIG_1(this.swigCPtr, this, Vector.getCPtr(paramVector1), paramVector1, Vector.getCPtr(paramVector2), paramVector2), true);
	}

	public Screen closestScreen(Vector paramVector) {
		return new Screen(LeapJNI.ScreenList_closestScreen(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector), true);
	}

	public class ScreenListIterator implements Iterator<Screen> {
		int index = 0;

		public ScreenListIterator() {  } 
		public boolean hasNext() { return this.index < ScreenList.this.count(); }

		public Screen next() {
			return ScreenList.this.get(this.index++);
		}

		public void remove() {
		}
	}
}