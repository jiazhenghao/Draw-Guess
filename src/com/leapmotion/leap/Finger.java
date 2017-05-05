package com.leapmotion.leap;

public class Finger extends Pointable {
	private long swigCPtr;

	public Finger(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Finger_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Finger paramFinger) {
		return paramFinger == null ? 0L : paramFinger.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Finger(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Finger() {
		this(LeapJNI.new_Finger__SWIG_0(), true);
	}

	public Finger(Pointable paramPointable) {
		this(LeapJNI.new_Finger__SWIG_1(Pointable.getCPtr(paramPointable), paramPointable), true);
	}

	public static Finger invalid() {
		return new Finger(LeapJNI.Finger_invalid(), false);
	}

	public String toString() {
		return LeapJNI.Finger_toString(this.swigCPtr, this);
	}
}