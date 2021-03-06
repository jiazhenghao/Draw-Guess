package com.leapmotion.leap;

public class ScreenTapGesture extends Gesture {
	private long swigCPtr;

	public ScreenTapGesture(long paramLong, boolean paramBoolean) {
		super(LeapJNI.ScreenTapGesture_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(ScreenTapGesture paramScreenTapGesture) {
		return paramScreenTapGesture == null ? 0L : paramScreenTapGesture.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_ScreenTapGesture(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public static Gesture.Type classType() {
		return Gesture.Type.swigToEnum(LeapJNI.ScreenTapGesture_classType());
	}

	public ScreenTapGesture() {
		this(LeapJNI.new_ScreenTapGesture__SWIG_0(), true);
	}

	public ScreenTapGesture(Gesture paramGesture) {
		this(LeapJNI.new_ScreenTapGesture__SWIG_1(Gesture.getCPtr(paramGesture), paramGesture), true);
	}

	public Vector position() {
		return new Vector(LeapJNI.ScreenTapGesture_position(this.swigCPtr, this), true);
	}

	public Vector direction() {
		return new Vector(LeapJNI.ScreenTapGesture_direction(this.swigCPtr, this), true);
	}

	public float progress() {
		return LeapJNI.ScreenTapGesture_progress(this.swigCPtr, this);
	}

	public Pointable pointable() {
		return new Pointable(LeapJNI.ScreenTapGesture_pointable(this.swigCPtr, this), true);
	}
}