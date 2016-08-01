package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imdb.Film;
import imdb.Rate;
import imdb.Response;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class MangerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nametxtfld;
	private JTextField yeartxtfld;
	private JTextField countrytxtfld;
	private JTextField mintxtfld;
	private JTextField Directortxtfld;
	private Response response;
	private String message;
	private JList list_1;
	private ArrayList<Film> Filmlist;
	private String UserName;
	private ArrayList<Rate> Ratelist;
	public MangerFrame(ObjectInputStream objectIn, PrintWriter output,String UserName) {
		this.UserName=UserName;
		setResizable(false);
		response = new Response();
		Filmlist = new ArrayList<>();
		Ratelist=new ArrayList<Rate>();
		setTitle("IMDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblSearchBy.setBounds(10, 11, 69, 14);
		contentPane.add(lblSearchBy);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblName.setBounds(10, 36, 46, 14);
		contentPane.add(lblName);

		JLabel lblNewLabel = new JLabel("Year:");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblCountry.setBounds(10, 86, 58, 14);
		contentPane.add(lblCountry);

		JLabel lblNewLabel_1 = new JLabel("Genrelist:");
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 111, 58, 14);
		contentPane.add(lblNewLabel_1);

		nametxtfld = new JTextField();
		nametxtfld.setBounds(90, 33, 95, 20);
		contentPane.add(nametxtfld);
		nametxtfld.setColumns(10);

		yeartxtfld = new JTextField();
		yeartxtfld.setBounds(90, 58, 96, 20);
		contentPane.add(yeartxtfld);
		yeartxtfld.setColumns(10);

		countrytxtfld = new JTextField();
		countrytxtfld.setBounds(90, 83, 95, 20);
		contentPane.add(countrytxtfld);
		countrytxtfld.setColumns(10);

		JCheckBox ComedyChBox = new JCheckBox("Comedy");
		ComedyChBox.setBounds(10, 132, 97, 23);
		contentPane.add(ComedyChBox);

		JCheckBox DramaChBox = new JCheckBox("Drama");
		DramaChBox.setBounds(104, 132, 97, 23);
		contentPane.add(DramaChBox);

		JCheckBox ShortChBox = new JCheckBox("Short");
		ShortChBox.setBounds(10, 158, 97, 23);
		contentPane.add(ShortChBox);

		JCheckBox WarChBox = new JCheckBox("War");
		WarChBox.setBounds(104, 158, 97, 23);
		contentPane.add(WarChBox);

		JCheckBox RomanceChBox = new JCheckBox("Romance");
		RomanceChBox.setBounds(10, 184, 97, 23);
		contentPane.add(RomanceChBox);

		JCheckBox DocumentaryChBox = new JCheckBox("Documentary");
		DocumentaryChBox.setBounds(104, 184, 97, 23);
		contentPane.add(DocumentaryChBox);

		JCheckBox FantasyChBox = new JCheckBox("Fantasy");
		FantasyChBox.setBounds(10, 210, 97, 23);
		contentPane.add(FantasyChBox);

		JCheckBox FamilyChBox = new JCheckBox("Family");
		FamilyChBox.setBounds(104, 210, 97, 23);
		contentPane.add(FamilyChBox);

		JLabel lblNewLabel_2 = new JLabel("Duration minute:");
		lblNewLabel_2.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 240, 103, 14);
		contentPane.add(lblNewLabel_2);

		mintxtfld = new JTextField();
		mintxtfld.setBounds(133, 237, 52, 20);
		contentPane.add(mintxtfld);
		mintxtfld.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Director:");
		lblNewLabel_3.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 265, 69, 14);
		contentPane.add(lblNewLabel_3);

		Directortxtfld = new JTextField();
		Directortxtfld.setBounds(99, 262, 86, 20);
		contentPane.add(Directortxtfld);
		Directortxtfld.setColumns(10);
		/////////////////////////////////////////////////////////////////////////////////////////////
		JButton searchbtn = new JButton("Search");
		searchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				message = "search#";
				if (!(nametxtfld.getText().equals("")))
					message += "name:" + nametxtfld.getText() + "*";
				if (!(yeartxtfld.getText().equals("")))
					message += "year:" + yeartxtfld.getText() + "*";
				if (!(countrytxtfld.getText().equals("")))
					message += "country:" + countrytxtfld.getText() + "*";
				System.out.println(ComedyChBox.isSelected());
				if (TheCheckBoxesIsSelected(ComedyChBox, DramaChBox, ShortChBox, WarChBox, RomanceChBox,
						DocumentaryChBox, FantasyChBox, FamilyChBox)) {
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
					message += "*";

				}
				if (!(mintxtfld.getText().equals("")))
					message += "DMinute:" + mintxtfld.getText() + "*";
				if (!(Directortxtfld.getText().equals("")))
					message += "Director:" + Directortxtfld.getText() + "*";
				if (nametxtfld.getText().equals("") && yeartxtfld.getText().equals("")
						&& countrytxtfld.getText().equals("") && !TheCheckBoxesIsSelected(ComedyChBox, DramaChBox,
								ShortChBox, WarChBox, RomanceChBox, DocumentaryChBox, FantasyChBox, FamilyChBox)
						&&mintxtfld.getText().equals("")&&Directortxtfld.getText().equals(""))
					JOptionPane.showMessageDialog(null, "please fill one item");
				else {
					output.println(message + "#");
					try {
						response = (Response) objectIn.readObject();
						String result = response.GetResult();
						if (result.equals("Ready")) {
							Filmlist = (ArrayList<Film>) objectIn.readObject();
							
							DefaultListModel listModel = (DefaultListModel) list_1.getModel();
							listModel.removeAllElements();
							DefaultListModel dlm = new DefaultListModel();
							for (int i = 0; i < Filmlist.size(); i++)
								dlm.addElement(Filmlist.get(i).toString());
							list_1.setModel(dlm);
						} else {
							DefaultListModel listModel = (DefaultListModel) list_1.getModel();
							listModel.removeAllElements();
							DefaultListModel dlm = new DefaultListModel();
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
		searchbtn.setFont(new Font("Arial", Font.BOLD, 12));
		searchbtn.setBounds(20, 293, 165, 23);
		contentPane.add(searchbtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 52, 506, 217);
		contentPane.add(scrollPane);
		
				list_1 = new JList();
				scrollPane.setViewportView(list_1);
				list_1.setFont(new Font("Georgia", Font.PLAIN, 12));
				DefaultListModel dlm=new DefaultListModel();
				dlm.addElement("Results....");
				list_1.setModel(dlm);


		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Name", "Year", "Country", "Duration Minute", "Director" }));
		comboBox.setToolTipText("");
		comboBox.setBounds(278, 280, 86, 20);
		contentPane.add(comboBox);

		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblSortBy.setBounds(216, 284, 58, 14);
		contentPane.add(lblSortBy);
////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnRate = new JButton("Edit Movie");
		btnRate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int SelectedIndex = list_1.getSelectedIndex();
				if(SelectedIndex>=0){
				EditMovie editframe=new EditMovie(output,Filmlist.get(SelectedIndex));
				editframe.setVisible(true);}
				else
					JOptionPane.showMessageDialog(null, "please select a film");
			}
		});
		btnRate.setFont(new Font("Arial", Font.BOLD, 12));
		btnRate.setBounds(489, 280, 95, 23);
		contentPane.add(btnRate);

		JButton btnNewButton = new JButton("Sort");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SortType;
				SortType=comboBox.getSelectedItem().toString();
				if(SortType.equals("Name"))
				Collections.sort(Filmlist, Film.FilmNameComparator);
				else if(SortType.equals("Year"))
					Collections.sort(Filmlist, Film.FilmYearComparator);
				else if(SortType.equals("Country"))
					Collections.sort(Filmlist, Film.FilmCountryComparator);
				else if(SortType.equals("Duration Minute"))
					Collections.sort(Filmlist, Film.FilmDurationMinuteComparator);
				else if(SortType.equals("Director"))
					Collections.sort(Filmlist, Film.FilmDirectorComparator);
				DefaultListModel listModel = (DefaultListModel) list_1.getModel();
				listModel.removeAllElements();
				DefaultListModel dlm = new DefaultListModel();
				for (int i = 0; i < Filmlist.size(); i++)
					dlm.addElement(Filmlist.get(i).toString());
				list_1.setModel(dlm);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBounds(393, 279, 86, 23);
		contentPane.add(btnNewButton);
