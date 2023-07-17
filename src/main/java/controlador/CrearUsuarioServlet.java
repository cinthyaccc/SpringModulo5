package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import DAO.ClaseDao;
import InterfaceDAO.ICapacitacionDao;
import InterfaceDAO.IUsuarioDAO;
import conexion.Conexion;
import controlador.Contador;
import modelo.Administrativo;
import modelo.Cliente;
import modelo.DatosCapacitacion;
import modelo.Profesional;
import modelo.Usuario;

/**
 * 
 * @author Grupo 5: Sabina Leal, Juan Barrientos, Manuel Chavez, Sebastian
 *         Fernandez, Cinthya Caldera.
 *
 */
/**
 * Servlet implementation class CrearCapacitacionServlet
 */
@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador;

	// Crear y llenar la lista de usuarios
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// Verificacion del login
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
	    
	    
	    
		int contador = Contador.getContador();
		
		String nombre = (String) session.getAttribute("nombre");
		String password = (String) session.getAttribute("password");
		System.out.println("contador" + contador);
		System.out.println(nombre + password);
		if (nombre == null || password == null || !validar(nombre, password)) {
			System.out.println("porque entra aqui");
			if (contador > 0) {

				String mensaje = "clave incorrecta";
				request.setAttribute("mensaje", mensaje);

			}
			
			
			
			
			

			Contador.setContador(1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
			dispatcher.forward(request, response);

		} else {
			Contador.setContador(0);
			System.out.println("holaaaa");
			HttpSession sesion = request.getSession();
			sesion.setAttribute("nombre", nombre);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/crearUsuario.jsp");
			dispatcher.forward(request, response);
		}

	}
	
	private IUsuarioDAO dao;

    public void init() throws ServletException {
        dao = ClaseDao.getUsuarioDAO();
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Se inicializan algunas variables obtenidas a traves del formulario mediante
		// metodo getparameter.
		String nombre = request.getParameter("nombre");
		Usuario nombreU = new Usuario();
		if (!nombreU.setNombre(nombre)) {
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}

				
		String tipo = request.getParameter("tipo");
		Usuario tipoU = new Usuario();
		

		if (tipo.isEmpty()) {
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Debe seleccionar un tipo');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}

		
		
		String run = request.getParameter("run");
		
		int runU;

		try {
		    runU = Integer.parseInt(run);
		} catch (NumberFormatException e) {
		    // Manejar el error si no se puede convertir a entero
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Error en el campo Rut, debe ser un valor númerico');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}


		Usuario runUu = new Usuario();
		if (!runUu.setRun(runU)) {
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('Campo Rut Obligatorio debe ser menor a 99.999.999');window.location.href='CrearCapacitacionServlet';</script>");
		    out.close();
		    return;
		}
		
		
		String fechaNacimiento = request.getParameter("fechaDeNacimiento");
		Usuario fechaDeNacimientoU = new Usuario();
		  if (!fechaDeNacimientoU.setFechaDeNacimiento(fechaNacimiento)) {
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('Error en el formato de fecha');window.location.href='CrearCapacitacionServlet';</script>");
			    out.close();
			    return;
			}
		  
		// Se crea un objeto y se añade a un listado existente.
			Usuario usuario1 = new Usuario(0, nombre, fechaNacimiento, runU, tipo);
			dao.registrarUsuario(usuario1);
				
			
						
						
		
						

		  
	    // Establecer la conexión a la base de datos
	    Connection conn = Conexion.getConn();
	    
	    System.out.println("tipo de usuario" + tipo);
	    if (tipo.equals("Profesional")) {
	    	//Profesional
			  String titulo = request.getParameter("titulo");
				Profesional tituloP = new Profesional();
				if (!tituloP.setTitulo(titulo)) {
				    PrintWriter out = response.getWriter();
				    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				    out.close();
				    return;
				}  
				 String fechaIngreso = request.getParameter("fechaIngreso");
				 Profesional fechaI = new Profesional();
					if (!fechaI.setFechaDeIngreso(fechaIngreso)) {
					    PrintWriter out = response.getWriter();
					    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
					    out.close();
					    return;
					}  
	  
	    	try {
		        // Crear una declaración SQL parametrizada
		        String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, titulo, fechaDeIngreso) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement statement = conn.prepareStatement(sql);

		        // Configurar los parámetros de la declaración SQL
		        statement.setString(1, nombre);
		        statement.setString(2, tipo);
		        statement.setString(3, fechaNacimiento);
		        statement.setString(4, run);
		        statement.setString(5, titulo);
		        statement.setString(6, fechaIngreso);
		        
		        // Ejecutar la declaración SQL
		        int filasAfectadas = statement.executeUpdate();

		        if (filasAfectadas > 0) {
		        	// Obtener el objeto PrintWriter para escribir la respuesta
		            PrintWriter out = response.getWriter();

		            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
		            out.println("<script type=\"text/javascript\">");
		            out.println("alert(\"El Usuario se creó correctamente\");");
		            out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
		            out.println("</script>");

		            // Cerrar el objeto PrintWriter
		            out.close();
		            // La inserción fue exitosa
		            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
		            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
		        } else {
		            // Ocurrió un error al insertar los datos
		            // Puedes redirigir a una página de error o mostrar un mensaje de error
		            response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		        }

		        // Cerrar la declaración
		        statement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Manejar el error de base de datos
		        // Puedes redirigir a una página de error o mostrar un mensaje de error
		        response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		    } 
	    	
	    	
	    	
	    	
	    	
	    } 
	    if (tipo.equals("Cliente")) {
	    	//cliente
			String rut = request.getParameter("rut");
			 Cliente rutC = new Cliente();
				if (!rutC.setRut(rut)) {
				    PrintWriter out = response.getWriter();
				    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				    out.close();
				    return;
				}  
				String nombres = request.getParameter("nombres");
				 Cliente nombreC = new Cliente();
					if (!nombreC.setNombres(nombres)) {
					    PrintWriter out = response.getWriter();
					    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
					    out.close();
					    return;
					}  
					String apellidos = request.getParameter("apellidos");
					 Cliente apellidosC = new Cliente();
						if (!apellidosC.setApellidos(apellidos)) {
						    PrintWriter out = response.getWriter();
						    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
						    out.close();
						    return;
						}  
						String telefono = request.getParameter("telefono");
						 Cliente telefonoC = new Cliente();
							if (!telefonoC.setTelefono(telefono)) {
							    PrintWriter out = response.getWriter();
							    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
							    out.close();
							    return;
							}  
			
							String afp = request.getParameter("afp");
							 Cliente afpC = new Cliente();
								if (!afpC.setAfp(afp)) {
								    PrintWriter out = response.getWriter();
								    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
								    out.close();
								    return;
								}  
								String sistemaDeSalud = request.getParameter("sistemaSalud");
								 Cliente sistemaS = new Cliente();
									if (!sistemaS.setSistemaDeSalud(sistemaDeSalud)) {
									    PrintWriter out = response.getWriter();
									    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
									    out.close();
									    return;
									}  
									sistemaDeSalud = sistemaS.getSistemaDeSalud();

									String direccion = request.getParameter("direccion");
									 Cliente direccionC = new Cliente();
										if (!direccionC.setDireccion(direccion)) {
										    PrintWriter out = response.getWriter();
										    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
										    out.close();
										    return;
										}  
										String comuna = request.getParameter("comuna");
										 Cliente comunaC = new Cliente();
											if (!comunaC.setComuna(comuna)) {
											    PrintWriter out = response.getWriter();
											    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
											    out.close();
											    return;
											}  
											String edad = request.getParameter("edad");
											 Cliente edadC = new Cliente();
												if (!edadC.setEdad(edad)) {
												    PrintWriter out = response.getWriter();
												    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
												    out.close();
												    return;
												}  
	    	
	    	
	    	
	    	try {
		        // Crear una declaración SQL parametrizada
		        String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement statement = conn.prepareStatement(sql);

		        // Configurar los parámetros de la declaración SQL
		        statement.setString(1, nombre);
		        statement.setString(2, tipo);
		        statement.setString(3, fechaNacimiento);
		        statement.setString(4, run);
		        statement.setString(5, rut);
		        statement.setString(6, nombres);
		        statement.setString(7, apellidos);
		        statement.setString(8, telefono);
		        statement.setString(9, afp);
		        statement.setString(10, sistemaDeSalud);
		        statement.setString(11, direccion);
		        statement.setString(12, comuna);
		        statement.setString(13, edad);
		     
		        
		        // Ejecutar la declaración SQL
		        int filasAfectadas = statement.executeUpdate();

		        if (filasAfectadas > 0) {
		        	// Obtener el objeto PrintWriter para escribir la respuesta
		            PrintWriter out = response.getWriter();

		            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
		            out.println("<script type=\"text/javascript\">");
		            out.println("alert(\"El Usuario se creó correctamente\");");
		            out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
		            out.println("</script>");

		            // Cerrar el objeto PrintWriter
		            out.close();
		            // La inserción fue exitosa
		            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
		            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
		        } else {
		            // Ocurrió un error al insertar los datos
		            // Puedes redirigir a una página de error o mostrar un mensaje de error
		            response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		        }

		        // Cerrar la declaración
		        statement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Manejar el error de base de datos
		        // Puedes redirigir a una página de error o mostrar un mensaje de error
		        response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		    }  
	    } 
	    if (tipo.equals("Administrativo")) {//Administrativo
			  String area = request.getParameter("area");
				Administrativo areaA = new Administrativo();
				if (!areaA.setArea(area)) {
				    PrintWriter out = response.getWriter();
				    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
				    out.close();
				    return;
				}  
				 String experienciaPrevia = request.getParameter("experiencia");
				 System.out.println("experiencia " + experienciaPrevia);
					Administrativo experienciaP = new Administrativo();
					if (!experienciaP.setExperienciaPrevia(experienciaPrevia)) {
					    PrintWriter out = response.getWriter();
					    out.println("<script>alert('Campo Obligatorio');window.location.href='CrearCapacitacionServlet';</script>");
					    out.close();
					    return;
					}  
	    	
	    	
	    	
	    	
	    	try {
		        // Crear una declaración SQL parametrizada
		        String sql = "INSERT INTO usuarios (id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement statement = conn.prepareStatement(sql);

		        // Configurar los parámetros de la declaración SQL
		        statement.setString(1, nombre);
		        statement.setString(2, tipo);
		        statement.setString(3, fechaNacimiento);
		        statement.setString(4, run);
		        statement.setString(5, area);
		        statement.setString(6, experienciaPrevia);
		        
		        
		        
		        
		        // Ejecutar la declaración SQL
		        int filasAfectadas = statement.executeUpdate();

		        if (filasAfectadas > 0) {
		        	// Obtener el objeto PrintWriter para escribir la respuesta
		            PrintWriter out = response.getWriter();

		            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
		            out.println("<script type=\"text/javascript\">");
		            out.println("alert(\"El Usuario se creó correctamente\");");
		            out.println("window.location.href = \"CrearUsuarioServlet\";"); // Redirigir a otra página
		            out.println("</script>");

		            // Cerrar el objeto PrintWriter
		            out.close();
		            // La inserción fue exitosa
		            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
		            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
		        } else {
		            // Ocurrió un error al insertar los datos
		            // Puedes redirigir a una página de error o mostrar un mensaje de error
		            response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		        }

		        // Cerrar la declaración
		        statement.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Manejar el error de base de datos
		        // Puedes redirigir a una página de error o mostrar un mensaje de error
		        response.sendRedirect("CrearUsuarioServlet?mensaje=Error");
		    }  
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