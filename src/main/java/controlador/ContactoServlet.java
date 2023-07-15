package controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controlador.Contador;
import java.io.PrintWriter;

/**
 * 
 * @author Grupo 5: Sabina Leal, Juan Barrientos, Manuel Chavez, Sebastian
 *         Fernandez, Cinthya Caldera.
 *
 */
/**
 * Servlet implementation class Contacto
 */
@WebServlet("/ContactoServlet")
public class ContactoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactoServlet() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/contacto.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombre = request.getParameter("nombre");
	    String email = request.getParameter("email");
	    String comentario = request.getParameter("comentario");

	    // Mostrar los datos en la consola
	    System.out.println("Datos ingresados:");
	    System.out.println("Nombre: " + nombre);
	    System.out.println("Email: " + email);
	    System.out.println("Comentario: " + comentario);

	    // Preparar la respuesta JSON
	    response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    String jsonResponse = "{\"mensaje\": \"Formulario enviado exitosamente\"}";
	    out.println(jsonResponse);
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