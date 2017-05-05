package com.leapmotion.leap;

public class Tool extends Pointable {
	private long swigCPtr;

	public Tool(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Tool_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Tool paramTool) {
		return paramTool == null ? 0L : paramTool.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Tool(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Tool() {
		this(LeapJNI.new_Tool__SWIG_0(), true);
	}

	public Tool(Pointable paramPointable) {
		this(LeapJNI.new_Tool__SWIG_1(Pointable.getCPtr(paramPointable), paramPointable), true);
	}

	public static Tool invalid() {
		return new Tool(LeapJNI.Tool_invalid(), false);
	}

	public String toString() {
		return LeapJNI.Tool_toString(this.swigCPtr, this);
	}
}