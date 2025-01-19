package Contactos;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.sql.*;


public class AgendaVisual {

	private JFrame frame;
	private JTextField textNameInstert;
	private JTextField textField_name;
	private JTextField textField_tlf;
	private JTextField textField_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendaVisual window = new AgendaVisual();
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
	public AgendaVisual() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 685, 669);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CONTACT LIST");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(175, 11, 318, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnConsultar = new JButton("Search");
		btnConsultar.setBounds(277, 352, 114, 42);
		frame.getContentPane().add(btnConsultar);
		
		JButton btnAnadir = new JButton("Add");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnadir.setBounds(277, 564, 114, 42);
		frame.getContentPane().add(btnAnadir);
		
		textNameInstert = new JTextField();
		textNameInstert.setBounds(56, 98, 543, 20);
		frame.getContentPane().add(textNameInstert);
		textNameInstert.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name of the person to consult:");
		lblNewLabel_1.setBounds(56, 73, 351, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_name = new JTextField();
		textField_name.setToolTipText("");
		textField_name.setColumns(10);
		textField_name.setBounds(58, 443, 541, 20);
		frame.getContentPane().add(textField_name);
		
		textField_tlf = new JTextField();
		textField_tlf.setColumns(10);
		textField_tlf.setBounds(58, 489, 541, 20);
		frame.getContentPane().add(textField_tlf);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(58, 533, 541, 20);
		frame.getContentPane().add(textField_email);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name of the person to add:");
		lblNewLabel_1_1.setBounds(58, 424, 351, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Phone number of the person to add:");
		lblNewLabel_1_1_1.setBounds(56, 469, 351, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email of the person to add:");
		lblNewLabel_1_1_1_1.setBounds(59, 515, 351, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JTextArea textReturn = new JTextArea();
		textReturn.setLineWrap(true);
		textReturn.setEditable(false);
		textReturn.setWrapStyleWord(true);
		textReturn.setBounds(56, 129, 543, 209);
		frame.getContentPane().add(textReturn);
		
		InsertarDatos agenda = new InsertarDatos();
		
		// Acción al clicar en el botón de Añadir

		btnAnadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String name = textField_name.getText();
				String phone = textField_tlf.getText();
				String email = textField_email.getText();
				if (phone.length() > 9) {
					textReturn.setText("The phone number is too long.");
				} else if (!email.contains("@") && !email.contains(".")) {
					textReturn.setText("Enter a valid email.");
				} else {
					try {
						agenda.addData(name, phone, email);
						if(agenda.getContador() == 1) {
							textReturn.setText("This contact already exists.");
						} else {
							textReturn.setText("We have added the contact successfully.");
	
						}
						textField_name.setText("");
						textField_tlf.setText("");
						textField_email.setText("");
	
					} catch (SQLException e1) {
						e1.printStackTrace();
						textReturn.setText("There was an error, please try again later.");
					}
	            }
			}
		});
		
		// Click on the button "Search"
		
		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String name = textNameInstert.getText();				
			try {
				StringBuilder resultado = agenda.search(name);
				if(agenda.getContador() == 0) {
					textReturn.setText("No matching name found.");
					textNameInstert.setText("");
				} else {
					textReturn.setText(resultado.toString());
					textNameInstert.setText("");
				}

				} catch (SQLException e1) {
					e1.printStackTrace();
					textReturn.setText("There was an error, please try again later.");
				}
			}
		});
	}
}
