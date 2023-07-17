package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ClaseDao;
import InterfaceDAO.ICapacitacionDao;
import InterfaceDAO.IUsuarioDAO;
import conexion.Conexion;
import controlador.Contador;
import modelo.Administrativo;
import modelo.Cliente;
import modelo.DatosCapacitacion;
import modelo.IUsuarios;
import modelo.Profesional;
import modelo.Usuario;

/**
 * Servlet implementation class ListadoDeUsuariosServlet
 */
@WebServlet("/ListadoDeUsuariosServlet")
public class ListadoDeUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador = 0;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private IUsuarioDAO dao;
	private List<Usuario> usuario; 
    public void init() throws ServletException {
       dao = ClaseDao.getUsuarioDAO();
       usuario = new ArrayList<>();
     }
	public ListadoDeUsuariosServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		if (nombre == null || password == null || !validar(nombre, password)) {
			System.out.println("contador" + contador);
			if (contador > 0) {
				System.out.println("contador error" + contador);
				String mensaje = "clave incorrecta";
				request.setAttribute("mensaje", mensaje);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");

			Contador.setContador(1);
			System.out.println("contador incrementar: " + contador);
			dispatcher.forward(request, response);

		} else {
			Contador.setContador(0);
			HttpSession sesion = request.getSession();
			sesion.setAttribute("nombre", nombre);
			sesion.setAttribute("password", password);

			 // Obtener el valor del atributo de sesión
		   
			String tipoU = request.getParameter("tipo");
			System.out.println("tipo de usuario: " + tipoU);
			if (tipoU == null || tipoU.isEmpty()) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listadoDeUsuarios.jsp");
				dispatcher.forward(request, response);
			} else {
				// Obtener la conexión a la base de datos
				Connection conn = Conexion.getConn();

				try {
					// Crear una declaración SQL
					java.sql.Statement statement = conn.createStatement();

					// verificar tipo de usuario
					if (tipoU.equals("Administrativo")) {
						// Ejecutar la consulta SQL
						String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia FROM usuarios";
						ResultSet resultSet = statement.executeQuery(sql);
						//Crear una lista para almacenar los usuarios
						List<Administrativo> administrativos = new ArrayList<>();
						//se Listan las capacitaciones con el patron DAO
						//List<Usuario> usuarios = dao.listarUsuario();
						
						// Recorrer los resultados de la consulta
						while (resultSet.next()) {
							// Obtener los valores de cada columna
							int id = resultSet.getInt("id");
							int run = resultSet.getInt("run");
							String nombreU = resultSet.getString("nombre");
							String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
							String tipo = resultSet.getString("tipo");
							String area = resultSet.getString("area");
							String experienciaPrevia = resultSet.getString("experienciaPrevia");

							// Crear un objeto Administrativo y agregarlo a la lista
							Administrativo administrativo = new Administrativo(id, nombreU, fechaDeNacimiento, run, tipoU, area, experienciaPrevia);
							administrativos.add(administrativo);
							System.out.println(administrativos);

							// Guardar la lista de administrativos en el request
							request.setAttribute("u", administrativos);
//							System.out.println("listado de administrativos" + administrativos);
						}
						// Cerrar el ResultSet y la declaración
						resultSet.close();
						statement.close();
					}

					if (tipoU.equals("Cliente")) {
						// Ejecutar la consulta SQL
						String sqlC = "SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad FROM usuarios";
						ResultSet resultSetC = statement.executeQuery(sqlC);
						// Crear una lista para almacenar los usuarios
						List<Cliente> clientes = new ArrayList<>();
						// Recorrer los resultados de la consulta
						while (resultSetC.next()) {
							// Obtener los valores de cada columna
							int id = resultSetC.getInt("id");
							int run = resultSetC.getInt("run");
							String nombreU = resultSetC.getString("nombre");
							String fechaDeNacimiento = resultSetC.getString("fechaNacimiento");
							int rut = resultSetC.getInt("rut");
							String nombres = resultSetC.getString("nombres");
							String apellidos = resultSetC.getString("apellidos");
							int telefono = resultSetC.getInt("telefono");
							String afp = resultSetC.getString("afp");
							String sistemaDeSalud = resultSetC.getString("sistemaDeSalud");
							String direccion = resultSetC.getString("direccion");
							String comuna = resultSetC.getString("comuna");
							int edad = resultSetC.getInt("edad");

							// Crear un objeto Cliente y agregarlo a la lista
							Cliente cliente = new Cliente(id, nombreU, fechaDeNacimiento, run, tipoU, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad);
							clientes.add(cliente);
							System.out.println(clientes);

							// Guardar la lista de clientes en el request
							request.setAttribute("u", clientes);
						}
						// Cerrar el ResultSet y la declaración
						resultSetC.close();
						statement.close();
					}
					if (tipoU.equals("Profesional")) {
						// Ejecutar la consulta SQL
						String sqlP = "SELECT id, nombre, tipo, fechaNacimiento, run, titulo, fechaIngreso FROM usuarios";
						ResultSet resultSetP = statement.executeQuery(sqlP);
						// Crear una lista para almacenar los usuarios
						List<Profesional> profesionales = new ArrayList<>();
						// Recorrer los resultados de la consulta
						while (resultSetP.next()) {
							// Obtener los valores de cada columna
							int id = resultSetP.getInt("id");
							int run = resultSetP.getInt("run");
							String nombreU = resultSetP.getString("nombre");
							String fechaDeNacimiento = resultSetP.getString("fechaNacimiento");
							String titulo = resultSetP.getString("titulo");
							String fechaIngreso = resultSetP.getString("fechaIngreso");

							// Crear un objeto Profesional y agregarlo a la lista
							Profesional profesional = new Profesional(id, nombreU, fechaDeNacimiento, run, tipoU, titulo, fechaIngreso);
							profesionales.add(profesional);
							System.out.println(profesionales);

							// Guardar la lista de profesionales en el request
							request.setAttribute("u", profesionales);
							System.out.println("listado de profesionales" + profesionales);
						}
						// Cerrar el ResultSet y la declaración
						resultSetP.close();
						statement.close();
					}

					// Redirigir al JSP para mostrar los datos
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listadoDeUsuarios.jsp");
					dispatcher.forward(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		String tipo = request.getParameter("tipo");
	    System.out.println("post tipo "+tipo);
	    request.setAttribute("tipo", tipo);
	    HttpSession session = request.getSession();
	    session.setAttribute("tipoUsuario", tipo);
	    // Aquí puedes realizar las operaciones necesarias en función del tipo seleccionado
	    
	    // Por ejemplo, puedes obtener la lista correspondiente al tipo seleccionado
	    List<IUsuarios> listaUsuarios = obtenerListaUsuarios(tipo);

	    // Luego, puedes almacenar la lista en un atributo de solicitud
	    request.setAttribute("u", listaUsuarios);

	    // Finalmente, redirigir la solicitud al archivo JSP
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listadoDeUsuarios.jsp");
	    dispatcher.forward(request, response);
	    

	
	    
	    
	    
	    
	    
	}
	
	private List<IUsuarios> obtenerListaUsuarios(String tipo) {
	    List<IUsuarios> listaUsuarios = new ArrayList<>();

	    if (tipo.equals("Cliente")) {
	        // Obtener la lista de clientes y convertirla a una lista de la interfaz IUsuarios
	        List<IUsuarios> clientes = obtenerListaClientes();
	        listaUsuarios.addAll(clientes);
	    } else if (tipo.equals("Profesional")) {
	        // Obtener la lista de profesionales y convertirla a una lista de la interfaz IUsuarios
	    	List<IUsuarios> profesionales = obtenerListaProfesionales();
	        listaUsuarios.addAll(profesionales);
	    } else if (tipo.equals("Administrativo")) {
	        // Obtener la lista de administrativos y convertirla a una lista de la interfaz IUsuarios
	    	List<IUsuarios> administrativos = obtenerListaAdministrativos();
	        listaUsuarios.addAll(administrativos);
	    }

	    return listaUsuarios;
	}

	private List<IUsuarios> obtenerListaClientes() {
	    // Crear una lista para almacenar los clientes
		List<IUsuarios> clientes = new ArrayList<>();

	    // Obtener la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL
	        Statement statement = conn.createStatement();

	        // Ejecutar la consulta SQL para obtener los clientes
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad FROM usuarios";
	        ResultSet resultSet = statement.executeQuery(sql);

	        // Recorrer los resultados de la consulta
	        while (resultSet.next()) {
	            // Obtener los valores de cada columna
	        	int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
	            int run = resultSet.getInt("run");
	            String tipo = resultSet.getString("tipo");
	            int rut = resultSet.getInt("rut");
	            String nombres = resultSet.getString("nombres");
	            String apellidos = resultSet.getString("apellidos");
	            int telefono = resultSet.getInt("telefono");
	            String afp = resultSet.getString("afp");
	            String sistemaDeSalud = resultSet.getString("sistemaDeSalud");
	            String direccion = resultSet.getString("direccion");
	            String comuna = resultSet.getString("comuna");
	            int edad = resultSet.getInt("edad");

	            // Crear un objeto Cliente y agregarlo a la lista
	            Cliente cliente = new Cliente(id, nombre, fechaDeNacimiento, run, tipo, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad);
	            clientes.add(cliente);
	        }

	        // Cerrar el ResultSet y la declaración
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión a la base de datos
//	        Conexion.closeConn(conn);
	    }
	    return clientes;
	}

	private List<IUsuarios> obtenerListaProfesionales() {
	    // Crear una lista para almacenar los clientes
		List<IUsuarios> profesionales = new ArrayList<>();

	    // Obtener la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL
	        Statement statement = conn.createStatement();

	        // Ejecutar la consulta SQL para obtener los clientes
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, titulo, fechaDeIngreso FROM usuarios";
	        ResultSet resultSet = statement.executeQuery(sql);

	        // Recorrer los resultados de la consulta
	        while (resultSet.next()) {
	            // Obtener los valores de cada columna
	        	int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
	            int run = resultSet.getInt("run");
	            String tipo = resultSet.getString("tipo");
	            String titulo = resultSet.getString("titulo");
	            String fechaIngreso = resultSet.getString("fechaDeIngreso");
	            

	            // Crear un objeto Cliente y agregarlo a la lista
	            Profesional profesional = new Profesional(id, nombre, fechaDeNacimiento, run, tipo, titulo, fechaIngreso);
	            profesionales.add(profesional);
	        }

	        // Cerrar el ResultSet y la declaración
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión a la base de datos
//	    	Conexion.closeConn(conn);
	    }

	    return profesionales;
	}
	
	private List<IUsuarios> obtenerListaAdministrativos() {
	    // Crear una lista para almacenar los clientes
		List<IUsuarios> administrativos = new ArrayList<>();

	    // Obtener la conexión a la base de datos
	    Connection conn = Conexion.getConn();

	    try {
	        // Crear una declaración SQL
	        Statement statement = conn.createStatement();

	        // Ejecutar la consulta SQL para obtener los clientes
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia FROM usuarios";
	        ResultSet resultSet = statement.executeQuery(sql);

	        // Recorrer los resultados de la consulta
	        while (resultSet.next()) {
	            // Obtener los valores de cada columna
	        	int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
	            int run = resultSet.getInt("run");
	            String tipo = resultSet.getString("tipo");
	            String area = resultSet.getString("area");
	            String experienciaPrevia = resultSet.getString("experienciaPrevia");
	            

	            // Crear un objeto Cliente y agregarlo a la lista
	            Administrativo administrativo = new Administrativo(id, nombre, fechaDeNacimiento, run, tipo, area, experienciaPrevia);
	            administrativos.add(administrativo);
//	            System.out.println("listado de administrativos" + administrativos);
	        }

	        // Cerrar el ResultSet y la declaración
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar la conexión a la base de datos
//	        Conexion.closeConn(conn);
	    }

	    return administrativos;
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