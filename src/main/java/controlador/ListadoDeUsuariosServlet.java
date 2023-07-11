package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * Servlet implementation class ListadoDeUsuariosServlet
 */
@WebServlet("/ListadoDeUsuariosServlet")
public class ListadoDeUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoDeUsuariosServlet() {
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
		// TODO Auto-generated method stub

		int contador = Contador.getContador();
		HttpSession session = request.getSession();
		String nombre = (String) session.getAttribute("nombre");
		String password = (String) session.getAttribute("password");

		if (nombre == null || password == null || !validar(nombre, password)) {
			System.out.println("contador" + contador);
			if (contador > 0) {
				System.out.println("contador error" + contador);
				String mensaje = "clave incorrecta";
				request.setAttribute("mensaje", mensaje);

			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");

			Contador.setContador(1);
			System.out.println("contador incrementar: " + contador);
			dispatcher.forward(request, response);

		} else {
			Contador.setContador(0);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("nombre", nombre);
			sesion.setAttribute("password", password);
			
			
		}
		 // Obtener la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL
	        java.sql.Statement statement = conn.createStatement();

	        // Ejecutar la consulta SQL
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run FROM usuarios";
	        ResultSet resultSet = statement.executeQuery(sql);
   
	        

	        
	        
	        // Crear una lista para almacenar las capacitaciones
	        List<Usuario> usuario = new ArrayList<>();

	        // Recorrer los resultados de la consulta
	        while (resultSet.next()) {
	            // Obtener los valores de cada columna
	        	
	            int id = resultSet.getInt("id");
	          
	            int run = resultSet.getInt("run");
	            String nombreU = resultSet.getString("nombre");
	            String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
	            String tipo = resultSet.getString("tipo");
	           
	            // Crear un objeto Capacitacion y agregarlo a la lista
	            Usuario usuarios = new Usuario(id, nombreU, fechaDeNacimiento, run, tipo);
	            usuario.add(usuarios);
	            System.out.println(usuario);
	           
	        }

	        // Cerrar el ResultSet y la declaración
	        resultSet.close();
	        statement.close();

	        // Guardar la lista de capacitaciones en el request
	        request.setAttribute("u", usuario);

	        // Redirigir al JSP para mostrar los datos
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listadoDeUsuarios.jsp");
	        dispatcher.forward(request, response);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	boolean validar(String nombre, String password) {
		Map<String, String> usuarios = new HashMap<String, String>();
		usuarios.put("admin", "1234");
		return usuarios.containsKey(nombre) && usuarios.get(nombre).equals(password);
	}

}
