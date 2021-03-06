package ui.face;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JLabel;

import thread.animation.ButtonDropper;
import thread.animation.ComponentDropper;
import thread.animation.Toast;
import thread.socket.BroadcastSender;
import thread.socket.ClientAction;
import thread.socket.ServerAction;
import thread.socket.TCPServerThread;
import ui.part.component.JumpButton;
import ui.part.game.BrushConfigPanel;
import ui.part.game.ChatPanel;
import ui.part.game.DrawPanel;
import ui.part.game.HeaderPanel;
import ui.part.game.ScorePanel;
import ui.part.game.ServerInfoPanel;
import util.Config;
import util.DataPackage.DataType;
import util.FontManager;
import util.leap.LeapEvent;
import util.leap.LeapManager;

@SuppressWarnings("serial")
public class GamePanel extends FacePanel {
	
	private ServerInfoPanel infoPanel = new ServerInfoPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	private BrushConfigPanel brushPanel = new BrushConfigPanel();
	private DrawPanel drawPanel = new DrawPanel();
	private ScorePanel scorePanel = new ScorePanel();
	private ChatPanel chatPanel = new ChatPanel();
	
	private JLabel countDown = new JLabel("3");

	private JumpButton back = new JumpButton("离 开");
	
	private Font countdownFont = FontManager.getDefaultFontManager().getThreeCountdownFont();
	
	public GamePanel(MainFrame owner) {
		super(owner);
		
		infoPanel.setBounds(40, 30, 540, 15);
		headerPanel.setBounds(40, 45, 540, 50);
		brushPanel.setBounds(40, 95, 60, 345);
		drawPanel.setBounds(100, 95, 480, 345);
		scorePanel.setBounds(40, 440, 540, 100);
		chatPanel.setBounds(580, 70, 180, 470);
		back.setBounds(600, 30, 48, 60);
		countDown.setBounds(290, 165, 100, 100);

		countDown.setHorizontalAlignment(JLabel.CENTER);
		countDown.setFont(countdownFont);
		countDown.setVisible(false);
		
		back.addMouseListener(this);
		LeapManager.getDefaultLeapManager().addLeapListener(back, this);
		
		add(countDown);
		add(infoPanel);
		add(headerPanel);
		add(drawPanel);
		add(scorePanel);
		add(back);
		add(brushPanel);
		add(chatPanel);
		
		setLayout(null);
		setEnabled(false);
	}
	
	public void gamePlaying(boolean playing) {
		countDownBeforeDraw(0);
		brushPanel.initBrush();
		if (playing) {
			headerPanel.setInfoVisible(true);
			drawPanel.setEnabled(false);
			brushPanel.setEnabled(false);
			scorePanel.initScore("0");
		} else {
			headerPanel.setInfoVisible(false);
			drawPanel.setEnabled(true);
			brushPanel.setEnabled(true);
			scorePanel.setPainter(null);
			scorePanel.initScore("");
			chatPanel.chatPaneClear();
		}
	}

	public ServerInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public HeaderPanel getHeaderPanel() {
		return headerPanel;
	}

	public BrushConfigPanel getBrushPanel() {
		return brushPanel;
	}

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}
	
	public void countDownBeforeDraw(int time) {
		countDown.setText(time + "");
		if (time > 0) {
			countDown.setVisible(true);
		} else {
			countDown.setVisible(false);
		}
	}
	
	public void quit() {
		gamePlaying(false);
		if (Config.serving) {
			Config.waiting = false;
			Config.serving = false;
			ServerAction.sendData(DataType.LOGOUT, null);
			TCPServerThread.closeServer();
			BroadcastSender.send();
			Toast.getDefaultToast().makeToastClear();
			Toast.getDefaultToast().makeToastNotice("房间已解散！", 1000);
			ComponentDropper.getDefaultDropper().switchPanel(owner.getCurrentPanel(), owner.getOnLinePanel());
		} else {
			ClientAction.sendData(DataType.LOGOUT, null);
			ClientAction.close();
		}
	}
	
	private void exit(final EventObject e) {
		if (isEnabled()) {
			ButtonDropper.getDefaultDropper().pressed((Component) e.getSource(), null, null);
			new Thread() {
				public void run() {
					try {
						Thread.sleep(200);
						quit();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				};
			}.start();
		}
	}

	@Override
	public void keyTapped(LeapEvent e) {
		exit(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		exit(e);
	}
}
