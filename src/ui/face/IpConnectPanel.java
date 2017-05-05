package ui.face;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JPanel;

import thread.animation.ButtonDropper;
import thread.animation.Toast;
import thread.socket.ClientAction;
import ui.part.component.JumpButton;
import ui.part.component.MyTextField;
import util.Config;
import util.FontManager;
import util.ImageManager;
import util.leap.LeapEvent;
import util.leap.LeapManager;

@SuppressWarnings("serial")
public class IpConnectPanel extends FacePanel {
	
	private JPanel panel = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getIpConnectBg();
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	};
	private JLabel IPLabel = new JLabel("������IP��");
	private MyTextField IPjtf = new MyTextField(Config.ip);
	private JLabel portLabel = new JLabel("�� �� �ţ�");
	private MyTextField portjtf = new MyTextField(Config.port + "");
	
	private JumpButton connect = new JumpButton("������Ϸ");
	private JumpButton back = new JumpButton("�� ��");
	
	private Font ipConnectfont = FontManager.getDefaultFontManager().getIpConnectLabelFont();
	
	public IpConnectPanel(MainFrame owner) {
		super(owner);
		
		panel.setOpaque(false);
		panel.setBounds(140, 110, 520, 300);
		
		panel.setLayout(null);
		IPLabel.setFont(ipConnectfont);
		IPLabel.setBounds(125, 200, 100, 30);
		IPjtf.setBounds(215, 200, 160, 30);
		IPjtf.setToolTipText("IP��ʽ ��127.0.0.1");
		portLabel.setFont(ipConnectfont);
		portLabel.setBounds(125, 240, 100, 30);
		portjtf.setBounds(215, 240, 160, 30);
		portjtf.setToolTipText("Port��ʽ ��С��65536������");
		
		connect.addMouseListener(this);
		back.addMouseListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(connect, this);
		LeapManager.getDefaultLeapManager().addLeapListener(back, this);
		
		panel.add(IPLabel);
		panel.add(IPjtf);
		panel.add(portLabel);
		panel.add(portjtf);
		
		connect.setBounds(MainFrame.WIDTH - JumpButton.WIDTH >> 1, 470, JumpButton.WIDTH, JumpButton.HEIGHT);
		back.setBounds(600, 30, 48, 60);
		
		add(panel);
		add(connect);
		add(back);

		setLayout(null);
		setEnabled(false);
	}
	
	private void buttonPressed(EventObject e) {
		if (e.getSource() == connect) {
			ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), null, null);
			String ip = IPjtf.getText();
			String port = portjtf.getText();
			if (!ip.matches("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}")) {
				Toast.getDefaultToast().makeToastNotice("IP��ʽ����Ӧ�� 127.0.0.1 ��", 1000);
				return;
			} else if (!port.matches("\\d{1,5}") || Integer.parseInt(port) >= 2 << 16) {
				Toast.getDefaultToast().makeToastNotice("�˿ڸ�ʽ����ӦȡС��65536��������", 1000);
				return;
			} else if ("127.0.0.1".equals(ip)) {
				Toast.getDefaultToast().makeToastNotice("�������ӱ�������������������������IP��", 1000);
				return;
			}
			Config.ip = ip;
			Config.port = Integer.parseInt(port);
			new ClientAction().start();
		} else if (e.getSource() == back) {
			ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), owner.getCurrentPanel(), owner.getRoomConnectPanel());
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
