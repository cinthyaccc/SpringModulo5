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
            String mensaje = "Usuario o contraseña incorrectos";
           
            
            request.setAttribute("mensaje", mensaje);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
            dispatcher.forward(request, response);
     
        	}
        	 Contador.setContador(1);
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
 			dispatcher.forward(request, response);
        } else {
        	Contador.setContador(0);
            HttpSession session = request.getSession();
            session.setAttribute("nombre", nombre);
            session.setAttribute("password", password);
            session.setAttribute("usuario", obtenerTipoUsuario(nombre));
            session.setAttribute("sesionIniciada", true); // Nuevo atributo para indicar que la sesión está iniciada

            response.sendRedirect("InicioServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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


    String obtenerTipoUsuario(String nombre) {
        Map<String, String> tiposUsuario = new HashMap<String, String>();
      
        tiposUsuario.put("cliente", "cliente");
        tiposUsuario.put("profesional", "profesional");
        tiposUsuario.put("administrativo", "administrativo");
        return tiposUsuario.get(nombre);
    }
}
