package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexion.Conexion;
import modelo.Usuario;

@WebServlet("/BuscarUsuarioServlet")
public class BuscarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("search");

        // Obtener la conexión a la base de datos
        Connection conn = Conexion.getConn();

        try {
            // Crear una declaración SQL
            Statement statement = conn.createStatement();

            // Ejecutar la consulta SQL con el término de búsqueda
            String sql = "SELECT * FROM usuarios WHERE nombre LIKE '%" + searchTerm + "%'";
            ResultSet resultSet = statement.executeQuery(sql);

            // Crear una lista para almacenar los usuarios encontrados
            List<Usuario> usuarios = new ArrayList<>();

            // Recorrer los resultados de la consulta
            while (resultSet.next()) {
                // Obtener los valores de cada columna
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String tipo = resultSet.getString("tipo");
                String fechaNacimiento = resultSet.getString("fechaNacimiento");
                int run = resultSet.getInt("run");

                // Crear un objeto Usuario y agregarlo a la lista
                Usuario usuario = new Usuario(id, nombre, tipo, fechaNacimiento, run);
                usuarios.add(usuario);
            }

            // Cerrar el ResultSet y la declaración
            resultSet.close();
            statement.close();

            // Guardar la lista de usuarios encontrados en el request
            request.setAttribute("u", usuarios);

            // Redirigir al JSP para mostrar los resultados de búsqueda
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listadoDeUsuarios.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}