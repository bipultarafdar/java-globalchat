package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

	private static int portNumber = 44444;
	private static ServerSocket serverSocket;
	protected static List<ClientThread> clients;
	
	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(portNumber);
			acceptClients();
		} catch (IOException e) {
			System.err.println("Server setup failed");
			e.printStackTrace();
		}
	}
	
	public static void acceptClients() {
		clients = new ArrayList<ClientThread>();
		try {
			while(true) {
				Socket socket = serverSocket.accept();
				ClientThread client = new ClientThread(socket);
				Thread t = new Thread(client);
				t.start();
				clients.add(client);
			}
		} catch (IOException e) {
			System.err.println("Connection Failure");
			e.printStackTrace();
		}
	}
	
}
