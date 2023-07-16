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

import DAO.AdministrativoDAOimpl;
import DAO.ClienteDAOImpl;
import DAO.ProfesionalDAOImpl;
import modelo.Administrativo;
import modelo.Cliente;
import modelo.Profesional;
import modelo.Usuario;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoUsuario = request.getParameter("tipo");
        String usuarioId = request.getParameter("id");

        Usuario usuario = obtenerUsuarioPorId(tipoUsuario, usuarioId);

        if (usuario != null) {
            String jspEditar = getJspEditar(tipoUsuario);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher(jspEditar).forward(request, response);
        } else {
            response.getWriter().println("Usuario no encontrado o tipo de usuario no válido.");
        }
    }

    // Método que obtiene el usuario por su ID y tipo de usuario
    private Usuario obtenerUsuarioPorId(String tipoUsuario, String id) {
        // Código para obtener el usuario según su ID y tipo
    }

    // Método que devuelve el nombre del JSP de edición según el tipo de usuario
    private String getJspEditar(String tipoUsuario) {
        String jspEditar;

        if ("Cliente".equalsIgnoreCase(tipoUsuario)) {
            jspEditar = "editarCliente.jsp";
        } else if ("Profesional".equalsIgnoreCase(tipoUsuario)) {
            jspEditar = "editarProfesional.jsp";
        } else if ("Administrativo".equalsIgnoreCase(tipoUsuario)) {
            jspEditar = "editarAdministrativo.jsp";
        } else {
        	//Falta hacer ese jsp
            jspEditar = "error.jsp"; // O manejar un JSP para mostrar un mensaje de error.
        }

        return jspEditar;
    }
}