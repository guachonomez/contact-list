package Contactos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

public class InsertarDatos {
	
	// Attributes
	private final String HOST;
	private final String USER;
	private final String PASSWORD;
	private int Contador;
	
	// Constructor
	public InsertarDatos(String h, String u, String p) {
		HOST = h;
		USER = u;
		PASSWORD = p;
	};
	
	public InsertarDatos() {
		this.HOST = ""; // Url of the Host
		this.USER = ""; // Name of the user
		this.PASSWORD = ""; // Password of the user
	};
	
	
	// Mutates
	public void addData(String n, String p, String e) throws SQLException{
		Connection connect = DriverManager.getConnection(this.HOST, this.USER, this.PASSWORD);
		Statement sentencia = connect.createStatement();
		
		// Counting if the contact already exists
		
		String sqlQuery = "SELECT COUNT(Telefono) FROM NameOfTable where Telefono = '" + p + "';"; // Change 'NameOfTable' for the name of your table
		ResultSet result = sentencia.executeQuery(sqlQuery);
		
		int count  = 1;
		
        if (result.next()) {
            count = result.getInt(1);
        }
                
        if(count > 1) {
        	setContador(1);
    		sentencia.close();
    		connect.close();
        } else {
        	setContador(0);
        	
        	// Change the name of the columns 1,2,3 for the name in your table and change 'NameOfTable' for the name of your table
        	
			sqlQuery = "INSERT INTO NameOfTable (Column1, Column2, Column2) VALUES ('" + n + "', '" + p + "', '" + e + "');";
			sentencia.execute(sqlQuery);
	
			sentencia.close();
			connect.close();
	    }
	}
	
	public StringBuilder search(String n) throws SQLException{
		Connection connect = DriverManager.getConnection(this.HOST, this.USER, this.PASSWORD);
		Statement sentencia = connect.createStatement();
		
		// Searching if the contact is on the list
		
		String sqlQuery = "SELECT COUNT(Nombre) FROM NameOfTable where Nombre LIKE '%" + n + "%';"; // Change 'NameOfTable' for the name of your table
		ResultSet result = sentencia.executeQuery(sqlQuery);
		
		int count  = 0;
		
        System.out.println(count);

        if (result.next()) {
            count = result.getInt(1);
            System.out.println(count);
        }
                
        StringBuilder sb = new StringBuilder();
        
        // Showing the contact if exists
        
        if(count < 1) {
        	setContador(0);
    		sentencia.close();
    		connect.close();
        }else {
        	setContador(1);
    		sqlQuery = "SELECT * FROM Agenda where Nombre LIKE '%" + n + "%';";
    		result = sentencia.executeQuery(sqlQuery);
    	    
    	    List<String> resultados = new ArrayList<>();
    		
    		while (result.next()) {
    		    String name = result.getString(1);
    		    String phone = result.getString(2);
    		    String mail = result.getString(3);

    		    resultados.add("Name: " + name + ", Phone: " + phone + ", Email: " + mail + "\n");
    		    
    		}
    		
            for (String item : resultados) {
                sb.append(item);
            }
    		sentencia.close();
    		connect.close();
        }
        
		return sb;

	};

	public int getContador() {
		return Contador;
	}

	public void setContador(int contador) {
		Contador = contador;
	}
}
