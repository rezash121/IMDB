package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Rate;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ReviewsList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<Rate> Ratelist;


	public ReviewsList(ArrayList<Rate> list,String Filmname) {
		Ratelist=list;
		setTitle("Review");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReviewsAbout = new JLabel("Reviews About:");
		lblReviewsAbout.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblReviewsAbout.setBounds(41, 11, 100, 14);
		contentPane.add(lblReviewsAbout);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(151, 8, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Filmname);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 36, 380, 203);
		contentPane.add(scrollPane);
		
		JList jlist = new JList();
		scrollPane.setViewportView(jlist);
		DefaultListModel dlm = new DefaultListModel();
		for (int i = 0; i < Ratelist.size(); i++)
			dlm.addElement(Ratelist.get(i).toString());
		jlist.setModel(dlm);
	}
}
