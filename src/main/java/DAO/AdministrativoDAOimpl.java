package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import InterfaceDAO.IAdministrativoDao;
import conexion.Conexion;
import modelo.Administrativo;

public class AdministrativoDAOimpl implements IAdministrativoDao{
	private int filasInsertadas;	
	private int filasActualizadas;
	
	@Override
	public List<Administrativo> obtenerListaAdministrativos() {
		List<Administrativo> administrativos = new ArrayList<Administrativo>();
		
	    // Obtener la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL
	        Statement statement = conn.createStatement();

	        // Ejecutar la consulta SQL para obtener los clientes
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia FROM usuarios";
	        ResultSet resultSet = statement.executeQuery(sql);

	        // Recorrer los resultados de la consulta
	        while (resultSet.next()) {
	            // Obtener los valores de cada columna
	        	int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
	            int run = resultSet.getInt("run");
	            String tipo = resultSet.getString("tipo");
	            String area = resultSet.getString("area");
	            String experienciaPrevia = resultSet.getString("experienciaPrevia");
	            

	            // Crear un objeto Cliente y agregarlo a la lista
	            Administrativo administrativo = new Administrativo(id, nombre, fechaDeNacimiento, run, tipo, area, experienciaPrevia);
	            administrativos.add(administrativo);
	        }

	        // Cerrar el ResultSet y la declaración
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión a la base de datos
//	        Conexion.closeConn(conn);
	    }

	    return administrativos;
	}

	@Override
	public void registrarAdministrativo(Administrativo administrativo) {
		Connection conn = null;
		PreparedStatement statement = null;
		List<Administrativo> administrativos = obtenerListaAdministrativos();
		
	    try {
	    	conn = Conexion.getConn();

	        // Ejecutar la consulta SQL para obtener los clientes
	        String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	        statement = conn.prepareStatement(sql);

	        // Configurar los parámetros de la declaración SQL
	        statement.setString(1, administrativo.getNombre());
	        statement.setString(2, administrativo.getTipo());
	        statement.setString(3, administrativo.getFechaDeNacimiento());
	        statement.setInt(4, administrativo.getRun());
	        statement.setString(5, administrativo.getArea());
	        statement.setString(6, administrativo.getExperienciaPrevia());
            

			// Crear un objeto Cliente y agregarlo a la lista
			Administrativo administrativo1 = new Administrativo(0, administrativo.getNombre(),
					administrativo.getFechaDeNacimiento(), administrativo.getRun(), administrativo.getTipo(),
					administrativo.getArea(), administrativo.getExperienciaPrevia());
			administrativos.add(administrativo1);
			
			filasInsertadas = statement.executeUpdate();
	        // Cerrar el ResultSet y la declaración
			statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión y liberar recursos
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                // Manejo de errores al cerrar la declaración
	            }
	        }
	        if (conn != null) {
	            try {
	            	conn.close();
	            } catch (SQLException e) {
	                // Manejo de errores al cerrar la conexión
	            }
	        }
        }
	}
	
	@Override
	public void modificarAdministrativo(Administrativo administrativo) {
	    Connection conn = null;
	    PreparedStatement statement = null;
	    
	    try {
	        conn = Conexion.getConn();
	        
	        // Ejecutar la consulta SQL para modificar el administrativo
	        String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, fechaNacimiento = ?, run = ?, area = ?, experienciaPrevia = ? WHERE id = ?";
	        statement = conn.prepareStatement(sql);

	        // Configurar los parámetros de la declaración SQL
	        statement.setString(1, administrativo.getNombre());
	        statement.setString(2, administrativo.getTipo());
	        statement.setString(3, administrativo.getFechaDeNacimiento());
	        statement.setInt(4, administrativo.getRun());
	        statement.setString(5, administrativo.getArea());
	        statement.setString(6, administrativo.getExperienciaPrevia());
	        statement.setInt(7, administrativo.getId()); // Agregar el ID para identificar el administrativo a modificar

	        filasActualizadas = statement.executeUpdate();

	        // Resto del código
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión y liberar recursos
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                // Manejo de errores al cerrar la declaración
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                // Manejo de errores al cerrar la conexión
	            }
	        }
	    }
	}
	
//	@Override
//	public Administrativo obtenerAdministrativo(int id) {
//		
//	}
	
	public int getFilasInsertadas() {
        return filasInsertadas;
    }
	public int getFilasActualizadas() {
        return filasActualizadas;
    }
	
	//	void eliminarAdministrativo(Administrativo administrativo);
}