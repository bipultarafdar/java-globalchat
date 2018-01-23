package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private static int portNumber = 44444;
	private static String host = "127.0.0.1";
	
	public static void main(String[] args) {
		System.out.println("Please enter your name");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		sc.close();
		try {
			Socket socket = new Socket(host, portNumber);
			ServerThread server = new ServerThread(socket, name);
			server.initiate();
		} catch (UnknownHostException e) {
			System.out.println("Host unknown");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Connection Failure.");
			e.printStackTrace();
		}
	}
	
}
