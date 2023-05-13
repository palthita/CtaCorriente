package com.platinum.CtaCorriente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;


import com.platinum.CtaCorriente.acceso;


import Menu.Menu;
import Menu.persona;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
public class acceso {
	public static int id_usuario;
	 public static int nombre_ejecutivo;
	 public static int rut_ejecutivo;
	 public static int departamento;
	private JFrame acc;
	private JTextField usu;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					acceso window = new acceso();
					window.acc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @return 
	 */
	
	public String setnombre_ejecutivo(String nombre_ejecutivo) {
		JOptionPane.showMessageDialog(null, "Usuario no tiene menus activos, contactar al administrador"); 
		return nombre_ejecutivo;
	}
	
	public acceso() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		acc = new JFrame();
		acc.getContentPane().setForeground(new Color(255, 255, 255));
		acc.setTitle("Sistema gestión");
		acc.getContentPane().setBackground(new Color(119, 136, 153));
		acc.setBounds(100, 100, 800, 500);
		acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		acc.getContentPane().setLayout(null);
		
		JLabel usuario = new JLabel("Usuario");
		usuario.setForeground(new Color(255, 255, 255));
		usuario.setFont(new Font("Arial", Font.BOLD, 16));
		usuario.setBounds(207, 142, 146, 13);
		acc.getContentPane().add(usuario);
		
		final JButton ingresar = new JButton("Ingresar");
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				  String us = (usu.getText());  
			      		     
			      String pas = pass.getText();
			      
			        try {
			        	
			          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/banco","root" ,"");
			          Statement comando=conexion.createStatement();
			           
			          ResultSet registro = comando.executeQuery("SELECT id FROM usuario WHERE nombre_usuario='"+us+"' AND password='"+pas+"'");
			          if (registro.next()==true) {			        	  
			        	  id_usuario = registro.getInt("id");
			        	  ResultSet ejecutivo = comando.executeQuery("SELECT * FROM ejecutivo WHERE id_usuario='"+id_usuario+"' ");			        	  
			        	 if (ejecutivo.next() == true) {
			        		 String nombre_ejecutivo,departamento,rut_ejecutivo;
			        		 
				        	    acc.setVisible(false);
				        	    Menu m = new Menu();
				        	    m.setVisible(true);
				        	   
				        	    
			        	 }else {
			        		 
			        		 ResultSet personas = comando.executeQuery("SELECT * FROM persona WHERE id_usuario='"+id_usuario+"' ");	
			        		 if (personas.next() == true) {
			        			 persona p = new persona();
			        			 p.setVisible(true);
			        		 }else {
			        			 JOptionPane.showMessageDialog(null, "Usuario no tiene menus activos, contactar al administrador"); 
			        		 }
			        	 }
			        	  		                 
			             
			             
			          } else {
			        	  JOptionPane.showMessageDialog(null, "Usuario o password invalido, intente nuevasmente");
			        	  us = "";
			        	  
			        	  
			          }
			          conexion.close();
			        } catch(SQLException ex){
			          
			        }
			
			}
		});
		ingresar.setBounds(483, 339, 85, 21);
		acc.getContentPane().add(ingresar);
		
		JLabel lblNewLabel = new JLabel("Contraseña");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(207, 224, 188, 13);
		acc.getContentPane().add(lblNewLabel);
		
		usu = new JTextField();
		usu.setBounds(349, 140, 188, 19);
		acc.getContentPane().add(usu);
		usu.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(349, 222, 188, 19);
		acc.getContentPane().add(pass);
		
		JLabel lblNewLabel_1 = new JLabel("Banco Platinum");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 25, 766, 26);
		acc.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bienvenido ingrese sus credenciales para ingresar");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 55, 766, 26);
		acc.getContentPane().add(lblNewLabel_1_1);
	}

	
}
