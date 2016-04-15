package com.client;

/* Thread Calculator Server*/
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class Client extends JFrame implements ActionListener {
	
	private JLabel number1, number2, resultLbl;
	private JTextField numberTxt, number2Txt, resultTxt;
	private JRadioButton plus, minus, multiply, divide;
	private JButton btnCalculate;
	JPanel panNum, panNum2, panRadio, panResultl, button;
	private Socket conn;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public Client(){
		super("Calculator");
		this.setLayout(new GridLayout(6,1));
		this.initializeComponents();
		this.addComponentsToPanels();
		this.addPanelsToWindow();
		this.setWindowProperties();
		registerListeners();
		
		try{
			conn = new Socket(InetAddress.getLocalHost(), 3310);
			output = new ObjectOutputStream(conn.getOutputStream());
			input = new ObjectInputStream(conn.getInputStream());
		}catch(IOException ex){
			
			ex.printStackTrace();
		}
	}
	
	private void initializeComponents(){
		
		 number1 = new JLabel("Number 1");
		 number2 = new JLabel("Number 2");
		 resultLbl = new JLabel("Result");
		 
		 
		 numberTxt = new JTextField(5);
		 number2Txt = new JTextField(5);
		 resultTxt = new JTextField(5);
		 
		plus = new JRadioButton("Plus");
		minus = new JRadioButton("Minus");
		multiply = new JRadioButton("Multiply");
		divide = new JRadioButton("Divide");
		 
		 ButtonGroup group = new ButtonGroup();
		 group.add(plus);
		 group.add(minus);
		 group.add(multiply);
		 group.add(divide);
		 
		 btnCalculate = new JButton("Calculate");
		 
		 panNum = new JPanel(new GridLayout(1,4));
		 panNum2 = new JPanel(new GridLayout(1,5));
		 panRadio = new JPanel(new GridLayout(1,6));
		 panResultl = new JPanel(new GridLayout(1,7));
		 button = new JPanel(new GridLayout(1,8));
	}
	
	
	private void addComponentsToPanels(){
		panNum.add(number1);
		panNum.add(numberTxt);
		panNum2.add(number2);
		panNum2.add(number2Txt);
		panResultl.add(resultLbl);
		panResultl.add(resultTxt);	
		panRadio.add(plus);
		panRadio.add(minus);   //run it and get that error
		panRadio.add(multiply);
		panRadio.add(divide);
		
		button.add(btnCalculate);
		
	}
	
	private void addPanelsToWindow(){
		this.add(panNum);
		this.add(panNum2);
		this.add(panRadio);
		this.add(panResultl);
		this.add(button);
	}
	
	private void setWindowProperties() {
		this.setSize(800, 300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
	}
	
	public void registerListeners(){
		btnCalculate.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event){
		if (event.getSource().equals(btnCalculate)) {
			String to = "";
			if(plus.isSelected()){
				to = "plus";
			} else if(minus.isSelected()){
				to = "minus";
			} else if(multiply.isSelected()){
				to = "multiply";
			} else if(divide.isSelected()){
		    	to = "divide";
		    }
		    try {
				output.writeObject(Integer.parseInt(numberTxt.getText()));
			    output.writeObject(Integer.parseInt(number2Txt.getText()));
			    output.writeObject(to);
			    Integer res = (Integer)input.readObject();
			    resultTxt.setText(res.toString());
			} catch (NumberFormatException|IOException|ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		new Client();
	}
}
