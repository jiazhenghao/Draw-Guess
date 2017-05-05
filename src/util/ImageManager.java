package util;

import java.awt.Image;
import java.awt.image.BufferedImage;


public class ImageManager {

	private static ImageManager imageManager = new ImageManager();

	private BufferedImage[] headPortraits = new BufferedImage[] {
			FileUtil.getBufferedImage("background/concave_mini_bg.png"), 
			FileUtil.getBufferedImage("head_portrait/01.jpg"), 
			FileUtil.getBufferedImage("head_portrait/02.jpg"), 
			FileUtil.getBufferedImage("head_portrait/03.jpg"), 
			FileUtil.getBufferedImage("head_portrait/04.jpg"), 
			FileUtil.getBufferedImage("head_portrait/05.jpg"), 
			FileUtil.getBufferedImage("head_portrait/06.jpg"), 
			FileUtil.getBufferedImage("head_portrait/07.jpg"), 
			FileUtil.getBufferedImage("head_portrait/08.jpg"), 
			FileUtil.getBufferedImage("head_portrait/09.jpg"), 
			FileUtil.getBufferedImage("head_portrait/10.jpg"), 
			FileUtil.getBufferedImage("head_portrait/11.jpg"), 
			FileUtil.getBufferedImage("head_portrait/12.jpg"), 
			FileUtil.getBufferedImage("head_portrait/13.jpg"), 
			FileUtil.getBufferedImage("head_portrait/14.jpg"),
			FileUtil.getBufferedImage("background/convex_mini_bg.png")
	};

	private ImageManager() {}

	public static ImageManager getDefaultImageManager() {
		return imageManager;
	}

	public Image getFrameBg() {
		return FileUtil.getImage("background/frame_bg.jpg");
	}

	public Image getTitleBg() {
		return FileUtil.getImage("background/title_bg.jpg");
	}

	public Image[] getMinimizeImages() {
		Image[] minimizeImages = new Image[] {
				FileUtil.getImage("frame_button/minimize_normal.png"),
				FileUtil.getImage("frame_button/minimize_rollover.png"),
				FileUtil.getImage("frame_button/minimize_pressed.png")
		};
		return minimizeImages;
	}

	public Image[] getMaximizeImages() {
		Image[] maximizeImages = new Image[] {
				FileUtil.getImage("frame_button/maximize_normal.png"),
				FileUtil.getImage("frame_button/maximize_rollover.png"),
				FileUtil.getImage("frame_button/maximize_pressed.png")
		};
		return maximizeImages;
	}

	public Image[] getCloseImages() {
		Image[] closeImages = new Image[] {
				FileUtil.getImage("frame_button/close_normal.png"),
				FileUtil.getImage("frame_button/close_rollover.png"),
				FileUtil.getImage("frame_button/close_pressed.png")
		};
		return closeImages;
	}

	public Image getViewBg() {
		return FileUtil.getImage("background/view_bg.jpg");
	}

	public Image getBoardBg() {
		return FileUtil.getImage("background/board_bg.png");
	}

	public Image getInputFieldBg() {
		return FileUtil.getImage("background/input_field_bg.png");
	}

	public Image getJumpButtonBg() {
		return FileUtil.getImage("background/jump_button_bg.png");
	}

	public Image getRoomListBg() {
		return FileUtil.getImage("background/room_list_bg.png");
	}

	public Image getScrollBarBg() {
		return FileUtil.getImage("background/scroll_bar_bg.png");
	}

	public Image getScrollBlockBg() {
		return FileUtil.getImage("background/scroll_block_bg.png");
	}

	public Image getSpeakingBg() {
		return FileUtil.getImage("background/speaking_bg.png");
	}

	private Image getConvexBg() {
		return FileUtil.getImage("background/convex_bg.png");
	}

	private Image getConvexMiniBg() {
		return FileUtil.getImage("background/convex_mini_bg.png");
	}

	private Image getConcaveBg() {
		return FileUtil.getImage("background/concave_bg.png");
	}

	private Image getConcaveMiniBg() {
		return FileUtil.getImage("background/concave_mini_bg.png");
	}

	@SuppressWarnings("unused")
	private Image getConvexCircleBg() {
		return FileUtil.getImage("background/convex_circle_bg.png");
	}

	private Image getConvexCircleMiniBg() {
		return FileUtil.getImage("background/convex_circle_mini_bg.png");
	}

	private Image getConcaveCircleBg() {
		return FileUtil.getImage("background/concave_circle_bg.png");
	}

	private Image getConcaveCircleMiniBg() {
		return FileUtil.getImage("background/concave_circle_mini_bg.png");
	}

	public Image getHeadBg() {
		return getConcaveMiniBg();
	}

	public Image getRoomInfoBg() {
		return getConvexBg();
	}

	public Image getRoomChooserBg() {
		return getConcaveBg();
	}

	public Image getIpConnectBg() {
		return getConvexBg();
	}

	public Image[] getButtonBgs() {
		Image[] buttonBgs = new Image[] {
				getConvexMiniBg(),
				getConcaveMiniBg()
		};
		return buttonBgs;
	}

	public Image getCountdownBg() {
		return getConcaveCircleBg();
	}

	public Image getPigmentBg() {
		return getConcaveMiniBg();
	}

	public Image getThicknessChosenBg() {
		return getConcaveMiniBg();
	}

	public Image getBrushTypeChosenBg() {
		return getConcaveMiniBg();
	}

	public Image getCanvasClearBg() {
		return getConvexMiniBg();
	}

	public Image[] getFingerBgs() {
		Image[] fingerBgs = new Image[] {
				getConvexCircleMiniBg(),
				getConcaveCircleMiniBg()
		};
		return fingerBgs;
	}

	public BufferedImage getHeadPortraits(int i) {
		return headPortraits[i];
	}

	public Image getBrushIcon() {
		return FileUtil.getImage("icon/brush.png");
	}

	public Image getEraserIcon() {
		return FileUtil.getImage("icon/eraser.png");
	}

	public Image getClearIcon() {
		return FileUtil.getImage("icon/clear.png");
	}

	public Image[] getCursorIcon() {
		Image[] cursorIcon = new Image[] {
				FileUtil.getImage("icon/brush.gif"),
				FileUtil.getImage("icon/eraser.gif")
		};
		return cursorIcon;
	}

}
