package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Film;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditMovie extends JFrame {

	private JPanel contentPane;
	private JTextField nametxtfld;
	private JTextField yeartxtfld;
	private JTextField countrytxtfld;
	private JTextField mintxtfld;
	private JTextField Directortxtfld;
	public EditMovie(PrintWriter output,Film film) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFilmName = new JLabel("Film Name:");
		lblFilmName.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblFilmName.setBounds(20, 37, 76, 14);
		contentPane.add(lblFilmName);
		
		nametxtfld = new JTextField();
		nametxtfld.setBounds(119, 34, 199, 20);
		contentPane.add(nametxtfld);
		nametxtfld.setColumns(10);
		nametxtfld.setText(film.getname());
		
		JLabel lblYear = new JLabel("year:");
		lblYear.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblYear.setBounds(20, 72, 46, 14);
		contentPane.add(lblYear);
		
		yeartxtfld = new JTextField();
		yeartxtfld.setBounds(119, 69, 103, 20);
		contentPane.add(yeartxtfld);
		yeartxtfld.setColumns(10);
		yeartxtfld.setText(Integer.toString(film.getyear()));
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblCountry.setBounds(20, 107, 63, 14);
		contentPane.add(lblCountry);
		
		countrytxtfld = new JTextField();
		countrytxtfld.setBounds(119, 104, 103, 20);
		contentPane.add(countrytxtfld);
		countrytxtfld.setColumns(10);
		countrytxtfld.setText(film.getcountry());
		
		JLabel label = new JLabel("Genrelist:");
		label.setFont(new Font("Georgia", Font.PLAIN, 12));
		label.setBounds(20, 135, 58, 14);
		contentPane.add(label);
		
		ArrayList<String> geners =(ArrayList<String>) film.getFilmGeners();
		
		JCheckBox ComedyChBox = new JCheckBox("Comedy");
		ComedyChBox.setBounds(20, 156, 97, 23);
		contentPane.add(ComedyChBox);
		
		JCheckBox DramaChBox = new JCheckBox("Drama");
		DramaChBox.setBounds(114, 156, 97, 23);
		contentPane.add(DramaChBox);
		
		JCheckBox WarChBox = new JCheckBox("War");
		WarChBox.setBounds(114, 182, 97, 23);
		contentPane.add(WarChBox);
		
		JCheckBox DocumentaryChBox = new JCheckBox("Documentary");
		DocumentaryChBox.setBounds(114, 208, 97, 23);
		contentPane.add(DocumentaryChBox);
		
		JCheckBox ShortChBox = new JCheckBox("Short");
		ShortChBox.setBounds(20, 182, 97, 23);
		contentPane.add(ShortChBox);
		
		JCheckBox RomanceChBox = new JCheckBox("Romance");
		RomanceChBox.setBounds(20, 208, 97, 23);
		contentPane.add(RomanceChBox);
		
		JCheckBox FantasyChBox = new JCheckBox("Fantasy");
		FantasyChBox.setBounds(20, 234, 97, 23);
		contentPane.add(FantasyChBox);
		
		JCheckBox FamilyChBox = new JCheckBox("Family");
		FamilyChBox.setBounds(114, 234, 97, 23);
		contentPane.add(FamilyChBox);
		
		JCheckBox CrimecheckBox = new JCheckBox("Crime");
		CrimecheckBox.setBounds(213, 156, 97, 23);
		contentPane.add(CrimecheckBox);
		
		JCheckBox HorrorcheckBox = new JCheckBox("Horror");
		HorrorcheckBox.setBounds(213, 182, 97, 23);
		contentPane.add(HorrorcheckBox);
		
		JCheckBox chckbxAnimation = new JCheckBox("Animation");
		chckbxAnimation.setBounds(213, 208, 97, 23);
		contentPane.add(chckbxAnimation);
		
		JCheckBox chckbxAdventure = new JCheckBox("Adventure");
		chckbxAdventure.setBounds(213, 234, 97, 23);
		contentPane.add(chckbxAdventure);
		
		for(int i=0;i<geners.size();i++)
			if(geners.get(i).equals("Comedy"))
				ComedyChBox.setSelected(true);
			else if(geners.get(i).equals("Drama"))
				DramaChBox.setSelected(true);
			else if(geners.get(i).equals("War"))
				WarChBox.setSelected(true);
			else if(geners.get(i).equals("Documentary"))
				DocumentaryChBox.setSelected(true);
			else if(geners.get(i).equals("Short"))
				ShortChBox.setSelected(true);
			else if(geners.get(i).equals("Romance"))
				RomanceChBox.setSelected(true);
			else if(geners.get(i).equals("Fantasy"))
				FantasyChBox.setSelected(true);
			else if(geners.get(i).equals("Family"))
				FamilyChBox.setSelected(true);
			else if(geners.get(i).equals("Crime"))
				CrimecheckBox.setSelected(true);
			else if(geners.get(i).equals("Horror"))
				HorrorcheckBox.setSelected(true);
			else if(geners.get(i).equals("Animation"))
				chckbxAnimation.setSelected(true);
			else if(geners.get(i).equals("Adventure"))
				chckbxAdventure.setSelected(true);
		
		
		JLabel label_1 = new JLabel("Duration minute:");
		label_1.setFont(new Font("Georgia", Font.PLAIN, 12));
		label_1.setBounds(20, 273, 103, 14);
		contentPane.add(label_1);
		
		mintxtfld = new JTextField();
		mintxtfld.setColumns(10);
		mintxtfld.setBounds(143, 270, 79, 20);
		contentPane.add(mintxtfld);
		mintxtfld.setText(Integer.toString(film.getdurationminutes()));
		
		Directortxtfld = new JTextField();
		Directortxtfld.setColumns(10);
		Directortxtfld.setBounds(142, 308, 113, 20);
		contentPane.add(Directortxtfld);
		Directortxtfld.setText(film.getdirector());
		
		JLabel label_2 = new JLabel("Director:");
		label_2.setFont(new Font("Georgia", Font.PLAIN, 12));
		label_2.setBounds(20, 311, 69, 14);
		contentPane.add(label_2);
		
		JLabel lblDiscription = new JLabel("Discription:");
		lblDiscription.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblDiscription.setBounds(20, 336, 76, 14);
		contentPane.add(lblDiscription);
		
		JTextArea DiscriptiontextArea = new JTextArea();
		DiscriptiontextArea.setLineWrap(true);
		DiscriptiontextArea.setBounds(135, 344, 250, 166);
		contentPane.add(DiscriptiontextArea);
		DiscriptiontextArea.setText(film.getdescription());
/////////////////////////////////////////////////////////////////////////////////////////////////////		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String message = "editmovie#identifiername:"+film.getname()+"*";
					message += "name:" + nametxtfld.getText() + "*";
					message += "year:" + yeartxtfld.getText() + "*";
					message += "country:" + countrytxtfld.getText() + "*";
					message += "gener:";
					if (ComedyChBox.isSelected())
						message += "Comedy|";
					if (DramaChBox.isSelected())
						message += "Drama|";
					if (ShortChBox.isSelected())
						message += "Short|";
					if (WarChBox.isSelected())
						message += "War|";
					if (RomanceChBox.isSelected())
						message += "Romance|";
					if (DocumentaryChBox.isSelected())
						message += "Documentary|";
					if (FantasyChBox.isSelected())
						message += "Fantasy|";
					if (FamilyChBox.isSelected())
						message += "Family|";
					if (CrimecheckBox.isSelected())
						message += "Crime|";
					if (HorrorcheckBox.isSelected())
						message += "Horror|";
					if (chckbxAnimation.isSelected())
						message += "Animation|";
					if (chckbxAdventure.isSelected())
						message += "Adventure|";
					message += "*";
					message += "DMinute:" + mintxtfld.getText() + "*";
					message += "Director:" + Directortxtfld.getText() + "*";
					message += "description:" + DiscriptiontextArea.getText() + "*";
					output.println(message + "#");
					dispose();
			}
		});
		btnSaveChanges.setFont(new Font("Arial", Font.BOLD, 12));
		btnSaveChanges.setBounds(166, 532, 139, 23);
		contentPane.add(btnSaveChanges);
		
	
	}
}
