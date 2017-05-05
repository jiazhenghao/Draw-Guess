package util.leap;

public interface LeapListener {

	public void fingerExist(LeapEvent e);
	
	public void circled(LeapEvent e);
	
	public void swiped(LeapEvent e);
	
	public void screenTapped (LeapEvent e);
	
	public void keyTapped(LeapEvent e);
}
