package laucher;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ui.face.MainFrame;

public class GameLauncher {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}

}
