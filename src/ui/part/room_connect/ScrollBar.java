package ui.part.room_connect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import thread.animation.ComponentMover;
import util.ImageManager;

@SuppressWarnings("serial")
public class ScrollBar extends JPanel implements MouseInputListener {

	public static enum Orientation { HORIZONTAL, VERTICAL }
	private int maximum = 100;
	private Orientation orientation = Orientation.VERTICAL;

	private ImageManager imageManager = ImageManager.getDefaultImageManager();
	private Image blockBg = imageManager.getScrollBlockBg();
	private Image barBg = imageManager.getScrollBarBg();

	private JLabel block = new JLabel(new ImageIcon(blockBg));

	private ComponentMover mover;
	
	public ScrollBar() {
		
		setOpaque(false);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		setLayout(null);
		add(block);
		
		mover = new ComponentMover(block);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		if (orientation == Orientation.VERTICAL) {
			block.setBounds(0, 0, width, width);
		} else {
			block.setBounds(0, 0, height, height);
		}
	}
	public int getValue() {
		if (orientation == Orientation.VERTICAL) {
			return (int) (maximum * 1.0 * block.getY() / (getHeight() - getWidth()));
		} else {
			return (int) (maximum * 1.0 * block.getX() / (getWidth() - getHeight()));
		}
	}
	
	public void setValue(int value) {
		value = Math.max(0, Math.min(value, maximum));
		if (orientation == Orientation.VERTICAL) {
			block.setLocation(0, (getHeight() - getWidth()) * value / maximum);
		} else {
			block.setLocation((getWidth() - getHeight()) * value / maximum, 0);
		}
	}
	
	public int getMaximum() {
		return maximum;
	}
	
	public void setMaximum(int maximum) {
		if (maximum > 0) {
			int value = getValue();
			this.maximum = maximum;
			setValue(value);
		}
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public JLabel getBlock() {
		return block;
	}
	
	public ComponentMover getMover() {
		return mover;
	}
	
	public int scroll(MouseEvent e) {
		int width = getWidth();
		int height = getHeight();
		int fixedPoint;
		if (orientation == Orientation.VERTICAL) {
			int point = e.getY() - (width >> 1);
			fixedPoint = Math.min(height - width, Math.max(0, point));
			mover.setTarget(0, fixedPoint);
		} else {
			int point = e.getX() - (height >> 1);
			fixedPoint = Math.min(width - height, Math.max(0, point));
			mover.setTarget(fixedPoint, 0);
		}
		return fixedPoint;
	}
	
	public void fixedScroll(MouseEvent e) {}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(barBg, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDragged(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		fixedScroll(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		scroll(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
}
