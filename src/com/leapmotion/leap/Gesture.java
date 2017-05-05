package com.leapmotion.leap;

public class Gesture extends Interface {
	private long swigCPtr;

	public Gesture(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Gesture_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Gesture paramGesture) {
		return paramGesture == null ? 0L : paramGesture.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Gesture(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Gesture() {
		this(LeapJNI.new_Gesture__SWIG_0(), true);
	}

	public Gesture(Gesture paramGesture) {
		this(LeapJNI.new_Gesture__SWIG_1(getCPtr(paramGesture), paramGesture), true);
	}

	public Type type() {
		return Type.swigToEnum(LeapJNI.Gesture_type(this.swigCPtr, this));
	}

	public State state() {
		return State.swigToEnum(LeapJNI.Gesture_state(this.swigCPtr, this));
	}

	public int id() {
		return LeapJNI.Gesture_id(this.swigCPtr, this);
	}

	public long duration() {
		return LeapJNI.Gesture_duration(this.swigCPtr, this);
	}

	public float durationSeconds() {
		return LeapJNI.Gesture_durationSeconds(this.swigCPtr, this);
	}

	public Frame frame() {
		return new Frame(LeapJNI.Gesture_frame(this.swigCPtr, this), true);
	}

	public HandList hands() {
		return new HandList(LeapJNI.Gesture_hands(this.swigCPtr, this), true);
	}

	public PointableList pointables() {
		return new PointableList(LeapJNI.Gesture_pointables(this.swigCPtr, this), true);
	}

	public boolean isValid() {
		return LeapJNI.Gesture_isValid(this.swigCPtr, this);
	}

	public boolean equals(Gesture paramGesture) {
		return LeapJNI.Gesture_equals(this.swigCPtr, this, getCPtr(paramGesture), paramGesture);
	}

	public String toString() {
		return LeapJNI.Gesture_toString(this.swigCPtr, this);
	}

	public static Gesture invalid() {
		return new Gesture(LeapJNI.Gesture_invalid(), false);
	}

	public static enum State {
		STATE_INVALID(-1), 
		STATE_START(1), 
		STATE_UPDATE(2), 
		STATE_STOP(3);

		private final int swigValue;

		public final int swigValue() { return this.swigValue; }

		public static State swigToEnum(int paramInt) {
			State[] arrayOfState1 = (State[])State.class.getEnumConstants();
			if ((paramInt < arrayOfState1.length) && (paramInt >= 0) && (arrayOfState1[paramInt].swigValue == paramInt))
				return arrayOfState1[paramInt];
			for (State localState : arrayOfState1)
				if (localState.swigValue == paramInt)
					return localState;
			throw new IllegalArgumentException("No enum " + State.class + " with value " + paramInt);
		}

		private State() {
			this.swigValue = SwigNext.next;
		}

		private State(int paramInt) {
			this.swigValue = paramInt;
			SwigNext.next = paramInt + 1;
		}

		private State(State paramState) {
			this.swigValue = paramState.swigValue;
			SwigNext.next = this.swigValue + 1;
		}

		private static class SwigNext {
			private static int next = 0;
		}
	}

	public static enum Type {
		TYPE_INVALID(-1), 
		TYPE_SWIPE(1), 
		TYPE_CIRCLE(4), 
		TYPE_SCREEN_TAP(5), 
		TYPE_KEY_TAP(6);

		private final int swigValue;

		public final int swigValue() { return this.swigValue; }

		public static Type swigToEnum(int paramInt) {
			Type[] arrayOfType1 = (Type[])Type.class.getEnumConstants();
			if ((paramInt < arrayOfType1.length) && (paramInt >= 0) && (arrayOfType1[paramInt].swigValue == paramInt))
				return arrayOfType1[paramInt];
			for (Type localType : arrayOfType1)
				if (localType.swigValue == paramInt)
					return localType;
			throw new IllegalArgumentException("No enum " + Type.class + " with value " + paramInt);
		}

		private Type() {
			this.swigValue = SwigNext.next;
		}

		private Type(int paramInt) {
			this.swigValue = paramInt;
			SwigNext.next = paramInt + 1;
		}

		private Type(Type paramType) {
			this.swigValue = paramType.swigValue;
			SwigNext.next = this.swigValue + 1;
		}

		private static class SwigNext {
			private static int next = 0;
		}
	}
}