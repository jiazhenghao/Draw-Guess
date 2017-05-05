package ui.part.room_connect;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import thread.animation.ComponentMover;
import thread.socket.BroadcastListener;
import util.ImageManager;

@SuppressWarnings("serial")
public class ListScrollPanel extends JPanel implements MouseWheelListener {
	
	private int scrollWidth = 20;
	private int borderWidth = 25;

	private JPanel viewPortPanel = new JPanel();
	private RoomListPanel listPanel = new RoomListPanel();
	private ListScrollBar scrollBar = new ListScrollBar();

	private ComponentMover mover;
	
	private Image img = ImageManager.getDefaultImageManager().getRoomListBg();
	
	public ListScrollPanel() {
		viewPortPanel.add(listPanel);
		viewPortPanel.setLayout(null);
		viewPortPanel.setOpaque(false);
		
		add(viewPortPanel);
		add(scrollBar);
		
		setLayout(null);
		setOpaque(false);

		this.addMouseWheelListener(this);
		
		mover = new ComponentMover(listPanel);
	}
	
	public String getRoomIP() {
		return listPanel.getRoomIP();
	}
	
	public int getRoomPort() {
		return listPanel.getRoomPort();
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		viewPortPanel.setBounds(borderWidth, borderWidth, width - scrollWidth - (borderWidth << 1), height - (borderWidth << 1));
		scrollBar.setBounds(width - scrollWidth, 0, scrollWidth, height);

		new BroadcastListener(listPanel).start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(img, 0, 0, getWidth() - scrollWidth, getHeight(), this);
	}
	
	public void scrollList(int line) {
		int pageHeight = viewPortPanel.getHeight();
		if (pageHeight != 0) {
			int targetLine = (int) (-listPanel.getY() * 1.0 / (pageHeight >> 1) + 1 + 0.5) + line;
			int wholeHeight = listPanel.getHeight();
			int wholeLine = wholeHeight / (pageHeight >> 1) - 1;
			targetLine = Math.max(1, Math.min(wholeLine, targetLine));
			int y = -(targetLine - 1) * (pageHeight >> 1);
			mover.setTarget(0, y);

			double percent = y * 1.0 / (pageHeight - wholeHeight);
			int point = (int) ((scrollBar.getHeight() - scrollBar.getWidth()) * percent);
			scrollBar.getMover().setTarget(0, point);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scrollList(e.getWheelRotation());
	}
	
	class ListScrollBar extends ScrollBar {
		@Override
		public int scroll(MouseEvent e) {
			int fixedPoint = super.scroll(e);
			
			double percent = fixedPoint * 1.0 / (getHeight() - getWidth());
			int pageHeight = ListScrollPanel.this.getHeight();
			int wholeHeight = listPanel.getHeight();
			int y = (int) ((pageHeight - wholeHeight) * percent);
			mover.setTarget(0, y);
			
			return fixedPoint;
		}
		
		@Override
		public void fixedScroll(MouseEvent e) {
			scrollList(0);
		}
	}
}
