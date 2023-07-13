package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Administrativo;
import modelo.Cliente;
import modelo.Profesional;
import modelo.Usuario;

/**
 * Servlet implementation class EditarUsuarioServlet
 */
@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
	private static final String DB_USER = "usuario";
	private static final String DB_PASSWORD = "contraseña";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarUsuarioServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");

		if (tipo != null) {
			switch (tipo) {
				case "Cliente":
					editarCliente(request, response);
					break;
				case "Administrativo":
					editarAdministrativo(request, response);
					break;
				case "Profesional":
					editarProfesional(request, response);
					break;
				default:
					response.sendRedirect("ListadoUsuariosServlet");
					break;
			}
		} else {
			response.sendRedirect("ListadoUsuariosServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void editarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String fechaNacimiento = request.getParameter("fechaDeNacimiento");
		int run = Integer.parseInt(request.getParameter("run"));
		int rut = Integer.parseInt(request.getParameter("rut"));
		String nombres = request.getParameter("nombres");
		String apellidos = request.getParameter("apellidos");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String afp = request.getParameter("afp");
		String sistemaSalud = request.getParameter("sistemaDeSalud");
		String direccion = request.getParameter("direccion");
		String comuna = request.getParameter("comuna");
		int edad = Integer.parseInt(request.getParameter("edad"));

		Cliente cliente = new Cliente(run, rut, nombres, apellidos, telefono, afp, sistemaSalud, direccion, comuna, edad);
		cliente.setId(id);
		cliente.setNombre(nombre);
		cliente.setFechaDeNacimiento(fechaNacimiento);
		cliente.setRun(run);

		// Guardar los datos editados en la base de datos
		actualizarClienteEnBaseDeDatos(cliente);

		// Mostrar mensaje de éxito con SweetAlert
		String mensaje = "El cliente se editó correctamente";
		mostrarMensajeExitoso(request, response, mensaje);
	}

	private void editarAdministrativo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String fechaNacimiento = request.getParameter("fechaDeNacimiento");
		int run = Integer.parseInt(request.getParameter("run"));
		String area = request.getParameter("area");
		String experienciaPrevia = request.getParameter("experienciaPrevia");

		Administrativo administrativo = new Administrativo(nombre, area, experienciaPrevia);
		administrativo.setId(id);
		administrativo.setFechaDeNacimiento(fechaNacimiento);
		administrativo.setRun(run);

		// Guardar los datos editados en la base de datos
		actualizarAdministrativoEnBaseDeDatos(administrativo);

		// Mostrar mensaje de éxito con SweetAlert
		String mensaje = "El administrativo se editó correctamente";
		mostrarMensajeExitoso(request, response, mensaje);
	}

	private void editarProfesional(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String fechaNacimiento = request.getParameter("fechaDeNacimiento");
		int run = Integer.parseInt(request.getParameter("run"));
		String titulo = request.getParameter("titulo");
		String fechaIngreso = request.getParameter("fechaDeIngreso");

		Profesional profesional = new Profesional(titulo, fechaIngreso);
		profesional.setId(id);
		profesional.setNombre(nombre);
		profesional.setFechaDeNacimiento(fechaNacimiento);
		profesional.setRun(run);

		// Guardar los datos editados en la base de datos
		actualizarProfesionalEnBaseDeDatos(profesional);

		// Mostrar mensaje de éxito con SweetAlert
		String mensaje = "El profesional se editó correctamente";
		mostrarMensajeExitoso(request, response, mensaje);
	}

	private void mostrarMensajeExitoso(HttpServletRequest request, HttpServletResponse response, String mensaje)
			throws ServletException, IOException {
		List<Usuario> usuarios = obtenerUsuariosDesdeBaseDeDatos();

		request.setAttribute("u", usuarios);
		request.setAttribute("mensaje", mensaje);
		request.getRequestDispatcher("/views/listado_usuarios.jsp").forward(request, response);
	}

	private void actualizarClienteEnBaseDeDatos(Cliente cliente) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String query = "UPDATE usuarios SET nombre=?, tipo=?, fechaNacimiento=?, run=?, nombres=?, apellidos=?, telefono=?, afp=?, sistemaDeSalud=?, direccion=?, comuna=?, edad=? WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, "Cliente");
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
			statement.setInt(14, cliente.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void actualizarAdministrativoEnBaseDeDatos(Administrativo administrativo) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String query = "UPDATE usuarios SET nombre=?, tipo=?, fechaNacimiento=?, run=?, area=?, experienciaPrevia=? WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, administrativo.getNombre());
			statement.setString(2, "Administrativo");
			statement.setString(3, administrativo.getFechaDeNacimiento());
			statement.setInt(4, administrativo.getRun());
			statement.setString(5, administrativo.getArea());
			statement.setString(6, administrativo.getExperienciaPrevia());
			statement.setInt(7, administrativo.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void actualizarProfesionalEnBaseDeDatos(Profesional profesional) {
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String query = "UPDATE usuarios SET nombre=?, tipo=?, fechaNacimiento=?, run=?, titulo=?, fechaDeIngreso=? WHERE id=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, profesional.getNombre());
			statement.setString(2, "Profesional");
			statement.setString(3, profesional.getFechaDeNacimiento());
			statement.setInt(4, profesional.getRun());
			statement.setString(5, profesional.getTitulo());
			statement.setString(6, profesional.getFechaDeIngreso());
			statement.setInt(7, profesional.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<Usuario> obtenerUsuariosDesdeBaseDeDatos() {
		List<Usuario> usuarios = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			String query = "SELECT * FROM usuarios";
			PreparedStatement statement = conn.prepareStatement(query);
			// ...
			// Obtener los datos de la base de datos y crear los objetos Usuario correspondientes
			// ...
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}
}
