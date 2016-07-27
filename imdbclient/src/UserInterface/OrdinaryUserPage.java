package UserInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class OrdinaryUserPage extends JFrame {

	private JPanel contentPane;
	private JTextField nametxtfld;
	private JTextField yeartxtfld;
	private JTextField countrytxtfld;
	private JTextField mintxtfld;
	private JTextField Directortxtfld;
	public OrdinaryUserPage() {
		setTitle("IMDB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 367);
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Comedy");
		chckbxNewCheckBox.setBounds(10, 132, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Drama");
		chckbxNewCheckBox_1.setBounds(104, 132, 97, 23);
		contentPane.add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxShort = new JCheckBox("Short");
		chckbxShort.setBounds(10, 158, 97, 23);
		contentPane.add(chckbxShort);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("War");
		chckbxNewCheckBox_2.setBounds(104, 158, 97, 23);
		contentPane.add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxRomance = new JCheckBox("Romance");
		chckbxRomance.setBounds(10, 184, 97, 23);
		contentPane.add(chckbxRomance);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Documentary");
		chckbxNewCheckBox_3.setBounds(104, 184, 97, 23);
		contentPane.add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxFantasy = new JCheckBox("Fantasy");
		chckbxFantasy.setBounds(10, 210, 97, 23);
		contentPane.add(chckbxFantasy);
		
		JCheckBox chckbxFamily = new JCheckBox("Family");
		chckbxFamily.setBounds(104, 210, 97, 23);
		contentPane.add(chckbxFamily);
		
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
		
		JButton searchbtn = new JButton("Search");
		searchbtn.setFont(new Font("Arial", Font.BOLD, 12));
		searchbtn.setBounds(20, 293, 165, 23);
		contentPane.add(searchbtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(216, 36, 434, 217);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "Year", "Country", "Duration Minute", "Director"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(272, 263, 86, 20);
		contentPane.add(comboBox);
		
		JLabel lblSortBy = new JLabel("Sort By:");
		lblSortBy.setFont(new Font("Georgia", Font.PLAIN, 12));
		lblSortBy.setBounds(216, 265, 58, 14);
		contentPane.add(lblSortBy);
		
		JButton btnRate = new JButton("Rate");
		btnRate.setFont(new Font("Arial", Font.BOLD, 12));
		btnRate.setBounds(464, 262, 86, 23);
		contentPane.add(btnRate);
		
		JButton btnNewButton = new JButton("Sort");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBounds(368, 262, 86, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reviews");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton_1.setBounds(560, 262, 81, 23);
		contentPane.add(btnNewButton_1);
	}
}
