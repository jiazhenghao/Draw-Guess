package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 * 该类用于处理项目资源的工具类
 * <p>
 * 要注意的是：项目资源必须要放到工程目录src下
 */
public class FileUtil {
	//项目文件必须位于src目录下的下列3个子文件夹之一
	private static final String FILE = "resource/file/";	//存放普通文件
	private static final String IMAGE = "resource/image/";	//存放图片文件
	private static final String AUDIO = "resource/audio/";	//存放音频文件
	private static final String FONT = "resource/font/";	//存放字体文件
	private static final String LIB = "resource/library/";	//存放字体文件
	private static final HashMap<String, String> MAP = new HashMap<String, String>();
	static {
		MAP.put("file", FILE);
		MAP.put("image", IMAGE);
		MAP.put("audio", AUDIO);
		MAP.put("font", FONT);
		MAP.put("library", LIB);
	}

	/**
	 * 返回资源文件的URL地址
	 * @param type 资源文件类型,包括文件、图片和音频
	 * @param path 资源文件路径(如：me.jpg)
	 * @return 资源文件的URL地址
	 */
	public static URL getURL(String type, String path) {
		String dir = MAP.get(type);
		return URLClassLoader.getSystemClassLoader().getResource(dir + path);
	}

	//读取输入流
	public static InputStream getInputStream(String type, String path) {
		String dir = MAP.get(type);
		return URLClassLoader.getSystemClassLoader().getResourceAsStream(dir + path);
	}

	//获取字体资源
	public static Font getFont(String path) {
		try {
			InputStream input = getInputStream("font", path);
			if (input != null) {
				return Font.createFont(Font.TRUETYPE_FONT, input);
			}
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	//获取文件资源
	public static File getFile(String path) {
		URL url = getURL("file", path);
		if (url == null) {
			return null;
		}
		return new File(url.getFile());
	}

	//获取音频资源
	public static AudioClip getAudio(String path) {
		URL url = getURL("audio", path);
		if(url == null) {
			return null;
		}
		return Applet.newAudioClip(url);
	}

	//获取图片资源
	public static Image getImage(String path) {
		URL url = getURL("image", path);
		if(url == null) {
			return null;
		}
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	//读取图片资源
	public static BufferedImage getBufferedImage(String path) {
		try {
			InputStream input = getInputStream("image", path);
			return ImageIO.read(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
