package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Film;
import imdb.Response;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class guest extends JFrame {
	
	private String message;
	private JPanel contentPane;
	private PrintWriter output;
	private ObjectInputStream objectIn;
	private JTextField nametextField;
	private JTextField textField_1;
	private JList list_1;
	private BufferedReader input;
	private Response response;
	private Socket socket;
	public guest(Socket socket) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.socket=socket;
		setResizable(false);
		setTitle("IMDB");
		
		try {
			output = new PrintWriter(socket.getOutputStream(), true);
			objectIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Film> list=new ArrayList<>();
		response = new Response();
		setBounds(100, 100, 443, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
		btnNewButton.setBounds(154, 76, 139, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message="search#";
				if(!(nametextField.getText().equals("")))
					message+="name:"+nametextField.getText()+"*";
				if(!(textField_1.getText().equals("")))
					message+="year:"+textField_1.getText()+"*";
				if((nametextField.getText().equals("")&&textField_1.getText().equals("")))
					JOptionPane.showMessageDialog(null, "please fill one item");
				else{
				output.println(message+"#");
				try {
					response = (Response) objectIn.readObject();
					String result=response.GetResult();
					if(result.equals("Ready")){
					ArrayList<Film> list=(ArrayList<Film>) objectIn.readObject();
					DefaultListModel listModel = (DefaultListModel) list_1.getModel();
					listModel.removeAllElements();
					DefaultListModel dlm=new DefaultListModel();
					for(int i=0;i<list.size();i++)
						dlm.addElement(list.get(i).toString());
					list_1.setModel(dlm);
					System.out.println(list.get(0).toString());
					}
					else{
						DefaultListModel listModel = (DefaultListModel) list_1.getModel();
						listModel.removeAllElements();
						DefaultListModel dlm=new DefaultListModel();
						dlm.addElement(response.GetResult());
						list_1.setModel(dlm);
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		nametextField = new JTextField();
		nametextField.setBounds(81, 45, 103, 20);
		contentPane.add(nametextField);
		nametextField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(269, 45, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 108, 387, 172);
		contentPane.add(scrollPane);
		
		list_1 = new JList();
		scrollPane.setViewportView(list_1);
		DefaultListModel dlm=new DefaultListModel();
		dlm.addElement("Results....");
		list_1.setModel(dlm);
		
		JLabel lblNewLabel = new JLabel("name:");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel.setBounds(25, 48, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblYear = new JLabel("year:");
		lblYear.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblYear.setBounds(213, 48, 46, 14);
		contentPane.add(lblYear);
		
		JLabel lblNewLabel_1 = new JLabel("SearchBy:");
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 12));
		lblNewLabel_1.setBounds(25, 23, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblToFullAccess = new JLabel("To full access the program please sign up");
		lblToFullAccess.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblToFullAccess.setBounds(99, 291, 243, 14);
		contentPane.add(lblToFullAccess);
		
		JButton btnNewButton_1 = new JButton("Sign in");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Sign_in s1=new Sign_in(objectIn,output,socket);
				s1.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1.setBounds(323, 11, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
}
