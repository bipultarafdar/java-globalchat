package server;

import java.io.*;
import java.net.Socket;

public class ClientThread extends ChatServer implements Runnable{
	
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			while(!socket.isClosed()) {
				String s;
				if((s = in.readUTF()) != null) {
					for(ClientThread client:clients) {
						client.getWriter().writeUTF(s);
					}
				} else {
					for(ClientThread client:clients) {
						client.getWriter().writeUTF("");
					}
				}
			}
			
		} catch (IOException e) {
			System.out.println("Connection failure");
			e.printStackTrace();
		}
	}
	
	public DataOutputStream getWriter() {
		return out;
	}

}
