package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Connect.Connect;

public class View extends JFrame implements ActionListener{
	
	private JMenuBar menuBar;
	private JMenu optionsMenu;
	private JMenuItem insertItem, updateItem,deleteItem;
	private JLabel idLbl,firstNameLbl,lastNameLbl;
	private JTextField idField,firstNameField,lastNameField;
	private JButton okButton;
	private JPanel infoPnl,buttonPnl;
	private Connect c;
	
	public View(Connect link){
		super("Students");
		this.c = link;
		this.setLayout(new GridLayout(3,1));
		this.initializeComponents();
		this.addItemsToMenu();
		this.addComponentsToPanel();
		this.addPanelToWindow();
		this.setWindowProperties();
		this.setListeners();
	}

	private void addItemsToMenu() {
		
		optionsMenu.add(insertItem);
		optionsMenu.add(updateItem);
		optionsMenu.add(deleteItem);
		
		menuBar.add(optionsMenu);
		
	}

	private void addComponentsToPanel() {
		// TODO Auto-generated method stub
		infoPnl.add(idLbl);infoPnl.add(idField);
		infoPnl.add(firstNameLbl);infoPnl.add(firstNameField);
		infoPnl.add(lastNameLbl);infoPnl.add(lastNameField);
		
		buttonPnl.add(okButton);
	}

	private void addPanelToWindow() {
		// TODO Auto-generated method stub
		this.setJMenuBar(menuBar);
		this.add(infoPnl);
		this.add(buttonPnl);
	}

	private void setWindowProperties() {
		// TODO Auto-generated method stub
		this.setSize(400,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setListeners() {
		// TODO Auto-generated method stub
		okButton.addActionListener(this);
		updateItem.addActionListener(this);
	}

	private void initializeComponents() {
		// TODO Auto-generated method stub
		idLbl = new JLabel("ID Number");
		firstNameLbl =  new JLabel("First Name");
		lastNameLbl =  new JLabel("Last Name");
		
		idField =  new JTextField(15);
		firstNameField =  new JTextField(15);
		lastNameField =  new JTextField(15);
		
		okButton =  new JButton("OK");
		
		infoPnl =  new JPanel(new GridLayout(3,2));
		buttonPnl =  new JPanel(new GridLayout(1,1));
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0,0,441,21);
		
		optionsMenu =  new JMenu("Options");
		insertItem =  new JMenuItem("Insert");
		updateItem = new JMenuItem("Update");
		deleteItem = new JMenuItem("Delete");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(okButton)){
			this.c.insert((String)firstNameField.getText(),(String)lastNameField.getText());
		}else{
			JOptionPane.showMessageDialog(null, "yo","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
			okButton.setText("Update");
			//addComponentsToPanel();
			//addPanelToWindow();
		}
		
	}
	
	public static void main(String[] args){
		Connect conc = new Connect();
		View v =  new View(conc);
	}

}
