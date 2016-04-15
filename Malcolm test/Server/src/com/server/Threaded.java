package com.server;
/* Thread Calculator Server*/
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Threaded implements Runnable{
	
	
	private Socket connection;
	private ObjectOutputStream os;
	private ObjectInputStream is;
	
	
	
	Threaded(Socket connec)
	{
		
		connection= connec;
		
	}
	
	
	public void run(){
		
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
		
		
	}

}
