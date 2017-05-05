package com.leapmotion.leap;

public class KeyTapGesture extends Gesture {
	private long swigCPtr;

	public KeyTapGesture(long paramLong, boolean paramBoolean) {
		super(LeapJNI.KeyTapGesture_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(KeyTapGesture paramKeyTapGesture) {
		return paramKeyTapGesture == null ? 0L : paramKeyTapGesture.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_KeyTapGesture(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public static Gesture.Type classType() {
		return Gesture.Type.swigToEnum(LeapJNI.KeyTapGesture_classType());
	}

	public KeyTapGesture() {
		this(LeapJNI.new_KeyTapGesture__SWIG_0(), true);
	}

	public KeyTapGesture(Gesture paramGesture) {
		this(LeapJNI.new_KeyTapGesture__SWIG_1(Gesture.getCPtr(paramGesture), paramGesture), true);
	}

	public Vector position() {
		return new Vector(LeapJNI.KeyTapGesture_position(this.swigCPtr, this), true);
	}

	public Vector direction() {
		return new Vector(LeapJNI.KeyTapGesture_direction(this.swigCPtr, this), true);
	}

	public float progress() {
		return LeapJNI.KeyTapGesture_progress(this.swigCPtr, this);
	}

	public Pointable pointable() {
		return new Pointable(LeapJNI.KeyTapGesture_pointable(this.swigCPtr, this), true);
	}
}