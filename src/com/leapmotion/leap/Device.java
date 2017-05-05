package com.leapmotion.leap;

public class Device extends Interface {
  private long swigCPtr;

  public Device(long paramLong, boolean paramBoolean) {
    super(LeapJNI.Device_SWIGUpcast(paramLong), paramBoolean);
    this.swigCPtr = paramLong;
  }

  public static long getCPtr(Device paramDevice) {
    return paramDevice == null ? 0L : paramDevice.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (this.swigCPtr != 0L) {
      if (this.swigCMemOwn) {
        this.swigCMemOwn = false;
        LeapJNI.delete_Device(this.swigCPtr);
      }
      this.swigCPtr = 0L;
    }
    super.delete();
  }

  public Device() {
    this(LeapJNI.new_Device(), true);
  }

  public float horizontalViewAngle() {
    return LeapJNI.Device_horizontalViewAngle(this.swigCPtr, this);
  }

  public float verticalViewAngle() {
    return LeapJNI.Device_verticalViewAngle(this.swigCPtr, this);
  }

  public float range() {
    return LeapJNI.Device_range(this.swigCPtr, this);
  }

  public float distanceToBoundary(Vector paramVector) {
    return LeapJNI.Device_distanceToBoundary(this.swigCPtr, this, Vector.getCPtr(paramVector), paramVector);
  }

  public boolean isValid() {
    return LeapJNI.Device_isValid(this.swigCPtr, this);
  }

  public static Device invalid() {
    return new Device(LeapJNI.Device_invalid(), false);
  }

  public boolean equals(Device paramDevice) {
    return LeapJNI.Device_equals(this.swigCPtr, this, getCPtr(paramDevice), paramDevice);
  }

  public String toString() {
    return LeapJNI.Device_toString(this.swigCPtr, this);
  }
}