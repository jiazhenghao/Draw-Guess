package ui.advanced_appearence;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public class AuxiliaryWindow extends JWindow implements ComponentListener, WindowListener, WindowFocusListener {
	
	protected JFrame owner;
	
	public AuxiliaryWindow(JFrame owner) {
		this.owner = owner;
		this.owner.addComponentListener(this);
		this.owner.addWindowListener(this);
		this.owner.addWindowFocusListener(this);
		this.setBackground(new Color(0, 0, 0, 0));
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}
	
	@Override
	public void setLocation(int parentX, int parentY) {
		super.setLocation(parentX, parentY);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource() instanceof Window) {
			Window window = (Window) e.getSource();
			this.setSize(window.getWidth(), window.getHeight());
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		if (e.getSource() instanceof Window) {
			Window window = (Window) e.getSource();
			this.setLocation(window.getX(), window.getY());
		}
	}

	@Override
	public void componentShown(ComponentEvent e) {
		this.setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		this.setVisible(false);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		setSize(owner.getWidth(), owner.getHeight());
		setLocation(owner.getX(), owner.getY());
		this.setVisible(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {
		this.setVisible(false);
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		this.setVisible(true);
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		this.setVisible(true);
	}

	@Override
	public void windowLostFocus(WindowEvent e) {}
}
