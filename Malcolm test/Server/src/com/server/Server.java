package com.server;
/* Thread Calculator Server*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server {

	private ServerSocket servSock;
	private Socket connection;
	private ObjectOutputStream os;
	private ObjectInputStream is;

	public Server() {

		try {

			servSock = new ServerSocket(3310);
		} catch (IOException ex) {

			ex.printStackTrace();
		}
		while (true) {
			try {
				System.out.println("Waiting for client on port 3309 ");
				connection = servSock.accept();
				System.out.println("Connection accepted.");
				//Handler();
				new Thread(new Threaded(connection)).start();


				
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	/*public void Handler() {
		Integer num1;
		Integer num2;
		int result = 0;
		try {
			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());
			num1 = (Integer) is.readObject();
			num2 = (Integer) is.readObject();
			String operator = (String) is.readObject();
			if (operator.equals("plus")) {
				 result = num1 + num2;
			} else if (operator.equals("minus")) {
				 result = num1 - num2;
			} else if (operator.equals("multiply")) {
				 result = num1 * num2;
			} else if (operator.equals("divide")) {
			     result = num1 / num2;
			}
			System.out.println(result);
			os.writeObject(result);
		} catch (ClassNotFoundException|ArithmeticException|IOException ex) {
			ex.printStackTrace();
		}
	}*/

	public static void main(String[] args) {

		new Server();
	}
}
