package com.leapmotion.leap;

import java.util.Iterator;

public class DeviceList extends Interface implements Iterable<Device> {
	private long swigCPtr;

	public DeviceList(long paramLong, boolean paramBoolean) {
		super(LeapJNI.DeviceList_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(DeviceList paramDeviceList) {
		return paramDeviceList == null ? 0L : paramDeviceList.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_DeviceList(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Iterator<Device> iterator() {
		return new DeviceListIterator();
	}

	public DeviceList() {
		this(LeapJNI.new_DeviceList(), true);
	}

	public int count() {
		return LeapJNI.DeviceList_count(this.swigCPtr, this);
	}

	public boolean isEmpty() {
		return LeapJNI.DeviceList_isEmpty(this.swigCPtr, this);
	}

	public Device get(int paramInt) {
		return new Device(LeapJNI.DeviceList_get(this.swigCPtr, this, paramInt), true);
	}

	public DeviceList append(DeviceList paramDeviceList) {
		return new DeviceList(LeapJNI.DeviceList_append(this.swigCPtr, this, getCPtr(paramDeviceList), paramDeviceList), false);
	}

	public class DeviceListIterator implements Iterator<Device> {
		int index = 0;

		public DeviceListIterator() {  } 
		public boolean hasNext() { return this.index < DeviceList.this.count(); }

		public Device next() {
			return DeviceList.this.get(this.index++);
		}

		public void remove() {
		}
	}
}