///////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnNewButton_1 = new JButton("Add New Movie");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				AddMovie addmovie=new AddMovie(objectIn,output);
				addmovie.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1.setBounds(594, 279, 119, 23);
		contentPane.add(btnNewButton_1);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.setFont(new Font("Arial", Font.BOLD, 12));
		btnSignOut.setBounds(637, 6, 89, 23);
		contentPane.add(btnSignOut);
		
		JLabel lblDatabaseType = new JLabel("DataBase Type:");
		lblDatabaseType.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblDatabaseType.setBounds(190, 327, 95, 14);
		contentPane.add(lblDatabaseType);
		
		JComboBox databasecomboBox = new JComboBox();
		databasecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Json", "SQL"}));
		databasecomboBox.setBounds(285, 324, 79, 20);
		contentPane.add(databasecomboBox);
		
		JButton btnSwitchDatabase = new JButton("Switch Database");
		btnSwitchDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String databasetype=databasecomboBox.getSelectedItem().toString();
				output.println("Switch Database#"+databasetype);
			}
		});
		btnSwitchDatabase.setFont(new Font("Arial", Font.BOLD, 12));
		btnSwitchDatabase.setBounds(403, 322, 129, 23);
		contentPane.add(btnSwitchDatabase);
		
		JButton btnAddRefree = new JButton("Add Refree");
		btnAddRefree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRefree AR=new AddRefree(objectIn, output);
				AR.setVisible(true);
			}
		});
		btnAddRefree.setFont(new Font("Arial", Font.BOLD, 12));
		btnAddRefree.setBounds(550, 322, 103, 23);
		contentPane.add(btnAddRefree);
	}

	public boolean TheCheckBoxesIsSelected(JCheckBox ComedyChBox, JCheckBox DramaChBox, JCheckBox ShortChBox,
			JCheckBox WarChBox, JCheckBox RomanceChBox, JCheckBox DocumentaryChBox, JCheckBox FantasyChBox,
			JCheckBox FamilyChBox) {
		boolean result = false;
		if (ComedyChBox.isSelected() || DramaChBox.isSelected() || ShortChBox.isSelected() || WarChBox.isSelected()
				|| RomanceChBox.isSelected() || DocumentaryChBox.isSelected() || FantasyChBox.isSelected()
				|| FamilyChBox.isSelected())
			result = true;
		return result;
	}
}
