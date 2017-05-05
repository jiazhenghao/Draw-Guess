package com.leapmotion.leap;

public class Pointable extends Interface {
	private long swigCPtr;

	public Pointable(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Pointable_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Pointable paramPointable) {
		return paramPointable == null ? 0L : paramPointable.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Pointable(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Pointable() {
		this(LeapJNI.new_Pointable(), true);
	}

	public int id() {
		return LeapJNI.Pointable_id(this.swigCPtr, this);
	}

	public Frame frame() {
		return new Frame(LeapJNI.Pointable_frame(this.swigCPtr, this), true);
	}

	public Hand hand() {
		return new Hand(LeapJNI.Pointable_hand(this.swigCPtr, this), true);
	}

	public Vector tipPosition() {
		return new Vector(LeapJNI.Pointable_tipPosition(this.swigCPtr, this), true);
	}

	public Vector tipVelocity() {
		return new Vector(LeapJNI.Pointable_tipVelocity(this.swigCPtr, this), true);
	}

	public Vector direction() {
		return new Vector(LeapJNI.Pointable_direction(this.swigCPtr, this), true);
	}

	public float width() {
		return LeapJNI.Pointable_width(this.swigCPtr, this);
	}

	public float length() {
		return LeapJNI.Pointable_length(this.swigCPtr, this);
	}

	public boolean isFinger() {
		return LeapJNI.Pointable_isFinger(this.swigCPtr, this);
	}

	public boolean isTool() {
		return LeapJNI.Pointable_isTool(this.swigCPtr, this);
	}

	public boolean isValid() {
		return LeapJNI.Pointable_isValid(this.swigCPtr, this);
	}

	public Zone touchZone() {
		return Zone.swigToEnum(LeapJNI.Pointable_touchZone(this.swigCPtr, this));
	}

	public float touchDistance() {
		return LeapJNI.Pointable_touchDistance(this.swigCPtr, this);
	}

	public Vector stabilizedTipPosition() {
		return new Vector(LeapJNI.Pointable_stabilizedTipPosition(this.swigCPtr, this), true);
	}

	public float timeVisible() {
		return LeapJNI.Pointable_timeVisible(this.swigCPtr, this);
	}

	public static Pointable invalid() {
		return new Pointable(LeapJNI.Pointable_invalid(), false);
	}

	public boolean equals(Pointable paramPointable) {
		return LeapJNI.Pointable_equals(this.swigCPtr, this, getCPtr(paramPointable), paramPointable);
	}

	public String toString() {
		return LeapJNI.Pointable_toString(this.swigCPtr, this);
	}

	public static enum Zone {
		ZONE_NONE(0), 
		ZONE_HOVERING(1), 
		ZONE_TOUCHING(2);

		private final int swigValue;

		public final int swigValue() { return this.swigValue; }

		public static Zone swigToEnum(int paramInt) {
			Zone[] arrayOfZone1 = (Zone[])Zone.class.getEnumConstants();
			if ((paramInt < arrayOfZone1.length) && (paramInt >= 0) && (arrayOfZone1[paramInt].swigValue == paramInt))
				return arrayOfZone1[paramInt];
			for (Zone localZone : arrayOfZone1)
				if (localZone.swigValue == paramInt)
					return localZone;
			throw new IllegalArgumentException("No enum " + Zone.class + " with value " + paramInt);
		}

		private Zone() {
			this.swigValue = SwigNext.next;
		}

		private Zone(int paramInt) {
			this.swigValue = paramInt;
			SwigNext.next = paramInt + 1;
		}

		private Zone(Zone paramZone) {
			this.swigValue = paramZone.swigValue;
			SwigNext.next = this.swigValue + 1;
		}

		private static class SwigNext {
			private static int next = 0;
		}
	}
}