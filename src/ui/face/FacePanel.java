package ui.face;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import util.ImageManager;
import util.leap.LeapEvent;
import util.leap.LeapListener;

@SuppressWarnings("serial")
public abstract class FacePanel extends JPanel implements MouseListener, LeapListener {

	protected MainFrame owner;
	private Image img = ImageManager.getDefaultImageManager().getBoardBg();
	
	public FacePanel(MainFrame owner) {
		this.owner = owner;
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void fingerExist(LeapEvent e) {}

	@Override
	public void circled(LeapEvent e) {}

	@Override
	public void swiped(LeapEvent e) {}

	@Override
	public void screenTapped(LeapEvent e) {}

	@Override
	public void keyTapped(LeapEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
