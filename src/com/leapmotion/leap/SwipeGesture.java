package com.leapmotion.leap;

public class SwipeGesture extends Gesture {
	private long swigCPtr;

	public SwipeGesture(long paramLong, boolean paramBoolean) {
		super(LeapJNI.SwipeGesture_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(SwipeGesture paramSwipeGesture) {
		return paramSwipeGesture == null ? 0L : paramSwipeGesture.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_SwipeGesture(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public static Gesture.Type classType() {
		return Gesture.Type.swigToEnum(LeapJNI.SwipeGesture_classType());
	}

	public SwipeGesture() {
		this(LeapJNI.new_SwipeGesture__SWIG_0(), true);
	}

	public SwipeGesture(Gesture paramGesture) {
		this(LeapJNI.new_SwipeGesture__SWIG_1(Gesture.getCPtr(paramGesture), paramGesture), true);
	}

	public Vector startPosition() {
		return new Vector(LeapJNI.SwipeGesture_startPosition(this.swigCPtr, this), true);
	}

	public Vector position() {
		return new Vector(LeapJNI.SwipeGesture_position(this.swigCPtr, this), true);
	}

	public Vector direction() {
		return new Vector(LeapJNI.SwipeGesture_direction(this.swigCPtr, this), true);
	}

	public float speed() {
		return LeapJNI.SwipeGesture_speed(this.swigCPtr, this);
	}

	public Pointable pointable() {
		return new Pointable(LeapJNI.SwipeGesture_pointable(this.swigCPtr, this), true);
	}
}