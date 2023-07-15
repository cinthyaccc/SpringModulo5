package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import modelo.DatosCapacitacion;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import InterfaceDAO.ICapacitacionDao;
import conexion.Conexion;
import controlador.Contador;
import DAO.ClaseDao;




/**
 * 
 * @author Grupo 5: Sabina Leal, Juan Barrientos, Manuel Chavez, Sebastian
 *         Fernandez, Cinthya Caldera.
 *
 */
/**
 * Servlet implementation class ListarCapacitacionServlet
 */
@WebServlet("/ListarCapacitacionServlet")
public class ListarCapacitacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private ICapacitacionDao dao;
	private List<DatosCapacitacion> capacitaciones; 
    public void init() throws ServletException {
       dao = ClaseDao.getCapacitacionDAO();
       capacitaciones = new ArrayList<>();
     }
	
	public ListarCapacitacionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int contador = Contador.getContador();
		HttpSession session = request.getSession();
	    String tipoUsuario = (String) session.getAttribute("usuario");

	    // Lógica para habilitar/deshabilitar elementos del navbar según el tipo de usuario
	    boolean mostrarFuncionalidadAdministrador = tipoUsuario.equals("administrador");
	    boolean mostrarFuncionalidadCliente = tipoUsuario.equals("cliente");
	    boolean mostrarFuncionalidadProfesional = tipoUsuario.equals("profesional");
	    boolean mostrarFuncionalidadAdministrativo = tipoUsuario.equals("administrativo");

	    // Guardar los valores en atributos de solicitud para su uso en la página JSP
	    request.setAttribute("mostrarFuncionalidadAdministrador", mostrarFuncionalidadAdministrador);
	    request.setAttribute("mostrarFuncionalidadCliente", mostrarFuncionalidadCliente);
	    request.setAttribute("mostrarFuncionalidadProfesional", mostrarFuncionalidadProfesional);
	    request.setAttribute("mostrarFuncionalidadAdministrativo", mostrarFuncionalidadAdministrativo);
		
		
		
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
			        String sql = "SELECT id, rut, dia, hora, lugar, duracion, cantidad, nombre, detalle FROM capacitaciones";
			        ResultSet resultSet = statement.executeQuery(sql);

			        // Crear una lista para almacenar las capacitaciones
			       // List<DatosCapacitacion> capacitaciones = new ArrayList<>();
			        
			      //se Listan las capacitaciones con el patron DAO
					List<DatosCapacitacion> capacitaciones = dao.listarCapacitacion();
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

			        // Guardar la lista de capacitaciones en el request
			        request.setAttribute("capacitaciones", capacitaciones);

			        // Redirigir al JSP para mostrar los datos
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listarCapacitaciones.jsp");
			        dispatcher.forward(request, response);
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
	
			
			
			// Obtener la lista de capacitaciones desde el ámbito de aplicación
			//ServletContext context = request.getServletContext();
			//List<DatosCapacitacion> capacitaciones = (List<DatosCapacitacion>) context.getAttribute("capacitaciones");

			// Verificar si la lista de capacitaciones está vacía
			//if (capacitaciones != null && !capacitaciones.isEmpty()) {
				// La lista de capacitaciones no está vacía, pasamos la lista como atributo a la
				// página JSP
				//request.setAttribute("c", capacitaciones);
		//	} else {
				// La lista de capacitaciones está vacía, pasamos un mensaje alternativo a la
				// página JSP
			//	String mensaje = "No se han encontrado capacitaciones.";
				//request.setAttribute("mensaje", mensaje);
			//}

			// Obtener el objeto RequestDispatcher para el archivo JSP
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listarCapacitaciones.jsp");

			// Redirigir la solicitud al archivo JSP
			//dispatcher.forward(request, response);
		//}

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

	        usuarios.put("cliente", "cliente");
	        usuarios.put("profesional", "profesional");
	        usuarios.put("administrativo", "administrativo");

	        String nombreLowerCase = nombre.toLowerCase(); // Convertir a minúsculas
	        return usuarios.containsKey(nombreLowerCase) && usuarios.get(nombreLowerCase).equals(password);
	    }
}
