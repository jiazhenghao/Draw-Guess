package com.leapmotion.leap;

public class Config extends Interface {
	private long swigCPtr;

	public Config(long paramLong, boolean paramBoolean) {
		super(LeapJNI.Config_SWIGUpcast(paramLong), paramBoolean);
		this.swigCPtr = paramLong;
	}

	public static long getCPtr(Config paramConfig) {
		return paramConfig == null ? 0L : paramConfig.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (this.swigCPtr != 0L) {
			if (this.swigCMemOwn) {
				this.swigCMemOwn = false;
				LeapJNI.delete_Config(this.swigCPtr);
			}
			this.swigCPtr = 0L;
		}
		super.delete();
	}

	public Config() {
		this(LeapJNI.new_Config(), true);
	}

	public ValueType type(String paramString) {
		return ValueType.swigToEnum(LeapJNI.Config_type(this.swigCPtr, this, paramString));
	}

	public boolean getBool(String paramString) {
		return LeapJNI.Config_getBool(this.swigCPtr, this, paramString);
	}

	public boolean setBool(String paramString, boolean paramBoolean) {
		return LeapJNI.Config_setBool(this.swigCPtr, this, paramString, paramBoolean);
	}

	public int getInt32(String paramString) {
		return LeapJNI.Config_getInt32(this.swigCPtr, this, paramString);
	}

	public boolean setInt32(String paramString, int paramInt) {
		return LeapJNI.Config_setInt32(this.swigCPtr, this, paramString, paramInt);
	}

	public float getFloat(String paramString) {
		return LeapJNI.Config_getFloat(this.swigCPtr, this, paramString);
	}

	public boolean setFloat(String paramString, float paramFloat) {
		return LeapJNI.Config_setFloat(this.swigCPtr, this, paramString, paramFloat);
	}

	public String getString(String paramString) {
		return LeapJNI.Config_getString(this.swigCPtr, this, paramString);
	}

	public boolean setString(String paramString1, String paramString2) {
		return LeapJNI.Config_setString(this.swigCPtr, this, paramString1, paramString2);
	}

	public boolean save() {
		return LeapJNI.Config_save(this.swigCPtr, this);
	}

	public static enum ValueType {
		TYPE_UNKNOWN(0), 
		TYPE_BOOLEAN(1), 
		TYPE_INT32(2), 
		TYPE_FLOAT(6), 
		TYPE_STRING(8);

		private final int swigValue;

		public final int swigValue() {
			return this.swigValue;
		}

		public static ValueType swigToEnum(int paramInt) {
			ValueType[] arrayOfValueType1 = (ValueType[])ValueType.class.getEnumConstants();
			if ((paramInt < arrayOfValueType1.length) && (paramInt >= 0) && (arrayOfValueType1[paramInt].swigValue == paramInt))
				return arrayOfValueType1[paramInt];
			for (ValueType localValueType : arrayOfValueType1)
				if (localValueType.swigValue == paramInt)
					return localValueType;
			throw new IllegalArgumentException("No enum " + ValueType.class + " with value " + paramInt);
		}

		private ValueType() {
			this.swigValue = SwigNext.next;
		}

		private ValueType(int paramInt) {
			this.swigValue = paramInt;
			SwigNext.next = paramInt + 1;
		}

		private ValueType(ValueType paramValueType) {
			this.swigValue = paramValueType.swigValue;
			SwigNext.next = this.swigValue + 1;
		}

		private static class SwigNext {
			private static int next = 0;
		}
	}
}