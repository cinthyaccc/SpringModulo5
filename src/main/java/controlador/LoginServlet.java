package controlador;

import java.io.IOException;
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
import controlador.Contador;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		int contador = Contador.getContador();
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
			sesion.setAttribute("password", password);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/contacto.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("POST");
		doGet(request, response);
	}

	boolean validar(String nombre, String password) {
		Map<String, String> usuarios = new HashMap<String, String>();
		usuarios.put("admin", "1234");
		return usuarios.containsKey(nombre) && usuarios.get(nombre).equals(password);
	}
}
