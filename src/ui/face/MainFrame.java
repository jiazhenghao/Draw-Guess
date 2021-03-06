package ui.face;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import thread.animation.ComponentDropper;
import ui.advanced_appearence.GlassShadow;
import ui.part.component.FrameButton;
import util.FontManager;
import util.ImageManager;
import util.leap.LeapEvent;
import util.leap.LeapListener;
import util.leap.LeapManager;
import util.leap.LeapPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseListener, MouseMotionListener, LeapListener {

	private static MainFrame mainFrame;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private Point location;
	private Point frameLocation;

	private LeapPanel leapPanel = new LeapPanel();
	private JPanel toastPanel = new JPanel();
	private JPanel showPanel = new JPanel();
	private JPanel framePanel = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getFrameBg();
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	};
	private JPanel titlePanel = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getTitleBg();
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	};
	private JPanel viewPanel = new JPanel() {
		private Image img = ImageManager.getDefaultImageManager().getViewBg();
		@Override
		protected void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	};

	private JLabel title = new JLabel("你 画 我 猜");
	private FrameButton minimize = new FrameButton(FrameButton.Type.MINIMIZE);
	private FrameButton maximize = new FrameButton(FrameButton.Type.MAXIMIZE);
	private FrameButton close = new FrameButton(FrameButton.Type.CLOSE);

	private JPanel currentPanel = null;
	private OnLinePanel onLinePanel = new OnLinePanel(this);
	private RoomConnectPanel roomConnectPanel = new RoomConnectPanel(this);
	private IpConnectPanel ipConnectPanel = new IpConnectPanel(this);
	private GamePanel gamePanel = new GamePanel(this);
	
	private Font titleFont = FontManager.getDefaultFontManager().getTitleFont();

	public MainFrame() {
		mainFrame = this;

		new GlassShadow(this);

		initFrameDecorated();
		initFace();

		framePanel.setLayout(null);
		framePanel.add(titlePanel);
		framePanel.add(viewPanel);
		
		this.setContentPane(framePanel);
		this.setGlassPane(leapPanel);
		this.setTitle("你画我猜");
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				quit();
			}
		});

		ComponentDropper.getDefaultDropper().switchPanel(getCurrentPanel(), getOnLinePanel());

		setColor(new Color(211, 211, 211));

		leapPanel.setVisible(true);
	}

	private void initFace() {

		viewPanel.setBounds(0, 30, WIDTH, HEIGHT - 30);
		viewPanel.setBackground(Color.WHITE);
		viewPanel.setLayout(null);
		viewPanel.add(toastPanel);
		viewPanel.add(showPanel);
		viewPanel.add(onLinePanel);
		viewPanel.add(gamePanel);
		viewPanel.add(roomConnectPanel);
		viewPanel.add(ipConnectPanel);

		toastPanel.setBounds(0, 0, WIDTH, HEIGHT - 30);
		toastPanel.setOpaque(false);
		toastPanel.setLayout(null);
		showPanel.setBounds(0, 0, WIDTH, HEIGHT - 30);
		showPanel.setOpaque(false);
		showPanel.setLayout(null);

		onLinePanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
		gamePanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
		roomConnectPanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
		ipConnectPanel.setBounds(0, -900, WIDTH, HEIGHT - 30);
	}

	private void initFrameDecorated() {

		titlePanel.setBounds(0, 0, WIDTH, 30);
		titlePanel.setLayout(null);
		titlePanel.add(title);
		titlePanel.add(minimize);
		titlePanel.add(maximize);
		titlePanel.add(close);

		title.setBounds(20, 0, 200, 30);
		title.setFont(titleFont);

		minimize.setBounds(WIDTH - 99, 0, 29, 15);
		minimize.setToolTipText("最小化");
		minimize.addMouseListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(minimize, this);

		maximize.setBounds(WIDTH - 70, 0, 29, 15);
		maximize.setToolTipText(null);
		maximize.setEnabled(false);

		close.setBounds(WIDTH - 41, 0, 35, 15);
		close.setToolTipText("关闭");
		close.addMouseListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(close, this);
	}

	private void setColor(Color color) {
		titlePanel.setBackground(color);
		viewPanel.setBackground(color);
	}

	public void quit() {
		gamePanel.quit();
		System.exit(0);
	}
	
	public LeapPanel getLeapPanel() {
		return leapPanel;
	}

	public JPanel getToastPanel() {
		return toastPanel;
	}

	public OnLinePanel getOnLinePanel() {
		currentPanel = onLinePanel;
		return onLinePanel;
	}

	public RoomConnectPanel getRoomConnectPanel() {
		currentPanel = roomConnectPanel;
		return roomConnectPanel;
	}

	public IpConnectPanel getIpConnectPanel() {
		currentPanel = ipConnectPanel;
		return ipConnectPanel;
	}

	public GamePanel getGamePanel() {
		currentPanel = gamePanel;
		return gamePanel;
	}

	public GamePanel getGamePanelOnly() {
		return gamePanel;
	}

	public JPanel getCurrentPanel() {
		return currentPanel;
	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this) {
			location = e.getLocationOnScreen();
			frameLocation = this.getLocation();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == minimize) {
			this.setExtendedState(ICONIFIED);
		} else if (e.getSource() == close) {
			quit();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getSource() == this) {
			Point moved = new Point(e.getLocationOnScreen().x - location.x, e.getLocationOnScreen().y - location.y);
			this.setLocation(frameLocation.x + moved.x, frameLocation.y + moved.y);
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void fingerExist(LeapEvent e) {}

	@Override
	public void circled(LeapEvent e) {}

	@Override
	public void swiped(LeapEvent e) {}

	@Override
	public void screenTapped(LeapEvent e) {}

	@Override
	public void keyTapped(LeapEvent e) {
		if (e.getSource() == minimize) {
			this.setExtendedState(ICONIFIED);
		} else if (e.getSource() == close) {
			quit();
		}
	}

}
