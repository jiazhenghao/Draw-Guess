package com.leapmotion.leap;

public class Listener {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	public Listener(long paramLong, boolean paramBoolean) {
		this.swigCMemOwn = paramBoolean;
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Listener paramListener) {
		return paramListener == null ? 0L : paramListener.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Listener(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
	}

	protected void swigDirectorDisconnect() {
		this.swigCMemOwn = false;
		delete();
	}

	public void swigReleaseOwnership() {
		this.swigCMemOwn = false;
		LeapJNI.Listener_change_ownership(this, this.swigCPtr, false);
	}

	public void swigTakeOwnership() {
		this.swigCMemOwn = true;
		LeapJNI.Listener_change_ownership(this, this.swigCPtr, true);
	}

	public Listener() {
		this(LeapJNI.new_Listener(), true);
		LeapJNI.Listener_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
	}

	public void onInit(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onInit(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onInitSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); 
	}

	public void onConnect(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onConnect(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onConnectSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); 
	}

	public void onDisconnect(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onDisconnect(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onDisconnectSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); 
	}

	public void onExit(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onExit(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onExitSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); 
	}

	public void onFrame(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onFrame(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onFrameSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); 
	}

	public void onFocusGained(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onFocusGained(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onFocusGainedSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); 
	}

	public void onFocusLost(Controller paramController) {
		if (getClass() == Listener.class) LeapJNI.Listener_onFocusLost(this.swigCPtr, this, Controller.getCPtr(paramController), paramController); else LeapJNI.Listener_onFocusLostSwigExplicitListener(this.swigCPtr, this, Controller.getCPtr(paramController), paramController);
	}
}