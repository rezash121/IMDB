package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Response;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class RateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField titletxtfld;
	private Response response;
	private String message;
	private ButtonGroup radioGroup;
	private JTextArea DiscriptiontextArea;
	private String FilmName;
	private String UserName;
	public RateFrame(ObjectInputStream objectIn, PrintWriter output,String FilmName,String UserName) {
		this.FilmName=FilmName;
		this.UserName=UserName;
		response = new Response();
		setResizable(false);
		setTitle("Rate");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 374, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtn1 = new JRadioButton("1");
		rdbtn1.setBounds(111, 31, 41, 23);
		contentPane.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("2");
		rdbtn2.setBounds(154, 31, 41, 23);
		contentPane.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("3");
		rdbtn3.setBounds(197, 31, 41, 23);
		contentPane.add(rdbtn3);
		
		JRadioButton rdbtn4 = new JRadioButton("4");
		rdbtn4.setBounds(240, 31, 41, 23);
		contentPane.add(rdbtn4);
		
		JRadioButton rdbtn5 = new JRadioButton("5");
		rdbtn5.setBounds(283, 31, 41, 23);
		contentPane.add(rdbtn5);
		
		radioGroup=new ButtonGroup();
		radioGroup.add(rdbtn1);
		radioGroup.add(rdbtn2);
		radioGroup.add(rdbtn3);
		radioGroup.add(rdbtn4);
		radioGroup.add(rdbtn5);
		
		JLabel lblTitle = new JLabel("  Title:");
		lblTitle.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblTitle.setBounds(54, 69, 48, 14);
		contentPane.add(lblTitle);
		
		titletxtfld = new JTextField();
		titletxtfld.setBounds(112, 66, 212, 20);
		contentPane.add(titletxtfld);
		titletxtfld.setColumns(10);
		
		JLabel lblDiscreption = new JLabel("    Discreption:");
		lblDiscreption.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblDiscreption.setBounds(10, 94, 92, 14);
		contentPane.add(lblDiscreption);
		
		JTextArea DiscriptiontextArea = new JTextArea();
		DiscriptiontextArea.setLineWrap(true);
		DiscriptiontextArea.setBounds(111, 97, 212, 114);
		contentPane.add(DiscriptiontextArea);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message="rate#FilmName:"+FilmName+"*UserName:"+UserName+"*";
				if(rdbtn1.isSelected())
					message+="rateNumber:"+rdbtn1.getText()+"*";
				else if(rdbtn2.isSelected())
					message+="rateNumber:"+rdbtn2.getText()+"*";
				else if(rdbtn3.isSelected())
					message+="rateNumber:"+rdbtn3.getText()+"*";
				else if(rdbtn4.isSelected())
					message+="rateNumber:"+rdbtn4.getText()+"*";
				else if(rdbtn5.isSelected())
					message+="rateNumber:"+rdbtn5.getText()+"*";
				if(titletxtfld.getText().equals("")||
						DiscriptiontextArea.getText().equals("")||
						(!rdbtn1.isSelected()&&!rdbtn2.isSelected()&&!rdbtn3.isSelected()&&
						!rdbtn4.isSelected()&&!rdbtn5.isSelected()))
					JOptionPane.showMessageDialog(null, "please fill all of items");
				else{
					message+="title:"+titletxtfld.getText()+"*Discription:"+DiscriptiontextArea.getText()+"*#";
					output.println(message);
				}
				try {
					response = (Response) objectIn.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String result=response.GetResult();
				if(result.equals("Rate Is Registerd"))
				{dispose();
				JOptionPane.showMessageDialog(null, "Rating Complete");
				}else {
				dispose();
				JOptionPane.showMessageDialog(null, "You Rated This Film Befor");
				}
			}
		});
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 12));
		btnSubmit.setBounds(138, 221, 89, 23);
		contentPane.add(btnSubmit);
		
		
		JLabel lblRaetNumber = new JLabel("Rate number:");
		lblRaetNumber.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblRaetNumber.setBounds(10, 35, 90, 14);
		contentPane.add(lblRaetNumber);
		

	}
}
