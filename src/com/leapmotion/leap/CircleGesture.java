package com.leapmotion.leap;

public class CircleGesture extends Gesture {
	private long swigCPtr;

	public CircleGesture(long paramLong, boolean paramBoolean) {
		super(LeapJNI.CircleGesture_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(CircleGesture paramCircleGesture) {
		return paramCircleGesture == null ? 0L : paramCircleGesture.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_CircleGesture(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public static Gesture.Type classType() {
		return Gesture.Type.swigToEnum(LeapJNI.CircleGesture_classType());
	}

	public CircleGesture() {
		this(LeapJNI.new_CircleGesture__SWIG_0(), true);
	}

	public CircleGesture(Gesture paramGesture) {
		this(LeapJNI.new_CircleGesture__SWIG_1(Gesture.getCPtr(paramGesture), paramGesture), true);
	}

	public Vector center() {
		return new Vector(LeapJNI.CircleGesture_center(this.swigCPtr, this), true);
	}

	public Vector normal() {
		return new Vector(LeapJNI.CircleGesture_normal(this.swigCPtr, this), true);
	}

	public float progress() {
		return LeapJNI.CircleGesture_progress(this.swigCPtr, this);
	}

	public float radius() {
		return LeapJNI.CircleGesture_radius(this.swigCPtr, this);
	}

	public Pointable pointable() {
		return new Pointable(LeapJNI.CircleGesture_pointable(this.swigCPtr, this), true);
	}
}