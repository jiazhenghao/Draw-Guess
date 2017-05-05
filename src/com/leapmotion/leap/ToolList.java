package com.leapmotion.leap;

import java.util.Iterator;

public class ToolList extends Interface implements Iterable<Tool> {
	private long swigCPtr;

	public ToolList(long paramLong, boolean paramBoolean) {
		super(LeapJNI.ToolList_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(ToolList paramToolList) {
		return paramToolList == null ? 0L : paramToolList.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_ToolList(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Iterator<Tool> iterator() {
		return new ToolListIterator();
	}

	public ToolList() {
		this(LeapJNI.new_ToolList(), true);
	}

	public int count() {
		return LeapJNI.ToolList_count(this.swigCPtr, this);
	}

	public boolean isEmpty() {
		return LeapJNI.ToolList_isEmpty(this.swigCPtr, this);
	}

	public Tool get(int paramInt) {
		return new Tool(LeapJNI.ToolList_get(this.swigCPtr, this, paramInt), true);
	}

	public ToolList append(ToolList paramToolList) {
		return new ToolList(LeapJNI.ToolList_append(this.swigCPtr, this, getCPtr(paramToolList), paramToolList), false);
	}

	public Tool leftmost() {
		return new Tool(LeapJNI.ToolList_leftmost(this.swigCPtr, this), true);
	}

	public Tool rightmost() {
		return new Tool(LeapJNI.ToolList_rightmost(this.swigCPtr, this), true);
	}

	public Tool frontmost() {
		return new Tool(LeapJNI.ToolList_frontmost(this.swigCPtr, this), true);
	}

	public class ToolListIterator implements Iterator<Tool> {
		int index = 0;

		public ToolListIterator() {  } 
		public boolean hasNext() { return this.index < ToolList.this.count(); }

		public Tool next() {
			return ToolList.this.get(this.index++);
		}

		public void remove() {
		}
	}
}