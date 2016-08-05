package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Film;
import imdb.Rate;
import imdb.Response;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RefreeFrame extends JFrame {

	private JPanel contentPane;
	private String RefreeName;
	private String message;
	private Response response;
	private ArrayList<Rate> Ratelist;
	public RefreeFrame(ObjectInputStream objectIn, PrintWriter output,String refreename) {
		this.RefreeName=refreename;
		response=new Response();
		Ratelist=new ArrayList<Rate>();
		setTitle("IMDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRates = new JLabel("Rates:");
		lblRates.setFont(new Font("Georgia", Font.BOLD, 12));
		lblRates.setBounds(27, 24, 46, 14);
		contentPane.add(lblRates);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 49, 521, 182);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		DefaultListModel dlm=new DefaultListModel();
		dlm.addElement("Results....");
		list.setModel(dlm);
		
		JButton signoutbtn = new JButton("Sign out");
		signoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message="SignOut#UserName:"+refreename;
				output.println(message);
				System.exit(0);
				
			}
		});
		signoutbtn.setFont(new Font("Arial", Font.BOLD, 12));
		signoutbtn.setBounds(482, 11, 89, 23);
		contentPane.add(signoutbtn);
		
		JButton editbtn = new JButton("Edit Profile");
		editbtn.setFont(new Font("Arial", Font.BOLD, 12));
		editbtn.setBounds(369, 11, 102, 23);
		contentPane.add(editbtn);
/////////////////////////////////////////////////////////////////////////////////////////////		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int SelectedIndex = list.getSelectedIndex();
			if(SelectedIndex!=-1){	
				if(Ratelist.isEmpty()){
					DefaultListModel listModel = (DefaultListModel) list.getModel();
					listModel.removeAllElements();
					DefaultListModel dlm=new DefaultListModel();
					dlm.addElement("Confirming the rates Are Completed");
					list.setModel(dlm);
				}else{
				message="RateConfirm#username:"+Ratelist.get(SelectedIndex).GetUserName()+
						"*FilmName:"+Ratelist.get(SelectedIndex).GetFilmName()+
						"*RefreeName:"+RefreeName+"*status:Accept*#";
				Ratelist.remove(SelectedIndex);
				output.println(message);
				DefaultListModel listModel = (DefaultListModel) list.getModel();
				listModel.removeAllElements();
				DefaultListModel dlm = new DefaultListModel();
				for (int i = 0; i < Ratelist.size(); i++)
					dlm.addElement(Ratelist.get(i).toString());
				list.setModel(dlm);
				}
			}else
				JOptionPane.showMessageDialog(null, "please select a Rate");
			
			
			
			}
		});
		btnAccept.setFont(new Font("Arial", Font.BOLD, 12));
		btnAccept.setBounds(166, 242, 89, 23);
		contentPane.add(btnAccept);
/////////////////////////////////////////////////////////////////////////////////////////////////////		
		JButton btnReject = new JButton("Reject");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int SelectedIndex = list.getSelectedIndex();
			if(SelectedIndex!=-1){	
				if(Ratelist.isEmpty()){
					DefaultListModel listModel = (DefaultListModel) list.getModel();
					listModel.removeAllElements();
					DefaultListModel dlm=new DefaultListModel();
					dlm.addElement("Confirming the rates Are Completed");
					list.setModel(dlm);
				}else{
				message="RateConfirm#username:"+Ratelist.get(SelectedIndex).GetUserName()+
						"*FilmName:"+Ratelist.get(SelectedIndex).GetFilmName()+
						"*RefreeName:"+RefreeName+"*status:Reject*#";
				Ratelist.remove(SelectedIndex);
				output.println(message);
				DefaultListModel listModel = (DefaultListModel) list.getModel();
				listModel.removeAllElements();
				DefaultListModel dlm = new DefaultListModel();
				for (int i = 0; i < Ratelist.size(); i++)
					dlm.addElement(Ratelist.get(i).toString());
				list.setModel(dlm);
				}
			}else
				JOptionPane.showMessageDialog(null, "please select a Rate");
			
			
			
			}
		});
		btnReject.setFont(new Font("Arial", Font.BOLD, 12));
		btnReject.setBounds(263, 242, 89, 23);
		contentPane.add(btnReject);
///////////////////////////////////////////////////////////////////////////////////////////////////////		
		JButton showbtn = new JButton("show rates");
		showbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message="showrate#refreename:"+refreename+"*#";
				output.println(message);
				try {
					response = (Response) objectIn.readObject();
					String result = response.GetResult();
					if(result.equals("Ready")){
						Ratelist=(ArrayList<Rate>) objectIn.readObject();
						DefaultListModel listModel = (DefaultListModel) list.getModel();
						listModel.removeAllElements();
						DefaultListModel dlm = new DefaultListModel();
						for (int i = 0; i < Ratelist.size(); i++)
							dlm.addElement(Ratelist.get(i).toString());
						list.setModel(dlm);
					}
					else{
						DefaultListModel listModel = (DefaultListModel) list.getModel();
						listModel.removeAllElements();
						DefaultListModel dlm = new DefaultListModel();
						dlm.addElement(response.GetResult());
						list.setModel(dlm);
					}
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		showbtn.setFont(new Font("Arial", Font.BOLD, 12));
		showbtn.setBounds(50, 243, 106, 23);
		contentPane.add(showbtn);
	}
}
