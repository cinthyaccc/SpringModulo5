package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import InterfaceDAO.ICapacitacionDao;
import conexion.Conexion;
import modelo.DatosCapacitacion;

public class CapacitacionDAOImpl implements ICapacitacionDao {
	
	@Override
	public List<DatosCapacitacion> obtenerListaCapacitaciones() {
		List<DatosCapacitacion> capacitaciones = new ArrayList<DatosCapacitacion>();
		
	    // Obtener la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL
	        Statement statement = conn.createStatement();

	        // Ejecutar la consulta SQL para obtener los clientes
	        String sql = "SELECT id, rut, dia, hora, lugar, duracion, cantidad, nombre, detalle FROM capacitaciones";
	        ResultSet resultSet = statement.executeQuery(sql);

	        // Recorrer los resultados de la consulta
	        while (resultSet.next()) {
	            // Obtener los valores de cada columna
	        	
	            int id = resultSet.getInt("id");
	          
	            int rut = resultSet.getInt("rut");
	            String dia = resultSet.getString("dia");
	            String hora = resultSet.getString("hora");
	            String lugar = resultSet.getString("lugar");
	            String duracion = resultSet.getString("duracion");
	            int cantidad = resultSet.getInt("cantidad");
	            String nombreC = resultSet.getString("nombre");
	        	String detalle = resultSet.getString("detalle");
	            // Crear un objeto Capacitacion y agregarlo a la lista
	            DatosCapacitacion capacitacion = new DatosCapacitacion(id, rut, dia, hora, lugar, duracion, cantidad, nombreC, detalle);
	            capacitaciones.add(capacitacion);
	            System.out.println(capacitaciones);
	           
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

	    return capacitaciones;
	}

	@Override
	public void registrarCapacitacion(DatosCapacitacion capacitacion) {
		// TODO Auto-generated method stub
		List<DatosCapacitacion> capacitaciones = obtenerListaCapacitaciones();
		capacitaciones.add(capacitacion);
	}
	
	@Override
	public void modificarCapacitacion(int id) {
		//FALTA TERMINARLO
	}
	
	//	void eliminarCapacitacion(DatosCapacitacion capacitacion);

}
