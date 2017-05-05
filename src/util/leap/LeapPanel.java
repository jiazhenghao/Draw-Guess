package util.leap;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.ImageManager;

import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;

@SuppressWarnings("serial")
public class LeapPanel extends JPanel implements LeapListener {
	
	private final int fingerDiameter = 10;
	private final int fingerWidth = 5;
	
	private Point2D.Float[][] handPositions = new Point2D.Float[2][5];
	private float[] tipRadius = new float[2];
	private int alpha;
	private Color color;
	
	private Image[] img = ImageManager.getDefaultImageManager().getFingerBgs();
	
	public LeapPanel() {
		LeapManager.getDefaultLeapManager().addLeapListener(this, this);
		setOpaque(false);
		setColor(Color.BLACK);
	}
	
	public void setColor(Color color) {
		int rgb = color.getRGB() & 0xffffff | (color == Color.WHITE ? 0 : alpha << 24);
		this.color = new Color(rgb);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			Graphics2D g2d = (Graphics2D) g;
    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			Color oldColor = g2d.getColor();
			
			g2d.setColor(color);

			for (int i = 0; i < handPositions.length; i++) {
				for (int j = 0; j < handPositions[i].length; j++) {
					Point2D.Float finger = handPositions[i][j];
					if (finger != null) {
						g2d.drawImage(img[0], (int) (finger.x - tipRadius[i] - fingerWidth), (int) (finger.y - tipRadius[i] - fingerWidth),
								(int) (tipRadius[i] * 2 + (fingerWidth << 1)), (int) (tipRadius[i] * 2 + (fingerWidth << 1)), this);
						g2d.drawImage(img[1], (int) (finger.x - tipRadius[i]), (int) (finger.y - tipRadius[i]),
								(int) (tipRadius[i] * 2), (int) (tipRadius[i] * 2), this);
						
						if (tipRadius[i] < 1) {
							g2d.fillOval((int) (finger.x) - (fingerWidth + 1 >> 1), (int) (finger.y) - (fingerWidth + 1 >> 1),
									fingerWidth + 1, fingerWidth + 1);
						}
						
//						g2d.setFont(new Font(Font.DIALOG, Font.BOLD, (int) tipRadius[i] << 1));
//						g2d.drawString(j + "", finger.x - tipRadius[i] / 2, finger.y + tipRadius[i]);
//						g2d.setColor(Color.LIGHT_GRAY);
//						g2d.draw(outer);
					}
				}
			}
			g2d.setColor(oldColor);
		}
	}

	@Override
	public void fingerExist(LeapEvent e) {
		HandList hands = (HandList) e.getData();
		for (int i = 0; i < 2; i++) {
			FingerList fingers = hands.get(i).fingers();
			int radiusTemp = 0;
			for (int j = 0; j < fingers.count(); j++) {
				Vector position = fingers.get(j).tipPosition();
				Point locationOnScreen = LeapManager.getPointOnScreen(position);
				SwingUtilities.convertPointFromScreen(locationOnScreen, this);
				handPositions[i][j] = new Point2D.Float(locationOnScreen.x,locationOnScreen.y);
				radiusTemp += position.getZ() / fingers.count();
			}
			tipRadius[i] = Math.max(0, Math.min(fingerDiameter, radiusTemp * fingerDiameter / 100 + fingerDiameter));

			for (int j = fingers.count(); j < handPositions[i].length; j++) {
				handPositions[i][j] = null;
			}
		}
		repaint();
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
