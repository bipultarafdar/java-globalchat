package client;

import java.io.*;
import java.net.Socket;

public class ServerThread {
	
	private String name;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream clientIn;
	private DataInputStream serverIn;
	
	public ServerThread(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}
	
	public void initiate() {
		try {
			out = new DataOutputStream(socket.getOutputStream());
			serverIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			clientIn = new DataInputStream(System.in);
			
			while(!socket.isClosed()) {
				String data1 = clientIn.readUTF();
				if(data1 != null) {
					out.writeUTF(name + ">" + data1);
					System.out.println(name + ">" + data1);
				}
				String data = serverIn.readLine();
				if(data != null) {
					System.out.println(data);
				}				
			}
			
		} catch (IOException e) {
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		
	}

}
