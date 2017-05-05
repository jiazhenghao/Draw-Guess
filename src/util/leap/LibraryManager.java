package util.leap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import util.FileUtil;

public class LibraryManager {
	
	private static HashMap<String, String> map = new HashMap<String, String>();
	
	static {
		LibrariesCopy();
	}
	
	private static void LibrariesCopy() {
		try {
			String arch = System.getProperty("sun.arch.data.model").equals("64") ? "x64" : "x86";
			for (String lib : getLibrariesList()) {
				InputStream intput = FileUtil.getInputStream("library", arch + "/" + lib);
				File file = new File(arch, lib);
				if (!file.exists()) {
					file.getParentFile().mkdir();
					fileCopy(intput, file);
				}
				map.put(lib, file.getAbsolutePath());
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	private static ArrayList<String> getLibrariesList() {
		ArrayList<String> libList = new ArrayList<String>();
		try {
			InputStream in = FileUtil.getInputStream("file", "libraries");
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			for (String st = null; (st = input.readLine()) != null;) {
				libList.add(st);
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libList;
	}
	
	private static void fileCopy(InputStream input, File dstFile) throws IOException {
		FileOutputStream out = new FileOutputStream(dstFile);
		
		byte[] b = new byte[1024];
		for (int len = 0; (len = input.read(b)) != -1; ) {
			out.write(b, 0, len);
		}

		out.flush();
		input.close();
		out.close();
	}
	
	public static void loadLibrary(String libname) {
		System.loadLibrary(libname);
	}
	
	public static void load(String filename) {
		System.load(map.get(filename));
	}
}
