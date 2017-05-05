package com.leapmotion.leap;

public class Screen extends Interface {
	private long swigCPtr;

	public Screen(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Screen_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Screen paramScreen) {
		return paramScreen == null ? 0L : paramScreen.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Screen(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Screen() {
		this(LeapJNI.new_Screen(), true);
	}

	public int id() {
		return LeapJNI.Screen_id(this.swigCPtr, this);
	}

	public Vector intersect(Pointable paramPointable, boolean paramBoolean, float paramFloat) {
		return new Vector(LeapJNI.Screen_intersect__SWIG_0(this.swigCPtr, this, Pointable.getCPtr(paramPointable), paramPointable, paramBoolean, paramFloat), true);
	}

	public Vector intersect(Pointable paramPointable, boolean paramBoolean) {
		return new Vector(LeapJNI.Screen_intersect__SWIG_1(this.swigCPtr, this, Pointable.getCPtr(paramPointable), paramPointable, paramBoolean), true);
	}

	public Vector intersect(Vector paramVector1, Vector paramVector2, boolean paramBoolean, float paramFloat) {
		return new Vector(LeapJNI.Screen_intersect__SWIG_2(this.swigCPtr, this, Vector.getCPtr(paramVector1), paramVector1, Vector.getCPtr(paramVector2), paramVector2, paramBoolean, paramFloat), true);
	}

	public Vector intersect(Vector paramVector1, Vector paramVector2, boolean paramBoolean) {
		return new Vector(LeapJNI.Screen_intersect__SWIG_3(this.swigCPtr, this, Vector.getCPtr(paramVector1), paramVector1, Vector.getCPtr(paramVector2), paramVector2, paramBoolean), true);
	}

	public Vector project(Vector paramVector, boolean paramBoolean, float paramFloat) {
		return new Vector(LeapJNI.Screen_project__SWIG_0(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector, paramBoolean, paramFloat), true);
	}

	public Vector project(Vector paramVector, boolean paramBoolean) {
		return new Vector(LeapJNI.Screen_project__SWIG_1(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector, paramBoolean), true);
	}

	public Vector horizontalAxis() {
		return new Vector(LeapJNI.Screen_horizontalAxis(this.swigCPtr, this), true);
	}

	public Vector verticalAxis() {
		return new Vector(LeapJNI.Screen_verticalAxis(this.swigCPtr, this), true);
	}

	public Vector bottomLeftCorner() {
		return new Vector(LeapJNI.Screen_bottomLeftCorner(this.swigCPtr, this), true);
	}

	public Vector normal() {
		return new Vector(LeapJNI.Screen_normal(this.swigCPtr, this), true);
	}

	public int widthPixels() {
		return LeapJNI.Screen_widthPixels(this.swigCPtr, this);
	}

	public int heightPixels() {
		return LeapJNI.Screen_heightPixels(this.swigCPtr, this);
	}

	public float distanceToPoint(Vector paramVector) {
		return LeapJNI.Screen_distanceToPoint(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector);
	}

	public boolean isValid() {
		return LeapJNI.Screen_isValid(this.swigCPtr, this);
	}

	public static Screen invalid() {
		return new Screen(LeapJNI.Screen_invalid(), false);
	}

	public boolean equals(Screen paramScreen) {
		return LeapJNI.Screen_equals(this.swigCPtr, this, getCPtr(paramScreen), paramScreen);
	}

	public String toString() {
		return LeapJNI.Screen_toString(this.swigCPtr, this);
	}
}