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
 * �������ڴ�����Ŀ��Դ�Ĺ�����
 * <p>
 * Ҫע����ǣ���Ŀ��Դ����Ҫ�ŵ�����Ŀ¼src��
 */
public class FileUtil {
	//��Ŀ�ļ�����λ��srcĿ¼�µ�����3�����ļ���֮һ
	private static final String FILE = "resource/file/";	//�����ͨ�ļ�
	private static final String IMAGE = "resource/image/";	//���ͼƬ�ļ�
	private static final String AUDIO = "resource/audio/";	//�����Ƶ�ļ�
	private static final String FONT = "resource/font/";	//��������ļ�
	private static final String LIB = "resource/library/";	//��������ļ�
	private static final HashMap<String, String> MAP = new HashMap<String, String>();
	static {
		MAP.put("file", FILE);
		MAP.put("image", IMAGE);
		MAP.put("audio", AUDIO);
		MAP.put("font", FONT);
		MAP.put("library", LIB);
	}

	/**
	 * ������Դ�ļ���URL��ַ
	 * @param type ��Դ�ļ�����,�����ļ���ͼƬ����Ƶ
	 * @param path ��Դ�ļ�·��(�磺me.jpg)
	 * @return ��Դ�ļ���URL��ַ
	 */
	public static URL getURL(String type, String path) {
		String dir = MAP.get(type);
		return URLClassLoader.getSystemClassLoader().getResource(dir + path);
	}

	//��ȡ������
	public static InputStream getInputStream(String type, String path) {
		String dir = MAP.get(type);
		return URLClassLoader.getSystemClassLoader().getResourceAsStream(dir + path);
	}

	//��ȡ������Դ
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

	//��ȡ�ļ���Դ
	public static File getFile(String path) {
		URL url = getURL("file", path);
		if (url == null) {
			return null;
		}
		return new File(url.getFile());
	}

	//��ȡ��Ƶ��Դ
	public static AudioClip getAudio(String path) {
		URL url = getURL("audio", path);
		if(url == null) {
			return null;
		}
		return Applet.newAudioClip(url);
	}

	//��ȡͼƬ��Դ
	public static Image getImage(String path) {
		URL url = getURL("image", path);
		if(url == null) {
			return null;
		}
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	//��ȡͼƬ��Դ
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
