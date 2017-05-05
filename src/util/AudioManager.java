package util;

import java.applet.AudioClip;


public class AudioManager {
	
	private static AudioManager audioBean = new AudioManager();
	
	private AudioClip login = FileUtil.getAudio("login.wav");
	private AudioClip hint = FileUtil.getAudio("hint.wav");
	private AudioClip guessed = FileUtil.getAudio("guessed.wav");
	private AudioClip countdown = FileUtil.getAudio("countdown.wav");
	private AudioClip showTopic = FileUtil.getAudio("show_topic.wav");
	
	private AudioManager() {}
	
	public static AudioManager getDefaultAudioManager() {
		return audioBean;
	}
	
	public void login() {
		login.play();
	}
	
	public  void hint() {
		hint.play();
	}
	
	public  void guessed() {
		guessed.play();
	}
	
	public  void countdown() {
		countdown.play();
	}
	
	public  void showTopic() {
		showTopic.play();
	}

}
