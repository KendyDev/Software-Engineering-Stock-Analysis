import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class LoginGUI {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 535, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setBackground(SystemColor.menu);
		txtpnLogin.setEditable(false);
		txtpnLogin.setText("Username: ");
		txtpnLogin.setBounds(10, 29, 76, 20);
		frame.getContentPane().add(txtpnLogin);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBackground(SystemColor.menu);
		txtpnPassword.setEditable(false);
		txtpnPassword.setText("Password: ");
		txtpnPassword.setBounds(10, 49, 76, 20);
		frame.getContentPane().add(txtpnPassword);
		
		JTextPane txtpnNote = new JTextPane();
		txtpnNote.setText("Note:\r\nSince this is an application that utilizes a login system, it temporarily incorporates an .xml file that stores in the login information of the users to reenact a database. Considering the scope of the project, an actual final release would incorporate a database. This decision was made with the reasoning that this will be tested on multiple machines, and that no database server was a provided when this project was assigned.  An implementation of a database can be easily made as the project is designed in such a manner. \r\n(See Documentation for more information)\r\n\r\nThose willing to test the application may use the following login/password combinations:\r\nuser1/pass1\r\nuser2/pass2\r\nuser3/pass3");
		txtpnNote.setBounds(10, 117, 499, 272);
		frame.getContentPane().add(txtpnNote);
		
		usernameField = new JTextField();
		usernameField.setBounds(96, 29, 86, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 49, 86, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JTextPane errorField = new JTextPane();
		errorField.setBackground(SystemColor.menu);
		errorField.setEditable(false);
		errorField.setBounds(291, 29, 183, 77);
		frame.getContentPane().add(errorField);
		
		JButton btnAuthenticate = new JButton("Enter");
		btnAuthenticate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)  {
				Authenticator authenticator = new Authenticator();
				char[] passwordInput = passwordField.getPassword();
				
				if(usernameField.getText().isEmpty() && String.valueOf(passwordInput).isEmpty()){
					errorField.setText("No Username and Password Entered");
				}
				else if(usernameField.getText().equals("")){
					errorField.setText("No Username Entered");
				}
				else if(String.valueOf(passwordInput).isEmpty()){
					errorField.setText("No Password Entered");
				} else
					try {
						if(authenticator.authenticate(usernameField.getText(), String.valueOf(passwordInput)).equals("exception")){
							errorField.setText("ERROR!\nMake sure that the user.xml file is in the same directory as this application");
						}
						else if(authenticator.authenticate(usernameField.getText(), String.valueOf(passwordInput)).equals("failure")){
							errorField.setText("Incorrect Username/Password combination!");
						}
						else if(authenticator.authenticate(usernameField.getText(), String.valueOf(passwordInput)).equals("success")){
							MyApp windowMyApp = new MyApp();
							windowMyApp.getFrame().setVisible(true);
							closeWindow();
						}
					} catch (ParserConfigurationException | SAXException | IOException e) {
						errorField.setText("ERROR!\nMake sure that the user.xml file is in the same directory as this application");
						e.printStackTrace();
					}
					
			}
		});
		btnAuthenticate.setBounds(192, 29, 89, 40);
		frame.getContentPane().add(btnAuthenticate);
		
		
	}
	public void closeWindow(){
		frame.dispose();
	}
}
