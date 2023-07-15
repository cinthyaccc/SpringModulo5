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

import modelo.Administrativo;
import modelo.Cliente;
import modelo.Profesional;
import modelo.Usuario;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
	private static final String DB_USER = "usuario";
	private static final String DB_PASSWORD = "contraseña";

	public EditarUsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tipo = request.getParameter("tipo");

		if (tipo != null) {
			switch (tipo) {
				case "Cliente":
					editarCliente(request, response);
					break;
				case "Administrativo":
					editarAdministrativo(request, response);
					break;
				case "Profesional":
					editarProfesional(request, response);
					break;
				default:
					response.sendRedirect("ListadoUsuariosServlet");
					break;
			}
		} else {
			response.sendRedirect("ListadoUsuariosServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
