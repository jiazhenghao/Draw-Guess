package util;

import java.awt.Font;


public class FontManager {
	
	private static FontManager fontManager = new FontManager();
	
	private Font girlFont = FileUtil.getFont("girl_font.ttc");
	
	private Font babyFont = FileUtil.getFont("baby_font.ttc");
	
	private FontManager() {}
	
	public static FontManager getDefaultFontManager() {
		return fontManager;
	}
	
	public Font getTitleFont() {
		return girlFont.deriveFont(Font.BOLD, 12);
	}
	
	public Font getJumpButtonFont() {
		return girlFont.deriveFont(Font.BOLD, 16);
	}
	
	public Font getNameConfigLabelFont() {
		return girlFont.deriveFont(Font.PLAIN, 30);
	}
	
	public Font getInputFieldFont() {
		return girlFont.deriveFont(Font.PLAIN, 16);
	}
	
	public Font getRoomListLabelFont() {
		return girlFont.deriveFont(Font.BOLD, 24);
	}
	
	public Font getConnectRoomNameFont() {
		return new Font(Font.DIALOG, Font.BOLD, 14);
	}
	
	public Font getConnectRoomCurrentFont() {
		return new Font(Font.DIALOG, Font.BOLD, 12);
	}
	
	public Font getIpConnectLabelFont() {
		return girlFont.deriveFont(Font.BOLD, 16);
	}
	
	public Font getButtonFont() {
		return new Font(Font.DIALOG, Font.BOLD, 18);
	}
	
	public Font getChatTitleFont() {
		return new Font(Font.DIALOG, Font.BOLD, 14);
	}
	
	public Font getRoomInfoFont() {
		return new Font(Font.DIALOG, Font.PLAIN, 12);
	}
	
	public Font getScorePanelFont() {
		return new Font(Font.DIALOG, Font.PLAIN, 12);
	}
	
	public Font getHeaderNoticeFont() {
		return new Font(Font.DIALOG, Font.PLAIN, 12);
	}
	
	public Font getThreeCountdownFont() {
		return babyFont.deriveFont(Font.BOLD, 60);
	}
	
	public Font getGameTimeFont() {
		return babyFont.deriveFont(Font.BOLD, 25);
	}
	
	public Font getAddScoreFont() {
		return babyFont.deriveFont(Font.BOLD, 20);
	}
	
	public Font getToastNoticeFont() {
		return babyFont.deriveFont(Font.PLAIN, 24);
	}
	
	public Font getToastTopicPanelFont() {
		return new Font(Font.DIALOG, Font.PLAIN, 12);
	}
	
	public Font getToastShowTopicFont() {
		return babyFont.deriveFont(Font.BOLD, 22);
	}
	
	public Font getToastResultPanelFont() {
		return new Font(Font.DIALOG, Font.PLAIN, 12);
	}
	
	public Font getToastResultScoreFont() {
		return babyFont.deriveFont(Font.BOLD, 20);
	}
	
}
