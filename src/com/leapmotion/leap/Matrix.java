package com.leapmotion.leap;

public class Matrix {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	public Matrix(long paramLong, boolean paramBoolean) {
		this.swigCMemOwn = paramBoolean;
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Matrix paramMatrix) {
		return paramMatrix == null ? 0L : paramMatrix.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Matrix(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
	}

	public float[] toArray3x3(float[] paramArrayOfFloat) {
		paramArrayOfFloat[0] = getXBasis().getX(); paramArrayOfFloat[1] = getXBasis().getY(); paramArrayOfFloat[2] = getXBasis().getZ();
		paramArrayOfFloat[3] = getYBasis().getX(); paramArrayOfFloat[4] = getYBasis().getY(); paramArrayOfFloat[5] = getYBasis().getZ();
		paramArrayOfFloat[6] = getZBasis().getX(); paramArrayOfFloat[7] = getZBasis().getY(); paramArrayOfFloat[8] = getZBasis().getZ();
		return paramArrayOfFloat;
	}

	public double[] toArray3x3(double[] paramArrayOfDouble) {
		paramArrayOfDouble[0] = getXBasis().getX(); paramArrayOfDouble[1] = getXBasis().getY(); paramArrayOfDouble[2] = getXBasis().getZ();
		paramArrayOfDouble[3] = getYBasis().getX(); paramArrayOfDouble[4] = getYBasis().getY(); paramArrayOfDouble[5] = getYBasis().getZ();
		paramArrayOfDouble[6] = getZBasis().getX(); paramArrayOfDouble[7] = getZBasis().getY(); paramArrayOfDouble[8] = getZBasis().getZ();
		return paramArrayOfDouble;
	}

	public float[] toArray3x3() {
		return toArray3x3(new float[9]);
	}

	public float[] toArray4x4(float[] paramArrayOfFloat) {
		paramArrayOfFloat[0] = getXBasis().getX(); paramArrayOfFloat[1] = getXBasis().getY(); paramArrayOfFloat[2] = getXBasis().getZ(); paramArrayOfFloat[3] = 0.0F;
		paramArrayOfFloat[4] = getYBasis().getX(); paramArrayOfFloat[5] = getYBasis().getY(); paramArrayOfFloat[6] = getYBasis().getZ(); paramArrayOfFloat[7] = 0.0F;
		paramArrayOfFloat[8] = getZBasis().getX(); paramArrayOfFloat[9] = getZBasis().getY(); paramArrayOfFloat[10] = getZBasis().getZ(); paramArrayOfFloat[11] = 0.0F;
		paramArrayOfFloat[12] = getOrigin().getX(); paramArrayOfFloat[13] = getOrigin().getY(); paramArrayOfFloat[14] = getOrigin().getZ(); paramArrayOfFloat[15] = 1.0F;
		return paramArrayOfFloat;
	}

	public double[] toArray4x4(double[] paramArrayOfDouble) {
		paramArrayOfDouble[0] = getXBasis().getX(); paramArrayOfDouble[1] = getXBasis().getY(); paramArrayOfDouble[2] = getXBasis().getZ(); paramArrayOfDouble[3] = 0.0D;
		paramArrayOfDouble[4] = getYBasis().getX(); paramArrayOfDouble[5] = getYBasis().getY(); paramArrayOfDouble[6] = getYBasis().getZ(); paramArrayOfDouble[7] = 0.0D;
		paramArrayOfDouble[8] = getZBasis().getX(); paramArrayOfDouble[9] = getZBasis().getY(); paramArrayOfDouble[10] = getZBasis().getZ(); paramArrayOfDouble[11] = 0.0D;
		paramArrayOfDouble[12] = getOrigin().getX(); paramArrayOfDouble[13] = getOrigin().getY(); paramArrayOfDouble[14] = getOrigin().getZ(); paramArrayOfDouble[15] = 1.0D;
		return paramArrayOfDouble;
	}

	public float[] toArray4x4() {
		return toArray4x4(new float[16]);
	}

	public Matrix() {
		this(LeapJNI.new_Matrix__SWIG_0(), true);
	}

	public Matrix(Matrix paramMatrix) {
		this(LeapJNI.new_Matrix__SWIG_1(getCPtr(paramMatrix), paramMatrix), true);
	}

	public Matrix(Vector paramVector1, Vector paramVector2, Vector paramVector3) {
		this(LeapJNI.new_Matrix__SWIG_2(Vector.getCPtr(paramVector1), paramVector1, Vector.getCPtr(paramVector2), paramVector2, Vector.getCPtr(paramVector3), paramVector3), true);
	}

	public Matrix(Vector paramVector1, Vector paramVector2, Vector paramVector3, Vector paramVector4) {
		this(LeapJNI.new_Matrix__SWIG_3(Vector.getCPtr(paramVector1), paramVector1, Vector.getCPtr(paramVector2), paramVector2, Vector.getCPtr(paramVector3), paramVector3, Vector.getCPtr(paramVector4), paramVector4), true);
	}

	public Matrix(Vector paramVector, float paramFloat) {
		this(LeapJNI.new_Matrix__SWIG_4(Vector.getCPtr(paramVector), paramVector, paramFloat), true);
	}

	public Matrix(Vector paramVector1, float paramFloat, Vector paramVector2) {
		this(LeapJNI.new_Matrix__SWIG_5(Vector.getCPtr(paramVector1), paramVector1, paramFloat, Vector.getCPtr(paramVector2), paramVector2), true);
	}

	public static Matrix identity() {
		return new Matrix(LeapJNI.Matrix_identity(), false);
	}

	public void setRotation(Vector paramVector, float paramFloat) {
		LeapJNI.Matrix_setRotation(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector, paramFloat);
	}

	public Vector transformPoint(Vector paramVector) {
		return new Vector(LeapJNI.Matrix_transformPoint(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector), true);
	}

	public Vector transformDirection(Vector paramVector) {
		return new Vector(LeapJNI.Matrix_transformDirection(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector), true);
	}

	public Matrix rigidInverse() {
		return new Matrix(LeapJNI.Matrix_rigidInverse(this.swigCPtr, this), true);
	}

	public Matrix times(Matrix paramMatrix) {
		return new Matrix(LeapJNI.Matrix_times(this.swigCPtr, this, getCPtr(paramMatrix), paramMatrix), true);
	}

	public boolean equals(Matrix paramMatrix) {
		return LeapJNI.Matrix_equals(this.swigCPtr, this, getCPtr(paramMatrix), paramMatrix);
	}

	public String toString() {
		return LeapJNI.Matrix_toString(this.swigCPtr, this);
	}

	public void setXBasis(Vector paramVector) {
		LeapJNI.Matrix_xBasis_set(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector);
	}

	public Vector getXBasis() {
		long l = LeapJNI.Matrix_xBasis_get(this.swigCPtr, this);
		return l == 0L ? null : new Vector(l, false);
	}

	public void setYBasis(Vector paramVector) {
		LeapJNI.Matrix_yBasis_set(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector);
	}

	public Vector getYBasis() {
		long l = LeapJNI.Matrix_yBasis_get(this.swigCPtr, this);
		return l == 0L ? null : new Vector(l, false);
	}

	public void setZBasis(Vector paramVector) {
		LeapJNI.Matrix_zBasis_set(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector);
	}

	public Vector getZBasis() {
		long l = LeapJNI.Matrix_zBasis_get(this.swigCPtr, this);
		return l == 0L ? null : new Vector(l, false);
	}

	public void setOrigin(Vector paramVector) {
		LeapJNI.Matrix_origin_set(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector);
	}

	public Vector getOrigin() {
		long l = LeapJNI.Matrix_origin_get(this.swigCPtr, this);
		return l == 0L ? null : new Vector(l, false);
	}
}