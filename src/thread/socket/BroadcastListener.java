package thread.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import ui.part.room_connect.RoomListPanel;
import util.Config;

public class BroadcastListener extends Thread {
	
	private RoomListPanel listPanel;
	
	public BroadcastListener(RoomListPanel listPanel) {
		this.listPanel = listPanel;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				byte[] info = new byte[256];
				DatagramPacket packet = new DatagramPacket(info, info.length);
				MulticastSocket ms = new MulticastSocket(Config.udpPort);
				ms.joinGroup(InetAddress.getByName(BroadcastSender.GROUP));
				ms.receive(packet);
				ms.close();

				InetAddress address = packet.getAddress();
				listPanel.updateList(address.getHostAddress(), new String(info, 0, packet.getLength()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
