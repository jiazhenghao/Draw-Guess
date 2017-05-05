package ui.part.room_connect;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import thread.animation.ComponentMover;
import thread.socket.BroadcastSender;
import util.ImageManager;
import util.leap.LeapEvent;
import util.leap.LeapListener;
import util.leap.LeapManager;

import com.leapmotion.leap.CircleGesture;

@SuppressWarnings("serial")
public class RoomListPanel extends JPanel implements MouseListener, LeapListener {
	
	private String roomIP = "";
	private int roomPort = 0;
	private ArrayList<RoomInfoPanel> roomList = new ArrayList<RoomInfoPanel>();
	
	private float progress;
	private int index;
	
	private JPanel chooser = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getRoomChooserBg();
		@Override
		public void paintComponent(Graphics g) {
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.setClip(0, 0, height >> 1, height);
			g2d.drawImage(img, 0, 0, height, height, this);
			g2d.setClip(height >> 1, 0, width - height, height);
			g2d.drawImage(img, 0, 0, width, height, this);
			g2d.setClip(width - (height >> 1), 0, height >> 1, height);
			g2d.drawImage(img, width - height, 0, height, height, this);
			g2d.setClip(null);
		}
	};
	private ComponentMover mover;
	
	public RoomListPanel() {
		chooser.setVisible(false);
		chooser.setOpaque(false);
		chooser.addMouseListener(this);
		
		setLayout(null);
		add(chooser);
		
		setOpaque(false);
		
		LeapManager.getDefaultLeapManager().addLeapListener(this, this);
		
		mover = new ComponentMover(chooser);
	}
	
	public String getRoomIP() {
		if (roomIP.equals("") && roomList.size() > 0) {
			roomIP = roomList.get(0).getIp();
		}
		return roomIP;
	}
	
	public int getRoomPort() {
		if (roomPort == 0 && roomList.size() > 0) {
			roomPort = roomList.get(0).getPort();
		}
		return roomPort;
	}
	
	private Rectangle getRoomBounds(int index) {
		Container container = getParent();
		int pageWidth = container.getWidth();
		int pageHeight = container.getHeight();
		
		int x = (index & 1) * (pageWidth >> 1);
		int y = (index >> 1) * (pageHeight >> 1);
		int roomWidth = (pageWidth >> 1); 
		int roomHeight = (pageHeight >> 1);
		
		return new Rectangle(x, y, roomWidth, roomHeight);
	}
	
	public void updateList(String ip, String info) {
		String[] infos = info.split(";");
		int port = Integer.parseInt(infos[BroadcastSender.PORT_NO]);
		int headPortrait = Integer.parseInt(infos[BroadcastSender.HEAD_NO]);
		int clientHeadPortrait1 = Integer.parseInt(infos[BroadcastSender.C_HEAD_1_NO]);
		int clientHeadPortrait2 = Integer.parseInt(infos[BroadcastSender.C_HEAD_2_NO]);
		int clientHeadPortrait3 = Integer.parseInt(infos[BroadcastSender.C_HEAD_3_NO]);
		int clientHeadPortrait4 = Integer.parseInt(infos[BroadcastSender.C_HEAD_4_NO]);
		
		int num = roomList.size();
		for (int i = 0; i < num; i++) {
			RoomInfoPanel roomInfo = roomList.get(i);
			if (roomInfo.getProgressId().equals(infos[BroadcastSender.PID_NO])) {
				if ("-1".equals(infos[BroadcastSender.CURRENT_NO])) {
					roomRemove(roomInfo, i, num);
				} else {
					roomUpdate(roomInfo, infos, clientHeadPortrait1, clientHeadPortrait2, clientHeadPortrait3, clientHeadPortrait4);
				}
				return;
			}
		}
		roomAdd(infos, ip, port, headPortrait, clientHeadPortrait1, clientHeadPortrait2, clientHeadPortrait3, clientHeadPortrait4);
	}
	
	private void roomRemove(RoomInfoPanel roomInfo, int i, int num) {
		LeapManager.getDefaultLeapManager().addLeapListener(roomInfo, this);
		roomList.remove(roomInfo);
		remove(roomInfo);
		num--;
		for (int j = i; j < num; j++) {
			roomList.get(j).setBounds(getRoomBounds(j));
		}
		fixedChosen(i, num);
		addOrRemove(num);
	}
	
	private void roomUpdate(RoomInfoPanel roomInfo, String[] infos, int... clientHeadPortrait) {
		roomInfo.setCurrent(infos[BroadcastSender.CURRENT_NO]);
		roomInfo.setClientHeadPortraits(clientHeadPortrait[0], clientHeadPortrait[1], clientHeadPortrait[2], clientHeadPortrait[3]);
		roomInfo.repaint();
	}
	
	private void roomAdd(String[] infos, String ip, int port, int headPortrait, int... clientHeadPortrait) {
		RoomInfoPanel newRoom = new RoomInfoPanel(infos[BroadcastSender.PID_NO], ip, port,
				headPortrait, infos[BroadcastSender.NAME_NO], infos[BroadcastSender.CURRENT_NO], 
				clientHeadPortrait[0], clientHeadPortrait[1], clientHeadPortrait[2], clientHeadPortrait[3]);
		newRoom.setBounds(getRoomBounds(roomList.size()));
		newRoom.addMouseListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(newRoom, this);
		if (roomList.isEmpty()) {
			newRoom.choose();
		}
		roomList.add(newRoom);
		add(newRoom);
		addOrRemove(roomList.size());
	}
	
	private void fixedChosen(int i, int num) {
		Container container = getParent();
		int n = (chooser.getY() / (container.getHeight() >> 1) << 1) + (chooser.getX() / (container.getWidth() >> 1));
		if (n >= i && n != 0) {
			RoomInfoPanel room = roomList.get(Math.max(0, n - 1));
			if (n == i) {
				roomIP = room.getIp();
				roomPort = room.getPort();
			}
			n--;
			room.choose();
			mover.setTarget(room.getX(), room.getY());
		}
	}
	
	private void addOrRemove(int num) {
		Container container = getParent();
		int pageWidth = container.getWidth();
		int pageHeight = container.getHeight();
		int height = Math.max(pageHeight, ((num + 1) >> 1) * (pageHeight >> 1));
		setSize(pageWidth, height);
		((ListScrollPanel) SwingUtilities.getAncestorOfClass(ListScrollPanel.class, this)).scrollList(0);
		
		if (!chooser.isVisible() && !roomList.isEmpty()) {
			chooser.setBounds(0, 0, pageWidth >> 1, height >> 1);
			chooser.setVisible(true);
		} else if (chooser.isVisible() && roomList.isEmpty()) {
			chooser.setVisible(false);
		}
	}
	
	private void roomChoose(RoomInfoPanel roomInfo) {
		roomInfo.choose();
		mover.setTarget(roomInfo.getX(), roomInfo.getY());
		roomIP = roomInfo.getIp();
		roomPort = roomInfo.getPort();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof RoomInfoPanel) {
			roomChoose((RoomInfoPanel) e.getSource());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void fingerExist(LeapEvent e) {}

	@Override
	public void circled(LeapEvent e) {
		CircleGesture circle = (CircleGesture) e.getData();
		if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4) {
			progress = circle.progress();
		} else {
			progress = -circle.progress();
		}
		if (circle.state() == CircleGesture.State.STATE_START) {
			index = 0;
		}
		if (Math.abs(progress - index) > 1) {
			((ListScrollPanel) SwingUtilities.getAncestorOfClass(ListScrollPanel.class, this)).scrollList((int) (progress - index));
			index = (int) progress;
		}
	}

	@Override
	public void swiped(LeapEvent e) {}

	@Override
	public void screenTapped(LeapEvent e) {}

	@Override
	public void keyTapped(LeapEvent e) {
		if (e.getSource() instanceof RoomInfoPanel) {
			roomChoose((RoomInfoPanel) e.getSource());
		}
	}
}
