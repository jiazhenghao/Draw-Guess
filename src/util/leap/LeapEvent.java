package util.leap;

import java.util.EventObject;

@SuppressWarnings("serial")
public class LeapEvent extends EventObject {
	private Object data;
	private int x;
	private int y;
	
	public LeapEvent(Object source, Object data, int x, int y) {
		super(source);
		setData(data);
		setPoint(x, y);
	}
	
	public Object getData() {
		return data;
	}
	
	private void setData(Object data) {
		this.data = data;
	}
	
	public void setPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
