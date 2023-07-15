package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexion.Conexion;
import modelo.Usuario;

/**
 * Servlet implementation class EditarUsuariosServlet
 */
@WebServlet("/EditarUsuariosServlet")
public class EditarUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idUsuario = request.getParameter("id");
    	
    	
    	Connection conn = Conexion.getConn();

        try {
            // Crear una declaración SQL
            String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad, area, experienciaPrevia, titulo, fechaDeIngreso FROM usuarios WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idUsuario);

            // Ejecutar la consulta SQL y obtener los resultados
            ResultSet resultSet = statement.executeQuery();

            // Verificar si se encontró el usuario
            if (resultSet.next()) {
                // Obtener los valores de cada columna
                String id = resultSet.getString("id");
                String nombre = resultSet.getString("nombre");
                String tipo = resultSet.getString("tipo");
                // Obtener los demás valores del usuario

                // Crear un objeto Usuario con los datos obtenidos
                Usuario usuario = new Usuario(id, nombre, tipo, );

                // Guardar el usuario en un atributo de solicitud
                request.setAttribute("usuario", usuario);
            }

            // Cerrar el ResultSet y la declaración
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos
//            Conexion.closeConn(conn);
        }
    	
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
