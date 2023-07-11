package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexion.Conexion;
import controlador.Contador;

/**
 * 
 * @author Grupo 5: Sabina Leal, Juan Barrientos, Manuel Chavez, Sebastian
 *         Fernandez, Cinthya Caldera.
 *
 */
/**
 * Servlet implementation class Inicio
 */
@WebServlet("/InicioServlet")
public class InicioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InicioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
			Contador.setContador(0);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("nombre", nombre);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/inicio.jsp");
			dispatcher.forward(request, response);
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