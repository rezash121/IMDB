package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Response;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class AddRefree extends JFrame {

	private JPanel contentPane;
	private JTextField usernametxtfld;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblEmail;
	private JButton btnSignUp;
	private String message;
	private Response response;
	public AddRefree(ObjectInputStream objectIn,PrintWriter output) {

		setResizable(false);
		setTitle("Sign up");
		response = new Response();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernametxtfld = new JTextField();
		usernametxtfld.setBounds(113, 24, 188, 20);
		contentPane.add(usernametxtfld);
		usernametxtfld.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("user name:");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel.setBounds(27, 27, 76, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(27, 62, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(113, 55, 188, 20);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(113, 90, 188, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblEmail.setBounds(37, 93, 46, 14);
		contentPane.add(lblEmail);
//////////////////////////////////////////////////////////////////////////////////////		
		btnSignUp = new JButton("Add Refree");
		btnSignUp.setFont(new Font("Arial", Font.BOLD, 12));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((usernametxtfld.getText().equals("")||passwordField.getText().equals("")
						||textField.getText().equals("")))
					JOptionPane.showMessageDialog(null, "please fill all of items");
				else{
					output.println("addrefree#username:"+usernametxtfld.getText()+
							"*password:"+passwordField.getText()+"*e_mail:"+
							textField.getText()+"*#");
				}
				try {
					response = (Response) objectIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String result=response.GetResult();
				if(result.equals("completed"))
					{setVisible(false);
					JOptionPane.showMessageDialog(null, "Refree Add to Database");
					}else 
					JOptionPane.showMessageDialog(null, "Sign up Can not done your username or email exist\n"
							+"Enter another username or email");
			}
		});
		btnSignUp.setBounds(113, 126, 126, 23);
		contentPane.add(btnSignUp);
	}

}
