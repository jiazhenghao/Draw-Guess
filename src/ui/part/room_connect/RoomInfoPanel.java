package ui.part.room_connect;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.Config;
import util.FileUtil;
import util.FontManager;
import util.ImageManager;

@SuppressWarnings("serial")
public class RoomInfoPanel extends JPanel {
	
	private static RoomInfoPanel roomInfoPanel;
	
	private String progressId;
	private String ip;
	private int port;
	private Image headPortrait;
	private String name;
	private String current;
	private int[] clientHeadPortraitsN = new int[Config.maximum];
	private Image[] clientHeadPortraits = new Image[Config.maximum];
	
	private FontManager fontManager = FontManager.getDefaultFontManager();
	private Font connectRoomNameFont = fontManager.getConnectRoomNameFont();
	private Font connectRoomCurrentFont = fontManager.getConnectRoomCurrentFont();
	private ImageManager imageManager = ImageManager.getDefaultImageManager();
	private Image img = imageManager.getRoomInfoBg();
	
	public RoomInfoPanel(String progressId, String ip, int port, int headPortrait, String name, String current, int... clientHeadPortraits) {
		this.progressId = progressId;
		this.ip = ip;
		this.port = port;
		this.headPortrait = FileUtil.getImage("head_portrait/" + (headPortrait + 100 + "").substring(1) + ".jpg");
		this.name = name;
		this.current = current;
		for (int i = 0; i < clientHeadPortraitsN.length; i++) {
			this.clientHeadPortraitsN[i] = -1;
		}
		setClientHeadPortraits(clientHeadPortraits);
		setOpaque(false);
	}
	
	public String getProgressId() {
		return progressId;
	}
	
	public String getIp() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setCurrent(String current) {
		this.current = current;
	}
	
	public void setClientHeadPortraits(int... clientHeadPortraits) {
		for (int i = 0; i < Config.maximum; i++) {
			if (this.clientHeadPortraitsN[i] != clientHeadPortraits[i]) {
				this.clientHeadPortraitsN[i] = clientHeadPortraits[i];
				this.clientHeadPortraits[i] = imageManager.getHeadPortraits(clientHeadPortraits[i]);
			}
		}
	}
	
	public void choose() {
		RoomInfoPanel.roomInfoPanel = this;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (this != roomInfoPanel) {
			g2d.setClip(0, 0, height >> 1, height);
			g2d.drawImage(img, 0, 0, height, height, this);
			g2d.setClip(height >> 1, 0, width - (height & -2), height);
			g2d.drawImage(img, 0, 0, width, height, this);
			g2d.setClip(width - (height >> 1), 0, height >> 1, height);
			g2d.drawImage(img, width - height, 0, height, height, this);
			g2d.setClip(null);
		}
		g2d.drawImage(headPortrait, 10, 10, 90, 90, this);
		int nameWidth = SwingUtilities.computeStringWidth(getFontMetrics(connectRoomNameFont), name);
		g2d.setFont(connectRoomNameFont);
		g2d.drawString(name, width - 10 - nameWidth, 55);
		String currentNum = "房间人数：" + current + "/" + Config.maximum;
		int currentWidth = SwingUtilities.computeStringWidth(getFontMetrics(connectRoomCurrentFont), currentNum);
		g2d.setFont(connectRoomCurrentFont);
		g2d.drawString(currentNum, width - 10 - currentWidth, 90);
		for (int i = 0; i < Config.maximum; i++) {
			g2d.drawImage(clientHeadPortraits[i], i * 65 + 15, 115, 50, 50, this);
		}
	}
}
