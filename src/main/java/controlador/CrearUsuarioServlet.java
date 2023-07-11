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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexion.Conexion;
import controlador.Contador;
import modelo.DatosCapacitacion;
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
		  if (!tipoU.setTipo(tipo)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Debe seleccionar un tipo');window.location.href='CrearCapacitacionServlet';</script>");
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
		    out.println("<script>alert('Error en el campo Rut, debe ser un valor númerico');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}


		Usuario runUu = new Usuario();
		if (!runUu.setRun(runU)) {
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Campo Rut Obligatorio debe ser menor a 99.999.999');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}
		
		
		String fechaNacimiento = request.getParameter("fechaDeNacimiento");
		Usuario fechaDeNacimientoU = new Usuario();
		  if (!fechaDeNacimientoU.setFechaDeNacimiento(fechaNacimiento)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error en el formato de fecha');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
			    
	    // Establecer la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL parametrizada
	        String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run) VALUES (DEFAULT, ?, ?, ?, ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);

	        // Configurar los parámetros de la declaración SQL
	        statement.setString(1, nombre);
	        statement.setString(2, tipo);
	        statement.setString(3, fechaNacimiento);
	        statement.setString(4, run);
	        
	        // Ejecutar la declaración SQL
	        int filasAfectadas = statement.executeUpdate();

	        if (filasAfectadas > 0) {
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
	            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
	        } else {
	            // Ocurrió un error al insertar los datos
	            // Puedes redirigir a una página de error o mostrar un mensaje de error
	            response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
	        }

	        // Cerrar la declaración
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar el error de base de datos
	        // Puedes redirigir a una página de error o mostrar un mensaje de error
	        response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
	    } 
	}
	
	boolean validar(String nombre, String password) {
		Map<String, String> usuarios = new HashMap<String, String>();
		usuarios.put("admin", "1234");
		return usuarios.containsKey(nombre) && usuarios.get(nombre).equals(password);
	}
	
}