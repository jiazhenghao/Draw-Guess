package com.leapmotion.leap;

public class Frame extends Interface {
	private long swigCPtr;

	public Frame(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Frame_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Frame paramFrame) {
		return paramFrame == null ? 0L : paramFrame.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Frame(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Frame() {
		this(LeapJNI.new_Frame(), true);
	}

	public long id() {
		return LeapJNI.Frame_id(this.swigCPtr, this);
	}

	public long timestamp() {
		return LeapJNI.Frame_timestamp(this.swigCPtr, this);
	}

	public HandList hands() {
		return new HandList(LeapJNI.Frame_hands(this.swigCPtr, this), true);
	}

	public Hand hand(int paramInt) {
		return new Hand(LeapJNI.Frame_hand(this.swigCPtr, this, paramInt), true);
	}

	public PointableList pointables() {
		return new PointableList(LeapJNI.Frame_pointables(this.swigCPtr, this), true);
	}

	public Pointable pointable(int paramInt) {
		return new Pointable(LeapJNI.Frame_pointable(this.swigCPtr, this, paramInt), true);
	}

	public FingerList fingers() {
		return new FingerList(LeapJNI.Frame_fingers(this.swigCPtr, this), true);
	}

	public Finger finger(int paramInt) {
		return new Finger(LeapJNI.Frame_finger(this.swigCPtr, this, paramInt), true);
	}

	public ToolList tools() {
		return new ToolList(LeapJNI.Frame_tools(this.swigCPtr, this), true);
	}

	public Tool tool(int paramInt) {
		return new Tool(LeapJNI.Frame_tool(this.swigCPtr, this, paramInt), true);
	}

	public Gesture gesture(int paramInt) {
		return new Gesture(LeapJNI.Frame_gesture(this.swigCPtr, this, paramInt), true);
	}

	public GestureList gestures() {
		return new GestureList(LeapJNI.Frame_gestures__SWIG_0(this.swigCPtr, this), true);
	}

	public GestureList gestures(Frame paramFrame) {
		return new GestureList(LeapJNI.Frame_gestures__SWIG_1(this.swigCPtr, this, getCPtr(paramFrame), paramFrame), true);
	}

	public Vector translation(Frame paramFrame) {
		return new Vector(LeapJNI.Frame_translation(this.swigCPtr, this, getCPtr(paramFrame), paramFrame), true);
	}

	public float translationProbability(Frame paramFrame) {
		return LeapJNI.Frame_translationProbability(this.swigCPtr, this, getCPtr(paramFrame), paramFrame);
	}

	public Vector rotationAxis(Frame paramFrame) {
		return new Vector(LeapJNI.Frame_rotationAxis(this.swigCPtr, this, getCPtr(paramFrame), paramFrame), true);
	}

	public float rotationAngle(Frame paramFrame) {
		return LeapJNI.Frame_rotationAngle__SWIG_0(this.swigCPtr, this, getCPtr(paramFrame), paramFrame);
	}

	public float rotationAngle(Frame paramFrame, Vector paramVector) {
		return LeapJNI.Frame_rotationAngle__SWIG_1(this.swigCPtr, this, getCPtr(paramFrame), paramFrame, Vector.getCPtr(paramVector), paramVector);
	}

	public Matrix rotationMatrix(Frame paramFrame) {
		return new Matrix(LeapJNI.Frame_rotationMatrix(this.swigCPtr, this, getCPtr(paramFrame), paramFrame), true);
	}

	public float rotationProbability(Frame paramFrame) {
		return LeapJNI.Frame_rotationProbability(this.swigCPtr, this, getCPtr(paramFrame), paramFrame);
	}

	public float scaleFactor(Frame paramFrame) {
		return LeapJNI.Frame_scaleFactor(this.swigCPtr, this, getCPtr(paramFrame), paramFrame);
	}

	public float scaleProbability(Frame paramFrame) {
		return LeapJNI.Frame_scaleProbability(this.swigCPtr, this, getCPtr(paramFrame), paramFrame);
	}

	public InteractionBox interactionBox() {
		return new InteractionBox(LeapJNI.Frame_interactionBox(this.swigCPtr, this), true);
	}

	public float currentFramesPerSecond() {
		return LeapJNI.Frame_currentFramesPerSecond(this.swigCPtr, this);
	}

	public boolean isValid() {
		return LeapJNI.Frame_isValid(this.swigCPtr, this);
	}

	public static Frame invalid() {
		return new Frame(LeapJNI.Frame_invalid(), false);
	}

	public boolean equals(Frame paramFrame) {
		return LeapJNI.Frame_equals(this.swigCPtr, this, getCPtr(paramFrame), paramFrame);
	}

	public String toString() {
		return LeapJNI.Frame_toString(this.swigCPtr, this);
	}
}