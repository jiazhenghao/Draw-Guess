package thread.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import util.Client;
import util.ClientInfo;
import util.Config;

public class BroadcastSender extends Thread {
	public static String GROUP = "239.255.255.255";
	
	public static final int PID_NO = 0;
	public static final int PORT_NO = 1;
	public static final int HEAD_NO = 2;
	public static final int NAME_NO = 3;
	public static final int CURRENT_NO = 4;
	public static final int C_HEAD_1_NO = 5;
	public static final int C_HEAD_2_NO = 6;
	public static final int C_HEAD_3_NO = 7;
	public static final int C_HEAD_4_NO = 8;
	
	@Override
	public void run() {
		try{
			MulticastSocket ms = new MulticastSocket();
			do {
				send(ms);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while (Config.serving);
			ms.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void send(MulticastSocket ms) throws IOException {
		ArrayList<Client> clientList = ServerAction.getClientList();
		int current = Config.waiting ? clientList.size() : -1;
		int[] clientHeadPortrait = new int[4];
		for (int i = 0; i < clientList.size(); i++) {
			ClientInfo info = clientList.get(i).getInfo();
			if (info != null) {
				clientHeadPortrait[i] = info.getHeadPortrait();
			}
		}
		String info = Config.progressId + ";" + Config.port
				+ ";" + Config.headPortrait + ";" + Config.nickName + " µÄ·¿¼ä" + ";" + current
				+ ";" + clientHeadPortrait[0] + ";" + clientHeadPortrait[1]
				+ ";" + clientHeadPortrait[2] + ";" + clientHeadPortrait[3];
		byte[] infoByte = info.getBytes();
		DatagramPacket packet = new DatagramPacket(infoByte, infoByte.length, InetAddress.getByName(GROUP), Config.udpPort);
		ms.send(packet);
	}
	
	public static void send() {
		try {
			MulticastSocket ms = new MulticastSocket();
			send(ms);
			ms.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
