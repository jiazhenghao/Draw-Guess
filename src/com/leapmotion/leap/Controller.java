package com.leapmotion.leap;

public class Controller extends Interface {
	private long swigCPtr;

	public Controller(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Controller_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Controller paramController) {
		return paramController == null ? 0L : paramController.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Controller(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Controller() {
		this(LeapJNI.new_Controller__SWIG_0(), true);
	}

	public Controller(Listener paramListener) {
		this(LeapJNI.new_Controller__SWIG_1(Listener.getCPtr(paramListener), paramListener), true);
	}

	public boolean isConnected() {
		return LeapJNI.Controller_isConnected(this.swigCPtr, this);
	}

	public boolean hasFocus() {
		return LeapJNI.Controller_hasFocus(this.swigCPtr, this);
	}

	public PolicyFlag policyFlags() {
		return PolicyFlag.swigToEnum(LeapJNI.Controller_policyFlags(this.swigCPtr, this));
	}

	public void setPolicyFlags(PolicyFlag paramPolicyFlag) {
		LeapJNI.Controller_setPolicyFlags(this.swigCPtr, this, paramPolicyFlag.swigValue());
	}

	public boolean addListener(Listener paramListener) {
		return LeapJNI.Controller_addListener(this.swigCPtr, this, Listener.getCPtr(paramListener), paramListener);
	}

	public boolean removeListener(Listener paramListener) {
		return LeapJNI.Controller_removeListener(this.swigCPtr, this, Listener.getCPtr(paramListener), paramListener);
	}

	public Frame frame(int paramInt) {
		return new Frame(LeapJNI.Controller_frame__SWIG_0(this.swigCPtr, this, paramInt), true);
	}

	public Frame frame() {
		return new Frame(LeapJNI.Controller_frame__SWIG_1(this.swigCPtr, this), true);
	}

	public Config config() {
		return new Config(LeapJNI.Controller_config(this.swigCPtr, this), true);
	}

	public DeviceList devices() {
		return new DeviceList(LeapJNI.Controller_devices(this.swigCPtr, this), true);
	}

	public ScreenList locatedScreens() {
		return new ScreenList(LeapJNI.Controller_locatedScreens(this.swigCPtr, this), true);
	}

	public void enableGesture(Gesture.Type paramType, boolean paramBoolean) {
		LeapJNI.Controller_enableGesture__SWIG_0(this.swigCPtr, this, paramType.swigValue(), paramBoolean);
	}

	public void enableGesture(Gesture.Type paramType) {
		LeapJNI.Controller_enableGesture__SWIG_1(this.swigCPtr, this, paramType.swigValue());
	}

	public boolean isGestureEnabled(Gesture.Type paramType) {
		return LeapJNI.Controller_isGestureEnabled(this.swigCPtr, this, paramType.swigValue());
	}

	public static enum PolicyFlag {
		POLICY_DEFAULT(0), 
		POLICY_BACKGROUND_FRAMES(1);

		private final int swigValue;

		public final int swigValue() { return this.swigValue; }

		public static PolicyFlag swigToEnum(int paramInt) {
			PolicyFlag[] arrayOfPolicyFlag1 = (PolicyFlag[])PolicyFlag.class.getEnumConstants();
			if ((paramInt < arrayOfPolicyFlag1.length) && (paramInt >= 0) && (arrayOfPolicyFlag1[paramInt].swigValue == paramInt))
				return arrayOfPolicyFlag1[paramInt];
			for (PolicyFlag localPolicyFlag : arrayOfPolicyFlag1)
				if (localPolicyFlag.swigValue == paramInt)
					return localPolicyFlag;
			throw new IllegalArgumentException("No enum " + PolicyFlag.class + " with value " + paramInt);
		}

		private PolicyFlag() {
			this.swigValue = SwigNext.next;
		}

		private PolicyFlag(int paramInt) {
			this.swigValue = paramInt;
			SwigNext.next = paramInt + 1;
		}

		private PolicyFlag(PolicyFlag paramPolicyFlag) {
			this.swigValue = paramPolicyFlag.swigValue;
			SwigNext.next = this.swigValue + 1;
		}

		private static class SwigNext {
			private static int next = 0;
		}
	}
}