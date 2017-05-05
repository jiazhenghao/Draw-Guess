package ui.part.game;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import thread.socket.ClientAction;
import thread.socket.ServerAction;
import ui.face.MainFrame;
import ui.part.game.TypeLabel.Type;
import util.Config;
import util.DataPackage.DataType;
import util.ImageManager;
import util.MyPath;
import util.leap.LeapEvent;
import util.leap.LeapListener;
import util.leap.LeapManager;

import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;


@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements MouseInputListener, LeapListener {
	
	private Point[] oldPosition = new Point[3];
	private int[] pathIndex = new int[3];
	private boolean[] releaseFinger = new boolean[2];
	private ArrayList<MyPath> paths = new ArrayList<MyPath>();
	
	private BufferedImage bimg;
	private Graphics2D g2d;
	
	private boolean dragged;
	
	private Image[] cursorIcon = ImageManager.getDefaultImageManager().getCursorIcon();
	
	public DrawPanel() {
		
		setCursor(Type.BRUSH);
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(this, this);
	}
	
	public BufferedImage getBimg() {
		return bimg;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		
		bimg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		g2d = bimg.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.clearRect(0, 0, width, height);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Color oldColor = g2d.getColor();
		Stroke oldStroke = g2d.getStroke();
		g2d.clearRect(0, 0, bimg.getWidth(), bimg.getHeight());
		synchronized (paths) {
			for (MyPath path : paths) {
				g2d.setColor(path.getColor());
				g2d.setStroke(path.getStroke());
				g2d.draw(path.getPath());
			}
		}
		g2d.setColor(oldColor);
		g2d.setStroke(oldStroke);
		g.drawImage(bimg, 0, 0, this);
	}
	
	@Override
	protected void paintBorder(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(1, 1, width - 3, height - 3);
	}
	
	private void sendDraw(int index, Point position) {
		if (Config.serving) {
			draw(index, position);
			ServerAction.sendData(DataType.BRUSH_POSITION, new Object[]{ index, position });
		} else {
			ClientAction.sendData(DataType.BRUSH_POSITION, new Object[]{ index, position });
		}
	}
	
	public void draw(int index, Point newPosition) {
		if (newPosition != null) {
			if (oldPosition[index] == null) {
				MyPath path = new MyPath(g2d.getColor(), g2d.getStroke(), newPosition);
				paths.add(path);
				pathIndex[index] = paths.size() - 1;
			}
			MyPath path = paths.get(pathIndex[index]);
			path.lineTo(newPosition);
		}
		oldPosition[index] = newPosition;
		repaint();
	}
	
	public void setPigment(Color color) {
		draw(0, null);
		draw(1, null);
		draw(2, null);
		if (color == Color.WHITE) {
			setCursor(Type.ERASER);
		} else {
			setCursor(Type.BRUSH);
			
		}
		g2d.setColor(color);
		MainFrame.getMainFrame().getLeapPanel().setColor(color);
	}
	
	public void setThickness(int thickness) {
		g2d.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}
	
	public void canvasClear() {
		synchronized (paths) {
			draw(0, null);
			draw(1, null);
			draw(2, null);
			paths.clear();
			repaint();
		}
	}
	
	public void setCursor(Type type) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		setCursor(toolkit.createCustomCursor(cursorIcon[type == Type.BRUSH ? 0 : 1], new Point(6, 25), type + ""));
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isEnabled()) {
			dragged = true;
			sendDraw(0, e.getPoint());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isEnabled()) {
			dragged = false;
			sendDraw(0, null);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isEnabled() && dragged) {
			sendDraw(0, e.getPoint());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void fingerExist(LeapEvent e) {
		HandList hands = (HandList) e.getData();
		for (int i = 0; i < 2; i++) {
			FingerList fingers = hands.get(i).fingers();
			if (isEnabled() && fingers.count() == 1) {
				Vector position = fingers.get(0).tipPosition();
				Point locationOnScreen = LeapManager.getPointOnScreen(position);
				SwingUtilities.convertPointFromScreen(locationOnScreen, this);
				if (position.getZ() < -100) {
					sendDraw(i + 1, new Point(locationOnScreen.x,locationOnScreen.y));
					releaseFinger[i] = false;
					continue;
				}
			}
			if (!releaseFinger[i]) {
				sendDraw(i + 1, null);
				releaseFinger[i] = true;
			}
		}
	}

	@Override
	public void circled(LeapEvent e) {}

	@Override
	public void swiped(LeapEvent e) {}

	@Override
	public void screenTapped(LeapEvent e) {}

	@Override
	public void keyTapped(LeapEvent e) {}
	
}
