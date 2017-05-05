package com.leapmotion.leap;

public class Hand extends Interface {
	private long swigCPtr;

	public Hand(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Hand_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Hand paramHand) {
		return paramHand == null ? 0L : paramHand.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Hand(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Hand() {
		this(LeapJNI.new_Hand(), true);
	}

	public int id() {
		return LeapJNI.Hand_id(this.swigCPtr, this);
	}

	public Frame frame() {
		return new Frame(LeapJNI.Hand_frame(this.swigCPtr, this), true);
	}

	public PointableList pointables() {
		return new PointableList(LeapJNI.Hand_pointables(this.swigCPtr, this), true);
	}

	public Pointable pointable(int paramInt) {
		return new Pointable(LeapJNI.Hand_pointable(this.swigCPtr, this, paramInt), true);
	}

	public FingerList fingers() {
		return new FingerList(LeapJNI.Hand_fingers(this.swigCPtr, this), true);
	}

	public Finger finger(int paramInt) {
		return new Finger(LeapJNI.Hand_finger(this.swigCPtr, this, paramInt), true);
	}

	public ToolList tools() {
		return new ToolList(LeapJNI.Hand_tools(this.swigCPtr, this), true);
	}

	public Tool tool(int paramInt) {
		return new Tool(LeapJNI.Hand_tool(this.swigCPtr, this, paramInt), true);
	}

	public Vector palmPosition() {
		return new Vector(LeapJNI.Hand_palmPosition(this.swigCPtr, this), true);
	}

	public Vector stabilizedPalmPosition() {
		return new Vector(LeapJNI.Hand_stabilizedPalmPosition(this.swigCPtr, this), true);
	}

	public Vector palmVelocity() {
		return new Vector(LeapJNI.Hand_palmVelocity(this.swigCPtr, this), true);
	}

	public Vector palmNormal() {
		return new Vector(LeapJNI.Hand_palmNormal(this.swigCPtr, this), true);
	}

	public Vector direction() {
		return new Vector(LeapJNI.Hand_direction(this.swigCPtr, this), true);
	}

	public Vector sphereCenter() {
		return new Vector(LeapJNI.Hand_sphereCenter(this.swigCPtr, this), true);
	}

	public float sphereRadius() {
		return LeapJNI.Hand_sphereRadius(this.swigCPtr, this);
	}

	public Vector translation(Frame paramFrame) {
		return new Vector(LeapJNI.Hand_translation(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame), true);
	}

	public float translationProbability(Frame paramFrame) {
		return LeapJNI.Hand_translationProbability(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame);
	}

	public Vector rotationAxis(Frame paramFrame) {
		return new Vector(LeapJNI.Hand_rotationAxis(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame), true);
	}

	public float rotationAngle(Frame paramFrame) {
		return LeapJNI.Hand_rotationAngle__SWIG_0(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame);
	}

	public float rotationAngle(Frame paramFrame, Vector paramVector) {
		return LeapJNI.Hand_rotationAngle__SWIG_1(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame, Vector.getCPtr(paramVector), paramVector);
	}

	public Matrix rotationMatrix(Frame paramFrame) {
		return new Matrix(LeapJNI.Hand_rotationMatrix(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame), true);
	}

	public float rotationProbability(Frame paramFrame) {
		return LeapJNI.Hand_rotationProbability(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame);
	}

	public float scaleFactor(Frame paramFrame) {
		return LeapJNI.Hand_scaleFactor(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame);
	}

	public float scaleProbability(Frame paramFrame) {
		return LeapJNI.Hand_scaleProbability(this.swigCPtr, this, Frame.getCPtr(paramFrame), paramFrame);
	}

	public float timeVisible() {
		return LeapJNI.Hand_timeVisible(this.swigCPtr, this);
	}

	public boolean isValid() {
		return LeapJNI.Hand_isValid(this.swigCPtr, this);
	}

	public static Hand invalid() {
		return new Hand(LeapJNI.Hand_invalid(), false);
	}

	public boolean equals(Hand paramHand) {
		return LeapJNI.Hand_equals(this.swigCPtr, this, getCPtr(paramHand), paramHand);
	}

	public String toString() {
		return LeapJNI.Hand_toString(this.swigCPtr, this);
	}
}