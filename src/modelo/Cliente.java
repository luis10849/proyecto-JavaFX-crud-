package modelo;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Cliente {
	private IntegerProperty cod;
	private StringProperty nombre;
	private IntegerProperty edad;
	private StringProperty genero;
	private Date fechaGraduacion;
	private StringProperty pais;

	public Cliente(int cod, String nombre, int edad, String genero, Date fechaGraduacion, String pais) {

		this.cod = new SimpleIntegerProperty(cod);
		this.nombre = new SimpleStringProperty(nombre);
		this.edad = new SimpleIntegerProperty(edad);
		this.genero = new SimpleStringProperty(genero);
		this.fechaGraduacion = fechaGraduacion;
		this.pais = new SimpleStringProperty(pais);
	}

	// Metodos atributo: cod
	public int getCod() {
		return cod.get();
	}

	public void setCod(int cod) {
		this.cod = new SimpleIntegerProperty(cod);
	}

	public IntegerProperty CodProperty() {
		return cod;
	}

	// Metodos atributo: nombre
	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}

	public StringProperty NombreProperty() {
		return nombre;
	}

	// Metodos atributo: edad
	public int getEdad() {
		return edad.get();
	}

	public void setEdad(int edad) {
		this.edad = new SimpleIntegerProperty(edad);
	}

	public IntegerProperty EdadProperty() {
		return edad;
	}

	// Metodos atributo: genero
	public String getGenero() {
		return genero.get();
	}

	public void setGenero(String genero) {
		this.genero = new SimpleStringProperty(genero);
	}

	public StringProperty GeneroProperty() {
		return genero;
	}

	// Metodos atributo: fechaGraduacion
	public Date getFechaGraduacion() {
		return fechaGraduacion;
	}

	public void setFechaGraduacion(Date fechaGraduacion) {
		this.fechaGraduacion = fechaGraduacion;
	}

	// Metodos atributo: pais
	public String getPais() {
		return pais.get();
	}

	

	public void setPais(String pais) {
		this.pais = new SimpleStringProperty(pais);
	}

	public StringProperty PaisProperty() {
		return pais;
	}

	public int guardarRegistro(Connection con) {

		
		
		try {
			PreparedStatement ps=con.prepareStatement("INSERT INTO cliente(Nombre,Edad,Genero,FechaGrado,Pais)values(?,?,?,?,?)");
			ps.setString(1,nombre.get());
			ps.setInt(2, edad.get());
			ps.setString(3,genero.get());
			ps.setDate(4,fechaGraduacion);
			ps.setString(5,pais.get());
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	public int actualizarRegistro(Connection con) {
	
		try {
			PreparedStatement ps=con.prepareStatement("update cliente set Nombre=?,Edad=?,Genero=?,FechaGrado=?,Pais=? where Id=?");
			ps.setString(1,nombre.get());
			ps.setInt(2, edad.get());
			ps.setString(3,genero.get());
			ps.setDate(4,fechaGraduacion);
			ps.setString(5,pais.get());
			ps.setInt(6,cod.get());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}

	}

	public int eliminarRegistro(Connection con) {
		
		try {
			PreparedStatement ps=con.prepareStatement("delete from cliente where Id=?");
			
			ps.setInt(1,cod.get());
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
			
		}

	}
	public static void llenarInformacionClientes(Connection con,ObservableList<Cliente>lista) {
		
		try {
			Statement ps= con.createStatement();
			ResultSet rs=ps.executeQuery("select * from cliente");
			while (rs.next()) {
			    lista.add(
			    		new Cliente(
			    		rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getDate(5),rs.getString(6)
			    		)	
			    );
			}
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
//	 public List listar() {
//
//	        String sql = "select * from cliente";
//	        List<Cliente> lista = new ArrayList<>();
//	        try {
//	            con = cn.Conexion();
//	            ps = con.prepareStatement(sql);
//	            rs = ps.executeQuery();
//	            while (rs.next()) {
//	                Cliente em = new Cliente();
//	                em.setId(rs.getInt(1));
//	                em.setDocumento(rs.getString(2));
//	                em.setDni(rs.getString(3));
//	                em.setNom(rs.getString(4));
//	                em.setDir(rs.getString(5));
//	                em.setTelres(rs.getInt(6));
//	                em.setTelcel(rs.getInt(7));
//	                em.setCiudad(rs.getString(8));
//	                em.setDepartamento(rs.getString(9));
//	                em.setPais(rs.getString(10));
//	                em.setProfesion(rs.getString(11));
//	                em.setEmail(rs.getString(12));
//	                lista.add(em);
//
//	            }
//	        } catch (Exception e) {
//	        }
//	        return lista;
//	    }
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}