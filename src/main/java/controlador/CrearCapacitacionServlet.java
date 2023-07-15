package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import InterfaceDAO.ICapacitacionDao;

import java.sql.Statement;
import java.sql.ResultSet;

import DAO.ClaseDao;
import conexion.Conexion;
import controlador.Contador;
import DAO.ClaseDao;
import InterfaceDAO.ICapacitacionDao;
import modelo.DatosCapacitacion;

/**
 * 
 * @author Grupo 5: Sabina Leal, Juan Barrientos, Manuel Chavez, Sebastian
 *         Fernandez, Cinthya Caldera.
 *
 */
/**
 * Servlet implementation class CrearCapacitacionServlet
 */
@WebServlet("/CrearCapacitacionServlet")
public class CrearCapacitacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador;

	// Crear y llenar la lista de capacitaciones
	private ArrayList<DatosCapacitacion> capacitaciones = new ArrayList<DatosCapacitacion>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearCapacitacionServlet() {
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
		System.out.println("nombre" + nombre + "contraseña" + password);
		System.out.println("contador" + contador);
		if (nombre == null || password == null || !validar(nombre, password)) {
			System.out.println("porque");
			if (contador > 0) {

				String mensaje = "clave incorrecta";
				request.setAttribute("mensaje", mensaje);

			}

			Contador.setContador(1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);

		} else {
			System.out.println("entra aqui");
			Contador.setContador(1);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("nombre", nombre);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/crearCapacitacion.jsp");
			dispatcher.forward(request, response);
			
		}
		
		String mensaje = request.getParameter("mensaje");

	    if (mensaje != null && mensaje.equals("Error")) {
	        // Acciones para manejar el mensaje de error
	    } else {
	        // Acciones normales del servlet
	    }


	    }
		
	private ICapacitacionDao dao;

    public void init() throws ServletException {
        dao = ClaseDao.getCapacitacionDAO();
    }
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obtener los parámetros del formulario
		
		String id = request.getParameter("idCapacitacion");
		int idC;

		try {
		    idC = Integer.parseInt(id);
		    
		} catch (NumberFormatException e) {
		    // Manejar el error si no se puede convertir a entero
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Error en el campo Identificador, debe ser un valor númerico');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}

		DatosCapacitacion idCc = new DatosCapacitacion();
		if (!idCc.setIdentificador(idC)) {
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Campo Indetificador Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}

		
		
		String nombre = request.getParameter("nombre");
		  DatosCapacitacion usuario = new DatosCapacitacion();
		  if (!usuario.setNombre(nombre)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error Campo Nombre es Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		
		
		String detalle = request.getParameter("detalle");
		 DatosCapacitacion detalleC = new DatosCapacitacion();
		  if (!detalleC.setDetalle(detalle)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error Campo Detalle es Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		
		
		
		String rut = request.getParameter("rutCliente");
		
		int rutC;

		try {
		    rutC = Integer.parseInt(rut);
		} catch (NumberFormatException e) {
		    // Manejar el error si no se puede convertir a entero
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Error en el campo Rut, debe ser un valor númerico');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}


		DatosCapacitacion rutCc = new DatosCapacitacion();
		if (!rutCc.setRutCliente(rutC)) {
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Campo Rut Obligatorio debe ser menor a 99.999.999');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}
		
		
		String dia = request.getParameter("dia");
		DatosCapacitacion diaC = new DatosCapacitacion();
		  if (!diaC.setDia(dia)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error en Campo Día');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		
		
		
		String hora = request.getParameter("hora");
		DatosCapacitacion horaC = new DatosCapacitacion();
		  if (!horaC.setHora(hora)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error Formato Hora Invalido');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		String lugar = request.getParameter("lugar");
		DatosCapacitacion lugarC = new DatosCapacitacion();
		  if (!lugarC.setLugar(lugar)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error Campo Lugar es Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		String duracion = request.getParameter("duracion");
		DatosCapacitacion duracionC = new DatosCapacitacion();
		  if (!duracionC.setDuracion(duracion)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error Campo Duración es Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		String cantidad = request.getParameter("cantidadAsistentes");
		int cantidadC;
		try {
		 cantidadC = Integer.parseInt(cantidad);

		} catch (NumberFormatException e) {
		    // Manejar el error si no se puede convertir a entero
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Error en el campo Cantidad, debe ser un valor númerico');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}
		
		// Se crea un objeto y se añade a un listado existente.
				DatosCapacitacion capacitacion1 = new DatosCapacitacion(idC, rutC, dia, hora, lugar, duracion, cantidadC, nombre, detalle);
				dao.registrarCapacitacion(capacitacion1);
		
		
	    
	    // Establecer la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL parametrizada
	        String sql = "INSERT INTO capacitaciones (id, nombre, detalle, rut, dia, hora, lugar, duracion, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = conn.prepareStatement(sql);

	        // Configurar los parámetros de la declaración SQL
	        statement.setString(1, id );
	        statement.setString(2, nombre);
	        statement.setString(3, detalle );
	        statement.setString(4, rut);
	        statement.setString(5, dia );
	        statement.setString(6, hora);
	        statement.setString(7, lugar );
	        statement.setString(8, duracion);
	        statement.setString(9, cantidad );

	        // Ejecutar la declaración SQL
	        int filasAfectadas = statement.executeUpdate();

	        if (filasAfectadas > 0) {
	        	// Obtener el objeto PrintWriter para escribir la respuesta
	            PrintWriter out = response.getWriter();

	            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
	            out.println("<script type=\"text/javascript\">");
	            out.println("alert(\"La capacitación se creó correctamente\");");
	            out.println("window.location.href = \"CrearCapacitacionServlet\";"); // Redirigir a otra página
	            out.println("</script>");

	            // Cerrar el objeto PrintWriter
	            out.close();
	            // La inserción fue exitosa
	            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
	            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
	        } else {
	            // Ocurrió un error al insertar los datos
	            // Puedes redirigir a una página de error o mostrar un mensaje de error
	            response.sendRedirect("CrearCapacitacionServlet?mensaje=Error");
	        }

	        // Cerrar la declaración
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar el error de base de datos
	        // Puedes redirigir a una página de error o mostrar un mensaje de error
	        response.sendRedirect("CrearCapacitacionServlet?mensaje=Error");
	    } 
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