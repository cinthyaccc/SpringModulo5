package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import InterfaceDAO.IClienteDAO;
import conexion.Conexion;
import modelo.Cliente;


public class ClienteDAOImpl implements IClienteDAO {
	private int filasInsertadas;
	private int filasActualizadas;

	@Override
	public List<Cliente> obtenerListaClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		// Obtener la conexión a la base de datos
		Connection conn = Conexion.getConn();

		try {
			// Crear una declaración SQL
			Statement statement = conn.createStatement();

			// Ejecutar la consulta SQL para obtener los clientes
			String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad FROM usuarios";
			ResultSet resultSet = statement.executeQuery(sql);

			// Recorrer los resultados de la consulta
			while (resultSet.next()) {
				// Obtener los valores de cada columna
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
				int run = resultSet.getInt("run");
				String tipo = resultSet.getString("tipo");
				int rut = resultSet.getInt("rut");
				String nombres = resultSet.getString("nombres");
				String apellidos = resultSet.getString("apellidos");
				int telefono = resultSet.getInt("telefono");
				String afp = resultSet.getString("afp");
				String sistemaDeSalud = resultSet.getString("sistemaDeSalud");
				String direccion = resultSet.getString("direccion");
				String comuna = resultSet.getString("comuna");
				int edad = resultSet.getInt("edad");

				// Crear un objeto Cliente y agregarlo a la lista
				Cliente cliente = new Cliente(id, nombre, fechaDeNacimiento, run, tipo, rut,
						nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad);
				clientes.add(cliente);
			}

			// Cerrar el ResultSet y la declaración
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Cerrar la conexión a la base de datos
//		        Conexion.closeConn(conn);
		}

		return clientes;
	}

	@Override
	public void registrarCliente(Cliente cliente) {
		Connection conn = null;
		PreparedStatement statement = null;
		List<Cliente> clientes = obtenerListaClientes();

		try {
			conn = Conexion.getConn();

			// Ejecutar la consulta SQL para obtener los clientes
			String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getTipo());
			statement.setString(3, cliente.getFechaDeNacimiento());
			statement.setInt(4, cliente.getRun());
			statement.setInt(5, cliente.getRut());
			statement.setString(6, cliente.getNombres());
			statement.setString(7, cliente.getApellidos());
			statement.setInt(8, cliente.getTelefono());
			statement.setString(9, cliente.getAfp());
			statement.setString(10, cliente.getSistemaDeSalud());
			statement.setString(11, cliente.getDireccion());
			statement.setString(12, cliente.getComuna());
			statement.setInt(13, cliente.getEdad());

			// Crear un objeto Cliente y agregarlo a la lista
			Cliente cliente1 = new Cliente(0, cliente.getNombre(),
					cliente.getFechaDeNacimiento(), cliente.getRun(), cliente.getTipo(), cliente.getRut(),
					cliente.getNombres(), cliente.getApellidos(), cliente.getTelefono(),
					cliente.getAfp(), cliente.getSistemaDeSalud(), cliente.getDireccion(),
					cliente.getComuna(), cliente.getEdad());
			clientes.add(cliente1);

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
	public void modificarCliente(Cliente cliente) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = Conexion.getConn();

			// Ejecutar la consulta SQL para modificar el administrativo
			String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, fechaNacimiento = ?, run = ?, rut = ?, nombres = ?, apellidos = ?, telefono = ?, afp = ?, sistemaDeSalud = ?, direccion = ?, comuna = ?, edad = ? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getTipo());
			statement.setString(3, cliente.getFechaDeNacimiento());
			statement.setInt(4, cliente.getRun());
			statement.setInt(5, cliente.getRut());
			statement.setString(6, cliente.getNombres());
			statement.setString(7, cliente.getApellidos());
			statement.setInt(8, cliente.getTelefono());
			statement.setString(9, cliente.getAfp());
			statement.setString(10, cliente.getSistemaDeSalud());
			statement.setString(11, cliente.getDireccion());
			statement.setString(12, cliente.getComuna());
			statement.setInt(13, cliente.getEdad());
			statement.setInt(14, cliente.getId()); // Agregar el ID para identificar el administrativo a modificar

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

//		@Override
//		public Administrativo obtenerAdministrativo(int id) {
//			
//		}

	public int getFilasInsertadas() {
		return filasInsertadas;
	}

	public int getFilasActualizadas() {
		return filasActualizadas;
	}

	// void eliminarCliente(Cliente cliente);
}