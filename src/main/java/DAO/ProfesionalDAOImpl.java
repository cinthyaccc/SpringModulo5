package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import InterfaceDAO.IProfesionalDao;
import conexion.Conexion;
import modelo.Profesional;

public class ProfesionalDAOImpl implements IProfesionalDao{
	private int filasInsertadas;
	private int filasActualizadas;

	@Override
	public List<Profesional> obtenerListaProfesionales() {
		List<Profesional> profesionales = new ArrayList<Profesional>();

		// Obtener la conexión a la base de datos
		Connection conn = Conexion.getConn();

		try {
			// Crear una declaración SQL
			Statement statement = conn.createStatement();

			// Ejecutar la consulta SQL para obtener los clientes
			String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, titulo, fechaDeIngreso FROM usuarios";
			ResultSet resultSet = statement.executeQuery(sql);

			// Recorrer los resultados de la consulta
			while (resultSet.next()) {
				// Obtener los valores de cada columna
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
				int run = resultSet.getInt("run");
				String tipo = resultSet.getString("tipo");
				String titulo = resultSet.getString("titulo");
				String fechaDeIngreso = resultSet.getString("fechaDeIngreso");

				// Crear un objeto Cliente y agregarlo a la lista
				Profesional profesional = new Profesional(id, nombre, fechaDeNacimiento, run, tipo, titulo,
						fechaDeIngreso);
				profesionales.add(profesional);
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

		return profesionales;
	}

	@Override
	public void registrarProfesional(Profesional profesional) {
		Connection conn = null;
		PreparedStatement statement = null;
		List<Profesional> profesionales = obtenerListaProfesionales();

		try {
			conn = Conexion.getConn();

			// Ejecutar la consulta SQL para obtener los clientes
			String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, titulo, fechaDeIngreso) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, profesional.getNombre());
			statement.setString(2, profesional.getTipo());
			statement.setString(3, profesional.getFechaDeNacimiento());
			statement.setInt(4, profesional.getRun());
			statement.setString(5, profesional.getTitulo());
			statement.setString(6, profesional.getFechaDeIngreso());

			// Crear un objeto Cliente y agregarlo a la lista
			Profesional profesional1 = new Profesional(0, profesional.getNombre(),
					profesional.getFechaDeNacimiento(), profesional.getRun(), profesional.getTipo(),
					profesional.getTitulo(), profesional.getFechaDeIngreso());
			profesionales.add(profesional1);

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
	public void modificarProfesional(Profesional profesional) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = Conexion.getConn();

			// Ejecutar la consulta SQL para modificar el administrativo
			String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, fechaNacimiento = ?, run = ?, titulo = ?, fechaDeIngreso = ? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, profesional.getNombre());
			statement.setString(2, profesional.getTipo());
			statement.setString(3, profesional.getFechaDeNacimiento());
			statement.setInt(4, profesional.getRun());
			statement.setString(5, profesional.getTitulo());
			statement.setString(6, profesional.getFechaDeIngreso());
			statement.setInt(7, profesional.getId()); // Agregar el ID para identificar el administrativo a modificar

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
//	public Profesional obtenerProfesional(int id) {
//		
//	}

	public int getFilasInsertadas() {
		return filasInsertadas;
	}

	public int getFilasActualizadas() {
		return filasActualizadas;
	}

	// void eliminarProfesional(Profesional profesional);
}