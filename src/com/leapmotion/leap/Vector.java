package com.leapmotion.leap;

public class Vector {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	public Vector(long paramLong, boolean paramBoolean) {
		this.swigCMemOwn = paramBoolean;
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Vector paramVector) {
		return paramVector == null ? 0L : paramVector.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Vector(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
	}

	public float[] toFloatArray() {
		return new float[] { getX(), getY(), getZ() };
	}

	public Vector() {
		this(LeapJNI.new_Vector__SWIG_0(), true);
	}

	public Vector(float paramFloat1, float paramFloat2, float paramFloat3) {
		this(LeapJNI.new_Vector__SWIG_1(paramFloat1, paramFloat2, paramFloat3), true);
	}

	public Vector(Vector paramVector) {
		this(LeapJNI.new_Vector__SWIG_2(getCPtr(paramVector), paramVector), true);
	}

	public static Vector zero() {
		return new Vector(LeapJNI.Vector_zero(), false);
	}

	public static Vector xAxis() {
		return new Vector(LeapJNI.Vector_xAxis(), false);
	}

	public static Vector yAxis() {
		return new Vector(LeapJNI.Vector_yAxis(), false);
	}

	public static Vector zAxis() {
		return new Vector(LeapJNI.Vector_zAxis(), false);
	}

	public static Vector left() {
		return new Vector(LeapJNI.Vector_left(), false);
	}

	public static Vector right() {
		return new Vector(LeapJNI.Vector_right(), false);
	}

	public static Vector down() {
		return new Vector(LeapJNI.Vector_down(), false);
	}

	public static Vector up() {
		return new Vector(LeapJNI.Vector_up(), false);
	}

	public static Vector forward() {
		return new Vector(LeapJNI.Vector_forward(), false);
	}

	public static Vector backward() {
		return new Vector(LeapJNI.Vector_backward(), false);
	}

	public float magnitude() {
		return LeapJNI.Vector_magnitude(this.swigCPtr, this);
	}

	public float magnitudeSquared() {
		return LeapJNI.Vector_magnitudeSquared(this.swigCPtr, this);
	}

	public float distanceTo(Vector paramVector) {
		return LeapJNI.Vector_distanceTo(this.swigCPtr, this, getCPtr(paramVector), paramVector);
	}

	public float angleTo(Vector paramVector) {
		return LeapJNI.Vector_angleTo(this.swigCPtr, this, getCPtr(paramVector), paramVector);
	}

	public float pitch() {
		return LeapJNI.Vector_pitch(this.swigCPtr, this);
	}

	public float yaw() {
		return LeapJNI.Vector_yaw(this.swigCPtr, this);
	}

	public float roll() {
		return LeapJNI.Vector_roll(this.swigCPtr, this);
	}

	public float dot(Vector paramVector) {
		return LeapJNI.Vector_dot(this.swigCPtr, this, getCPtr(paramVector), paramVector);
	}

	public Vector cross(Vector paramVector) {
		return new Vector(LeapJNI.Vector_cross(this.swigCPtr, this, getCPtr(paramVector), paramVector), true);
	}

	public Vector normalized() {
		return new Vector(LeapJNI.Vector_normalized(this.swigCPtr, this), true);
	}

	public Vector opposite() {
		return new Vector(LeapJNI.Vector_opposite(this.swigCPtr, this), true);
	}

	public Vector plus(Vector paramVector) {
		return new Vector(LeapJNI.Vector_plus(this.swigCPtr, this, getCPtr(paramVector), paramVector), true);
	}

	public Vector minus(Vector paramVector) {
		return new Vector(LeapJNI.Vector_minus(this.swigCPtr, this, getCPtr(paramVector), paramVector), true);
	}

	public Vector times(float paramFloat) {
		return new Vector(LeapJNI.Vector_times(this.swigCPtr, this, paramFloat), true);
	}

	public Vector divide(float paramFloat) {
		return new Vector(LeapJNI.Vector_divide(this.swigCPtr, this, paramFloat), true);
	}

	public String toString() {
		return LeapJNI.Vector_toString(this.swigCPtr, this);
	}

	public boolean equals(Vector paramVector) {
		return LeapJNI.Vector_equals(this.swigCPtr, this, getCPtr(paramVector), paramVector);
	}

	public boolean isValid() {
		return LeapJNI.Vector_isValid(this.swigCPtr, this);
	}

	public float get(long paramLong) {
		return LeapJNI.Vector_get(this.swigCPtr, this, paramLong);
	}

	public void setX(float paramFloat) {
		LeapJNI.Vector_x_set(this.swigCPtr, this, paramFloat);
	}

	public float getX() {
		return LeapJNI.Vector_x_get(this.swigCPtr, this);
	}

	public void setY(float paramFloat) {
		LeapJNI.Vector_y_set(this.swigCPtr, this, paramFloat);
	}

	public float getY() {
		return LeapJNI.Vector_y_get(this.swigCPtr, this);
	}

	public void setZ(float paramFloat) {
		LeapJNI.Vector_z_set(this.swigCPtr, this, paramFloat);
	}

	public float getZ() {
		return LeapJNI.Vector_z_get(this.swigCPtr, this);
	}
}