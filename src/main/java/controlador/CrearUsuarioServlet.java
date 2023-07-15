package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DAO.AdministrativoDAOimpl;
import DAO.ClienteDAOImpl;
import DAO.ProfesionalDAOImpl;
import conexion.Conexion;
import modelo.Administrativo;
import modelo.Cliente;
import modelo.Profesional;
import modelo.Usuario;

/**
 * 
 * @author Grupo 5: Sabina Leal, Juan Barrientos, Manuel Chavez, Sebastian
 *         Fernandez, Cinthya Caldera.
 *
 */
/**
 * Servlet implementation class CrearCapacitacionServlet
 */
@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {
	private AdministrativoDAOimpl administrativoDAO;
	private ClienteDAOImpl clienteDAO;
	private ProfesionalDAOImpl profesionalDAO;

	public void init() {
		// Inicializar el objeto AdministrativoDAO
		administrativoDAO = new AdministrativoDAOimpl();
		clienteDAO = new ClienteDAOImpl();
		profesionalDAO = new ProfesionalDAOImpl();
	}

	private static final long serialVersionUID = 1L;
	int contador;

	// Crear y llenar la lista de usuarios
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// Verificacion del login
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int contador = Contador.getContador();
		HttpSession session = request.getSession();
		String nombre = (String) session.getAttribute("nombre");
		String password = (String) session.getAttribute("password");
		if (nombre == null || password == null || !validar(nombre, password)) {
			if (contador > 0) {

				String mensaje = "clave incorrecta";
				request.setAttribute("mensaje", mensaje);

			}

			Contador.setContador(1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);

		} else {
			Contador.setContador(1);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("nombre", nombre);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/crearUsuario.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Se inicializan algunas variables obtenidas a traves del formulario mediante
		// metodo getparameter.
		String nombre = request.getParameter("nombre");
		Usuario nombreU = new Usuario();
		if (!nombreU.setNombre(nombre)) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
			out.close();
			return;
		}

		String tipo = request.getParameter("tipo");
		Usuario tipoU = new Usuario();

		if (tipo.isEmpty()) {
			PrintWriter out = response.getWriter();
			out.println(
					"<script>alert('Debe seleccionar un tipo');window.location.href='CrearCapacitacionServlet';</script>");
			out.close();
			return;
		}

		String run = request.getParameter("run");

		int runU;

		try {
			runU = Integer.parseInt(run);
		} catch (NumberFormatException e) {
			// Manejar el error si no se puede convertir a entero
			PrintWriter out = response.getWriter();
			out.println(
					"<script>alert('Error en el campo Rut, debe ser un valor númerico');window.location.href='CrearCapacitacionServlet';</script>");
			out.close();
			return;
		}

		Usuario runUu = new Usuario();
		if (!runUu.setRun(runU)) {
			PrintWriter out = response.getWriter();
			out.println(
					"<script>alert('Campo Rut Obligatorio debe ser menor a 99.999.999');window.location.href='CrearCapacitacionServlet';</script>");
			out.close();
			return;
		}

		String fechaNacimiento = request.getParameter("fechaDeNacimiento");
		Usuario fechaDeNacimientoU = new Usuario();
		if (!fechaDeNacimientoU.setFechaDeNacimiento(fechaNacimiento)) {
			PrintWriter out = response.getWriter();
			out.println(
					"<script>alert('Error en el formato de fecha');window.location.href='CrearCapacitacionServlet';</script>");
			out.close();
			return;
		}

		// Se crea un objeto y se añade a un listado existente.
		Usuario usuario1 = new Usuario(nombre, fechaNacimiento, runU, tipo);
//			dao.registrarUsuario(usuario1);

		// Establecer la conexión a la base de datos
		Connection conn = Conexion.getConn();

		System.out.println("tipo de usuario" + tipo);
		if (tipo.equals("Profesional")) {
			// Profesional
			String titulo = request.getParameter("titulo");
			Profesional tituloP = new Profesional();
			if (!tituloP.setTitulo(titulo)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String fechaIngreso = request.getParameter("fechaIngreso");
			Profesional fechaI = new Profesional();
			if (!fechaI.setFechaDeIngreso(fechaIngreso)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}

			Profesional profesional = new Profesional();
			profesional.setNombre(nombre);
			profesional.setFechaDeNacimiento(fechaNacimiento);
			profesional.setRun(runU);
			profesional.setTipo(tipo);
			profesional.setTitulo(titulo);
			profesional.setFechaDeIngreso(fechaIngreso);
			
			profesionalDAO.registrarProfesional(profesional);
			int filasInsertadas = profesionalDAO.getFilasInsertadas();
			
			if (filasInsertadas > 0) {
				// Obtener el objeto PrintWriter para escribir la respuesta
				PrintWriter out = response.getWriter();

				// Generar código JavaScript para mostrar el mensaje en una ventana emergente
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"El Usuario se creó correctamente\");");
				out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
				out.println("</script>");

				// Cerrar el objeto PrintWriter
				out.close();
				// La inserción fue exitosa
				// Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
				// response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se
				// creó correctamente");
		    } else {
		    	// Ocurrió un error al insertar los datos
				// Puedes redirigir a una página de error o mostrar un mensaje de error
				response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		    }

		}
		if (tipo.equals("Cliente")) {
			// cliente
			String rut = request.getParameter("rut");
			Cliente rutC = new Cliente();
			if (!rutC.setRut(rut)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String nombres = request.getParameter("nombres");
			Cliente nombreC = new Cliente();
			if (!nombreC.setNombres(nombres)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String apellidos = request.getParameter("apellidos");
			Cliente apellidosC = new Cliente();
			if (!apellidosC.setApellidos(apellidos)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String telefono = request.getParameter("telefono");
			Cliente telefonoC = new Cliente();
			if (!telefonoC.setTelefono(telefono)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}

			String afp = request.getParameter("afp");
			Cliente afpC = new Cliente();
			if (!afpC.setAfp(afp)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String sistemaDeSalud = request.getParameter("sistemaSalud");
			Cliente sistemaS = new Cliente();
			if (!sistemaS.setSistemaDeSalud(sistemaDeSalud)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String direccion = request.getParameter("direccion");
			Cliente direccionC = new Cliente();
			if (!direccionC.setDireccion(direccion)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String comuna = request.getParameter("comuna");
			Cliente comunaC = new Cliente();
			if (!comunaC.setComuna(comuna)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String edad = request.getParameter("edad");
			Cliente edadC = new Cliente();
			if (!edadC.setEdad(edad)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}

			Cliente cliente = new Cliente();
			cliente.setNombre(nombre);
			cliente.setFechaDeNacimiento(fechaNacimiento);
			cliente.setRun(runU);
			cliente.setTipo(tipo);
			cliente.setRut(rut);
			cliente.setNombres(nombres);
			cliente.setApellidos(apellidos);
			cliente.setTelefono(telefono);
			cliente.setAfp(afp);
			cliente.setSistemaDeSalud(sistemaDeSalud);
			cliente.setDireccion(direccion);
			cliente.setComuna(comuna);
			cliente.setEdad(edad);
			
			clienteDAO.registrarCliente(cliente);
			int filasInsertadas = clienteDAO.getFilasInsertadas();

			if (filasInsertadas > 0) {
				// Obtener el objeto PrintWriter para escribir la respuesta
				PrintWriter out = response.getWriter();

				// Generar código JavaScript para mostrar el mensaje en una ventana emergente
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"El Usuario se creó correctamente\");");
				out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
				out.println("</script>");

				// Cerrar el objeto PrintWriter
				out.close();
				// La inserción fue exitosa
				// Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
				// response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se
				// creó correctamente");
		    } else {
		    	// Ocurrió un error al insertar los datos
				// Puedes redirigir a una página de error o mostrar un mensaje de error
				response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		    }
		}
		if (tipo.equals("Administrativo")) {// Administrativo
			String area = request.getParameter("area");
			Administrativo areaA = new Administrativo();
			if (!areaA.setArea(area)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}
			String experienciaPrevia = request.getParameter("experiencia");
			System.out.println("experiencia " + experienciaPrevia);
			Administrativo experienciaP = new Administrativo();
			if (!experienciaP.setExperienciaPrevia(experienciaPrevia)) {
				PrintWriter out = response.getWriter();
				out.println(
						"<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				out.close();
				return;
			}

			Administrativo administrativo = new Administrativo();
			administrativo.setNombre(nombre);
			administrativo.setFechaDeNacimiento(fechaNacimiento);
			administrativo.setRun(runU);
			administrativo.setTipo(tipo);
			administrativo.setArea(area);
			administrativo.setExperienciaPrevia(experienciaPrevia);
			
			administrativoDAO.registrarAdministrativo(administrativo);
			int filasInsertadas = administrativoDAO.getFilasInsertadas();
			
			if (filasInsertadas > 0) {
				// Obtener el objeto PrintWriter para escribir la respuesta
				PrintWriter out = response.getWriter();

				// Generar código JavaScript para mostrar el mensaje en una ventana emergente
				out.println("<script type=\"text/javascript\">");
				out.println("alert(\"El Usuario se creó correctamente\");");
				out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
				out.println("</script>");

				// Cerrar el objeto PrintWriter
				out.close();
				// La inserción fue exitosa
				// Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
				// response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se
				// creó correctamente");
		    } else {
		    	// Ocurrió un error al insertar los datos
				// Puedes redirigir a una página de error o mostrar un mensaje de error
				response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		    }
//			try {
//				// Crear una declaración SQL parametrizada
//				String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
//				PreparedStatement statement = conn.prepareStatement(sql);
//
//				// Configurar los parámetros de la declaración SQL
//				statement.setString(1, nombre);
//				statement.setString(2, tipo);
//				statement.setString(3, fechaNacimiento);
//				statement.setString(4, run);
//				statement.setString(5, area);
//				statement.setString(6, experienciaPrevia);
//
//				// Ejecutar la declaración SQL
//				int filasAfectadas = statement.executeUpdate();
//
//				if (filasAfectadas > 0) {
//					// Obtener el objeto PrintWriter para escribir la respuesta
//					PrintWriter out = response.getWriter();
//
//					// Generar código JavaScript para mostrar el mensaje en una ventana emergente
//					out.println("<script type=\"text/javascript\">");
//					out.println("alert(\"El Usuario se creó correctamente\");");
//					out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
//					out.println("</script>");
//
//					// Cerrar el objeto PrintWriter
//					out.close();
//					// La inserción fue exitosa
//					// Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
//					// response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se
//					// creó correctamente");
//				} else {
//					// Ocurrió un error al insertar los datos
//					// Puedes redirigir a una página de error o mostrar un mensaje de error
//					response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
//				}
//
//				// Cerrar la declaración
//				statement.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				// Manejar el error de base de datos
//				// Puedes redirigir a una página de error o mostrar un mensaje de error
//				response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
//			}
		}

	}

	boolean validar(String nombre, String password) {
		Map<String, String> usuarios = new HashMap<String, String>();
		usuarios.put("admin", "1234");
		return usuarios.containsKey(nombre) && usuarios.get(nombre).equals(password);
	}
}