package util.leap;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.SwingUtilities;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;

public class LeapManager {
	public static final float LAEP_SCALING_X = 2;
	public static final float LEAP_SCALING_X = 2;
	public static final int LEAP_OFFSET_Y = 100;
	
	private static LeapManager leapManager = new LeapManager();
	
	private HashMap<Component, LeapListener> listeners = new HashMap<Component, LeapListener>();
	
	public static Point getPointOnScreen(Vector point) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		float x = point.getX() * LAEP_SCALING_X + (dimension.width >> 1);
		float y = -(point.getY() - LEAP_OFFSET_Y) * LEAP_SCALING_X + dimension.height;
		return new Point((int) x, (int) y);
	}
	
	public static LeapManager getDefaultLeapManager() {
		return leapManager;
	}
	
	private LeapManager() {
		new Thread() {
			@Override
			public void run() {
				try {
					SampleListener listener = new SampleListener();
					Controller controller = new Controller();
					controller.addListener(listener);
				} catch (ExceptionInInitializerError e) {
					e.printStackTrace();
					return;
				}
				while (true) {
					try {
						Thread.sleep(Integer.MAX_VALUE);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	}
	
	public void addLeapListener(Component component, LeapListener listener) {
		listeners.put(component, listener);
	}
	
	public void removeLeapListener(Component component) {
		listeners.remove(component);
	}
	
	private void fingerExist(HandList hands) {
		
		Iterator<Entry<Component, LeapListener>> it = listeners.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Component, LeapListener> entry = it.next();
			Component component = entry.getKey();
			LeapListener listener = entry.getValue();
			
			LeapEvent e = new LeapEvent(component, hands, 0, 0);
			listener.fingerExist(e);
		}
	}
	
	private void circled(CircleGesture circle) {
		sendEvent(circle, Gesture.Type.TYPE_CIRCLE, circle.center());
	}
	
	private void swiped(SwipeGesture swipe) {
		sendEvent(swipe, Gesture.Type.TYPE_SWIPE, swipe.position());
	}
	
	private void screenTapped(ScreenTapGesture screenTap) {
		sendEvent(screenTap, Gesture.Type.TYPE_SCREEN_TAP, screenTap.position());
	}
	
	private void keyTapped(KeyTapGesture keyTap) {
		sendEvent(keyTap, Gesture.Type.TYPE_KEY_TAP, keyTap.position());
	}
	
	private void sendEvent(Object source, Gesture.Type type, Vector location) {
		
		Point locationOnScreen = getPointOnScreen(location);

		Iterator<Entry<Component,LeapListener>> it = listeners.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Component,LeapListener> entry = it.next();
			Component component = entry.getKey();
			LeapListener listener = entry.getValue();
			Point locationOnComponent = (Point) locationOnScreen.clone();
			SwingUtilities.convertPointFromScreen(locationOnComponent, component);
			if (component.contains(locationOnComponent)) {
				LeapEvent e = new LeapEvent(component, source, (int) locationOnComponent.x, (int) locationOnComponent.y);
				switch (type) {
				case TYPE_CIRCLE:
					listener.circled(e);
					break;
				case TYPE_SWIPE:
					listener.swiped(e);
					break;
				case TYPE_SCREEN_TAP:
					listener.screenTapped(e);
					break;
				case TYPE_KEY_TAP:
					listener.keyTapped(e);
					break;
				default:
					break;
				}
			}
		}
	}
	
	class SampleListener extends Listener {

		public void onConnect(Controller controller) {
			System.out.println("Connected");
			controller.enableGesture(Gesture.Type.TYPE_SWIPE);
			controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
			controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
			controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		}

		public void onFrame(Controller controller) {
			Frame frame = controller.frame();

			fingerExist(frame.hands());

			GestureList gestures = frame.gestures();
			for (Gesture gesture : gestures) {
				
				switch (gesture.type()) {
				case TYPE_CIRCLE:
					circled(new CircleGesture(gesture));
					break;
				case TYPE_SWIPE:
					swiped(new SwipeGesture(gesture));
					break;
				case TYPE_SCREEN_TAP:
					screenTapped(new ScreenTapGesture(gesture));
					break;
				case TYPE_KEY_TAP:
					keyTapped(new KeyTapGesture(gesture));
					break;
				default:
					System.out.println("Unknown gesture type.");
					break;
				}
			}
		}
	}
}
