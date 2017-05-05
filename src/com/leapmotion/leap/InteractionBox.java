package com.leapmotion.leap;

public class InteractionBox extends Interface {
	private long swigCPtr;

	public InteractionBox(long paramLong, boolean paramBoolean) {
		super(LeapJNI.InteractionBox_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(InteractionBox paramInteractionBox) {
		return paramInteractionBox == null ? 0L : paramInteractionBox.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_InteractionBox(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public InteractionBox() {
		this(LeapJNI.new_InteractionBox(), true);
	}

	public Vector normalizePoint(Vector paramVector, boolean paramBoolean) {
		return new Vector(LeapJNI.InteractionBox_normalizePoint__SWIG_0(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector, paramBoolean), true);
	}

	public Vector normalizePoint(Vector paramVector) {
		return new Vector(LeapJNI.InteractionBox_normalizePoint__SWIG_1(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector), true);
	}

	public Vector denormalizePoint(Vector paramVector) {
		return new Vector(LeapJNI.InteractionBox_denormalizePoint(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector), true);
	}

	public Vector center() {
		return new Vector(LeapJNI.InteractionBox_center(this.swigCPtr, this), true);
	}

	public float width() {
		return LeapJNI.InteractionBox_width(this.swigCPtr, this);
	}

	public float height() {
		return LeapJNI.InteractionBox_height(this.swigCPtr, this);
	}

	public float depth() {
		return LeapJNI.InteractionBox_depth(this.swigCPtr, this);
	}

	public boolean isValid() {
		return LeapJNI.InteractionBox_isValid(this.swigCPtr, this);
	}

	public static InteractionBox invalid() {
		return new InteractionBox(LeapJNI.InteractionBox_invalid(), false);
	}

	public boolean equals(InteractionBox paramInteractionBox) {
		return LeapJNI.InteractionBox_equals(this.swigCPtr, this, getCPtr(paramInteractionBox), paramInteractionBox);
	}

	public String toString() {
		return LeapJNI.InteractionBox_toString(this.swigCPtr, this);
	}
}