package ui.face;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JLabel;

import thread.animation.ButtonDropper;
import thread.socket.ClientAction;
import ui.part.component.JumpButton;
import ui.part.room_connect.ListScrollPanel;
import util.Config;
import util.FontManager;
import util.leap.LeapEvent;
import util.leap.LeapManager;

@SuppressWarnings("serial")
public class RoomConnectPanel extends FacePanel {
	
	private JLabel roomListLabel = new JLabel("房间列表：");
	private ListScrollPanel roomListPanel = new ListScrollPanel();
	
	private JumpButton back = new JumpButton("返 回");
	private JumpButton joinIn = new JumpButton("加入游戏");
	private JumpButton ipConnect = new JumpButton("ＩＰ连接");
	
	private Font roomListLabelFont = FontManager.getDefaultFontManager().getRoomListLabelFont();
	
	public RoomConnectPanel(MainFrame owner) {
		super(owner);

		roomListLabel.setFont(roomListLabelFont);
		roomListLabel.setBounds(100, 40, 300, 30);
		roomListPanel.setBounds(100, 75, 620, 400);
		int temp = (int) ((MainFrame.WIDTH - (JumpButton.WIDTH << 1)) * JumpButton.GOLD_SECTION);
		joinIn.setBounds(MainFrame.WIDTH - temp - (JumpButton.WIDTH << 1), 475, JumpButton.WIDTH, JumpButton.HEIGHT);
		ipConnect.setBounds(temp + JumpButton.WIDTH, 475, JumpButton.WIDTH, JumpButton.HEIGHT);
		back.setBounds(600, 30, 48, 60);
		
		back.addMouseListener(this);
		joinIn.addMouseListener(this);
		ipConnect.addMouseListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(back, this);
		LeapManager.getDefaultLeapManager().addLeapListener(joinIn, this);
		LeapManager.getDefaultLeapManager().addLeapListener(ipConnect, this);
		
		add(roomListLabel);
		add(back);
		add(roomListPanel);
		add(joinIn);
		add(ipConnect);
		
		setLayout(null);
		setEnabled(false);
	}
	
	private void buttonPressed(EventObject e) {
		if (isEnabled()) {
			if (e.getSource() == back) {
				ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), owner.getCurrentPanel(), owner.getOnLinePanel());
			} else if (e.getSource() == joinIn) {
				ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), null, null);
				String ip = roomListPanel.getRoomIP();
				if (!"".equals(ip)) {
					Config.ip = ip;
					Config.port = roomListPanel.getRoomPort();
					new Thread() {
						public void run() {
							try {
								Thread.sleep(200);
								new ClientAction().start();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						};
					}.start();
				}
			} else if (e.getSource() == ipConnect) {
				ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), owner.getCurrentPanel(), owner.getIpConnectPanel());
			}
		}
	}

	@Override
	public void keyTapped(LeapEvent e) {
		buttonPressed(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonPressed(e);
	}
}