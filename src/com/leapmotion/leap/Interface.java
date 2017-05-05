package com.leapmotion.leap;

public class Interface {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	public Interface(long paramLong, boolean paramBoolean) {
		this.swigCMemOwn = paramBoolean;
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Interface paramInterface) {
		return paramInterface == null ? 0L : paramInterface.swigCPtr;
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				throw new UnsupportedOperationException("C++ destructor does not have public access");
			}
			this.swigCPtr = 0L;
		}
	}
}