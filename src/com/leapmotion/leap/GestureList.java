package com.leapmotion.leap;

import java.util.Iterator;

public class GestureList extends Interface implements Iterable<Gesture> {
  private long swigCPtr;

  public GestureList(long paramLong, boolean paramBoolean) {
    super(LeapJNI.GestureList_SWIGUpcast(paramLong), paramBoolean);
    this.swigCPtr = paramLong;
  }

  public static long getCPtr(GestureList paramGestureList) {
    return paramGestureList == null ? 0L : paramGestureList.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (this.swigCPtr != 0L) {
      if (this.swigCMemOwn) {
        this.swigCMemOwn = false;
        LeapJNI.delete_GestureList(this.swigCPtr);
      }
      this.swigCPtr = 0L;
    }
    super.delete();
  }

  public Iterator<Gesture> iterator() {
    return new GestureListIterator();
  }

  public GestureList() {
    this(LeapJNI.new_GestureList(), true);
  }

  public int count() {
    return LeapJNI.GestureList_count(this.swigCPtr, this);
  }

  public boolean isEmpty() {
    return LeapJNI.GestureList_isEmpty(this.swigCPtr, this);
  }

  public Gesture get(int paramInt) {
    return new Gesture(LeapJNI.GestureList_get(this.swigCPtr, this, paramInt), true);
  }

  public GestureList append(GestureList paramGestureList) {
    return new GestureList(LeapJNI.GestureList_append(this.swigCPtr, this, getCPtr(paramGestureList), paramGestureList), false);
  }

  public class GestureListIterator implements Iterator<Gesture> {
    int index = 0;

    public GestureListIterator() {  } 
    public boolean hasNext() { return this.index < GestureList.this.count(); }

    public Gesture next() {
      return GestureList.this.get(this.index++);
    }

    public void remove() {
    }
  }
}