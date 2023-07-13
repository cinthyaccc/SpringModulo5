package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditarUsuarioServlet
 */
@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del usuario desde los parámetros de la URL
        String idUsuario = request.getParameter("id");

        // Aquí puedes realizar la lógica para obtener los datos actuales del usuario de la base de datos
        // Utiliza el ID del usuario para obtener los datos del usuario que se desea editar

        // Guardar los datos del usuario en un objeto de modelo o en los atributos de solicitud
        // Por ejemplo:
        // Usuario usuario = obtenerUsuarioPorId(idUsuario);
        // request.setAttribute("usuario", usuario);

        // Redirigir a la página de edición de usuario
        RequestDispatcher dispatcher = request.getRequestDispatcher("editarUsuario.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
