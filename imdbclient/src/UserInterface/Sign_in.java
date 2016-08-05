package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Response;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Sign_in extends JFrame {

	private JPanel contentPane;
	private JTextField usernametxtfld;

	private String message;
	private Response response;
	private JPasswordField passwordField;

	public Sign_in(ObjectInputStream objectIn, PrintWriter output, Socket socket) {
		setResizable(false);
		response = new Response();
		setTitle("Sign in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 223);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblUserName.setBounds(135, 11, 73, 14);
		contentPane.add(lblUserName);

		usernametxtfld = new JTextField();
		usernametxtfld.setBounds(30, 35, 287, 20);
		contentPane.add(usernametxtfld);
		usernametxtfld.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblPassword.setBounds(139, 60, 66, 14);
		contentPane.add(lblPassword);

		JButton cancelbtn = new JButton("Cancel");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		cancelbtn.setFont(new Font("Arial", Font.BOLD, 12));
		cancelbtn.setBounds(228, 135, 89, 23);
		contentPane.add(cancelbtn);
		//////////////////////////////////////////////////////////////////////////////
		JButton signupbtn = new JButton("Sign up");
		signupbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sign_up signupframe = new Sign_up(objectIn, output);
				signupframe.setVisible(true);

			}
		});
		signupbtn.setFont(new Font("Arial", Font.BOLD, 12));
		signupbtn.setBounds(129, 135, 89, 23);
		contentPane.add(signupbtn);

		JButton signinbtn = new JButton("Sign in");
		signinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (usernametxtfld.getText().equals("") || passwordField.getText().equals(""))
					JOptionPane.showMessageDialog(null, "please fill all of items");
				else {
					output.println("signin#username:" + usernametxtfld.getText() + "*password:"
							+ passwordField.getText() + "*#");
				
				try {
					response = (Response) objectIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String result = response.GetResult();
				if (result.equals("Ordinary")) {
					dispose();
					JOptionPane.showMessageDialog(null, "Sign in Is Completed");
					OrdinaryUserPage OUP = new OrdinaryUserPage(objectIn, output, usernametxtfld.getText());
					OUP.setVisible(true);
				} else if(result.equals("Referee")){
					dispose();
					JOptionPane.showMessageDialog(null, "Sign in Is Completed");
					RefreeFrame RF=new RefreeFrame(objectIn, output,usernametxtfld.getText());
					RF.setVisible(true);
				} else if(result.equals("Manager")){
					dispose();
					JOptionPane.showMessageDialog(null, "Sign in Is Completed");
					MangerFrame MF=new MangerFrame(objectIn, output,usernametxtfld.getText());
					MF.setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "Username or Password Are Incorrect");
				}

			}
		});
		signinbtn.setFont(new Font("Arial", Font.BOLD, 12));
		signinbtn.setBounds(30, 135, 89, 23);
		contentPane.add(signinbtn);

		passwordField = new JPasswordField();
		passwordField.setBounds(30, 85, 287, 20);
		contentPane.add(passwordField);
	}
}
