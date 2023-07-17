package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexion.Conexion;
import modelo.Administrativo;
import modelo.Cliente;
import modelo.Profesional;
import modelo.Usuario;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
//	private static final long serialVersionUID = 123456789L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipo");

        // Aquí validamos el tipo de usuario para determinar qué clase usar
        Usuario usuario = null;
        if ("Administrativo".equals(tipo)) {
            usuario = getAdministrativoPorId(id);
            if (usuario != null) {
                request.setAttribute("administrativo", usuario);
                request.getRequestDispatcher("/views/editarAdministrativo.jsp").forward(request, response);
            } else {
                response.sendRedirect("/views/listadoDeUsuarios.jsp");
            }
        } else if ("Profesional".equals(tipo)) {
            usuario = getProfesionalPorId(id);
            if (usuario != null) {
                request.setAttribute("profesional", usuario);
                request.getRequestDispatcher("/views/editarProfesional.jsp").forward(request, response);
            } else {
                response.sendRedirect("/views/listadoDeUsuarios.jsp");
            }
        } else if ("Cliente".equals(tipo)) {
            usuario = getClientePorId(id);
            if (usuario != null) {
                request.setAttribute("cliente", usuario);
                request.getRequestDispatcher("/views/editarCliente.jsp").forward(request, response);
            } else {
            	response.sendRedirect("/views/listadoDeUsuarios.jsp");
            }
        } else {
            // Manejamos el caso si el tipo de usuario no es válido
            response.sendRedirect("listadoDeUsuarios");
        }
    }
	// Método POST para procesar la edición del usuario
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String tipo = request.getParameter("tipo");

		System.out.println(tipo);

		if (tipo != null) {
			switch (tipo) {
			case "Profesional":

				Profesional profesional = getProfesionalPorId(id);
//				System.out.println(administrativo.getNombre());
				if (profesional != null) {
					// Actualizar los datos específicos del administrativo con los valores enviados
					// desde el formulario
					profesional.setNombre(request.getParameter("nombre"));
					profesional.setFechaDeNacimiento(request.getParameter("fechaNacimiento"));
					int run = Integer.parseInt(request.getParameter("run"));
					profesional.setRun(run);
					profesional.setTitulo(request.getParameter("titulo"));
					profesional.setFechaDeIngreso(request.getParameter("fechaIngreso"));
					
					// Aquí deberías guardar los cambios en la base de datos
					actualizarProfesionalEnBaseDeDatos(profesional);

					if (filasActualizadas > 0) {
			        	// Obtener el objeto PrintWriter para escribir la respuesta
			            PrintWriter out = response.getWriter();

			            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
			            out.println("<script type=\"text/javascript\">");
			            out.println("alert(\"El Usuario se modificó correctamente\");");
			            out.println("window.location.href = \"ListadoDeUsuariosServlet\";"); // Redirigir a otra página
			            out.println("</script>");

			            // Cerrar el objeto PrintWriter
			            out.close();
			            // La inserción fue exitosa
			            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
			            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
			        } else {
			            // Ocurrió un error al insertar los datos
			            // Puedes redirigir a una página de error o mostrar un mensaje de error
			            response.sendRedirect("ListadoDeUsuariosServlet?mensaje=Error");
			        }
				}
				break;

			case "Administrativo":

				Administrativo administrativo = getAdministrativoPorId(id);
//				System.out.println(administrativo.getNombre());
				if (administrativo != null) {
					// Actualizar los datos específicos del administrativo con los valores enviados
					// desde el formulario
					administrativo.setNombre(request.getParameter("nombre"));
					System.out.println(administrativo.getNombre());
					administrativo.setFechaDeNacimiento(request.getParameter("fechaNacimiento"));
					int run = Integer.parseInt(request.getParameter("run"));
					administrativo.setRun(run);
					administrativo.setArea(request.getParameter("area"));
					administrativo.setExperienciaPrevia(request.getParameter("experiencia"));
					
					// Aquí deberías guardar los cambios en la base de datos
					actualizarAdministrativoEnBaseDeDatos(administrativo);

					if (filasActualizadas > 0) {
			        	// Obtener el objeto PrintWriter para escribir la respuesta
			            PrintWriter out = response.getWriter();

			            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
			            out.println("<script type=\"text/javascript\">");
			            out.println("alert(\"El Usuario se modificó correctamente\");");
			            out.println("window.location.href = \"ListadoDeUsuariosServlet\";"); // Redirigir a otra página
			            out.println("</script>");

			            // Cerrar el objeto PrintWriter
			            out.close();
			            // La inserción fue exitosa
			            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
			            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
			        } else {
			            // Ocurrió un error al insertar los datos
			            // Puedes redirigir a una página de error o mostrar un mensaje de error
			            response.sendRedirect("ListadoDeUsuariosServlet?mensaje=Error");
			        }
				}
				break;

			case "Cliente":

				Cliente cliente = getClientePorId(id);
//				System.out.println(administrativo.getNombre());
				if (cliente != null) {
					// Actualizar los datos específicos del administrativo con los valores enviados
					// desde el formulario
					cliente.setNombre(request.getParameter("nombre"));
					cliente.setFechaDeNacimiento(request.getParameter("fechaNacimiento"));
					int run = Integer.parseInt(request.getParameter("run"));
					cliente.setRun(run);
		        	cliente.setRut(request.getParameter("rut"));
		        	cliente.setNombres(request.getParameter("nombres"));
		        	cliente.setApellidos(request.getParameter("apellidos"));
		        	cliente.setTelefono(request.getParameter("telefono"));
		        	cliente.setAfp(request.getParameter("afp"));
		        	cliente.setSistemaDeSalud(request.getParameter("sistemaSalud"));
		        	cliente.setDireccion(request.getParameter("direccion"));
		        	cliente.setComuna(request.getParameter("comuna"));
		        	cliente.setEdad(request.getParameter("edad"));
					
					// Aquí deberías guardar los cambios en la base de datos
					actualizarClienteEnBaseDeDatos(cliente);

					if (filasActualizadas > 0) {
			        	// Obtener el objeto PrintWriter para escribir la respuesta
			            PrintWriter out = response.getWriter();

			            // Generar código JavaScript para mostrar el mensaje en una ventana emergente
			            out.println("<script type=\"text/javascript\">");
			            out.println("alert(\"El Usuario se modificó correctamente\");");
			            out.println("window.location.href = \"ListadoDeUsuariosServlet\";"); // Redirigir a otra página
			            out.println("</script>");

			            // Cerrar el objeto PrintWriter
			            out.close();
			            // La inserción fue exitosa
			            // Puedes redirigir a una página de éxito o mostrar un mensaje de confirmación
			            //response.sendRedirect("CrearCapacitacionServlet?mensaje=La capacitación se creó correctamente");
			        } else {
			            // Ocurrió un error al insertar los datos
			            // Puedes redirigir a una página de error o mostrar un mensaje de error
			            response.sendRedirect("ListadoDeUsuariosServlet?mensaje=Error");
			        }
				}
				break;
			default:
				// Manejar el caso si el tipo de usuario no es válido
				response.sendRedirect("/views/listadoDeUsuarios.jsp");
			}
		} else {
			// Manejar el caso si el tipo de usuario no se especificó
			response.sendRedirect("/views/listadoDeUsuarios.jsp");
		}
	}

	private Administrativo getAdministrativoPorId(int id) {
	    Administrativo administrativo = null;
	    Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null; // Agregar esta línea

	    try {
	        conn = Conexion.getConn();
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia FROM usuarios WHERE id = ?";
	        statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        rs = statement.executeQuery();

	        if (rs.next()) {
	            administrativo = new Administrativo();
	            administrativo.setId(rs.getInt("id"));
	            administrativo.setNombre(rs.getString("nombre"));
	            administrativo.setTipo(rs.getString("tipo"));
	            administrativo.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
	            administrativo.setRun(rs.getInt("run"));
	            administrativo.setArea(rs.getString("area"));
	            administrativo.setExperienciaPrevia(rs.getString("experienciaPrevia"));
	        }
	    } catch (SQLException e) {
	        // Manejar errores de SQL
	        e.printStackTrace();
	    } finally {
	        // Cerrar los recursos en el bloque finally
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (conn != null) {
//	                conn.close();
	            }
	        } catch (SQLException e) {
	            // Manejar errores al cerrar los recursos
	            e.printStackTrace();
	        }
	    }

	    System.out.println("metodo getId administrativo " + administrativo.getId());
	    System.out.println("metodo getNombre administrativo " + administrativo.getNombre());
	    System.out.println("metodo getFechaDeNacimiento administrativo " + administrativo.getFechaDeNacimiento());
	    System.out.println("metodo getRun administrativo " + administrativo.getRun());
	    System.out.println("metodo getTipo administrativo " + administrativo.getTipo());
	    System.out.println("metodo getArea administrativo " + administrativo.getArea());
	    System.out.println("metodo getExperienciaPrevia administrativo " + administrativo.getExperienciaPrevia());

	    return administrativo;
	}
	
	private Profesional getProfesionalPorId(int id) {
		Profesional profesional = null;
	    Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null; // Agregar esta línea

	    try {
	        conn = Conexion.getConn();
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, titulo, fechaDeIngreso FROM usuarios WHERE id = ?";
	        statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        rs = statement.executeQuery();

	        if (rs.next()) {
	        	profesional = new Profesional();
	        	profesional.setId(rs.getInt("id"));
	        	profesional.setNombre(rs.getString("nombre"));
	        	profesional.setTipo(rs.getString("tipo"));
	        	profesional.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
	        	profesional.setRun(rs.getInt("run"));
	        	profesional.setTitulo(rs.getString("titulo"));
	        	profesional.setFechaDeIngreso(rs.getString("fechaDeIngreso"));
	        }
	    } catch (SQLException e) {
	        // Manejar errores de SQL
	        e.printStackTrace();
	    } finally {
	        // Cerrar los recursos en el bloque finally
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (conn != null) {
//	                conn.close();
	            }
	        } catch (SQLException e) {
	            // Manejar errores al cerrar los recursos
	            e.printStackTrace();
	        }
	    }

	    return profesional;
	}

	private Cliente getClientePorId(int id) {
		Cliente cliente = null;
	    Connection conn = null;
	    PreparedStatement statement = null;
	    ResultSet rs = null; // Agregar esta línea

	    try {
	        conn = Conexion.getConn();
	        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad FROM usuarios WHERE id = ?";
	        statement = conn.prepareStatement(sql);
	        statement.setInt(1, id);
	        rs = statement.executeQuery();

	        if (rs.next()) {
	        	cliente = new Cliente();
	        	cliente.setId(rs.getInt("id"));
	        	cliente.setNombre(rs.getString("nombre"));
	        	cliente.setTipo(rs.getString("tipo"));
	        	cliente.setFechaDeNacimiento(rs.getString("fechaNacimiento"));
	        	cliente.setRun(rs.getInt("run"));
	        	cliente.setRut(rs.getString("rut"));
	        	cliente.setNombres(rs.getString("nombres"));
	        	cliente.setApellidos(rs.getString("apellidos"));
	        	cliente.setTelefono(rs.getString("telefono"));
	        	cliente.setAfp(rs.getString("afp"));
	        	cliente.setSistemaDeSalud(rs.getString("sistemaDeSalud"));
	        	cliente.setDireccion(rs.getString("direccion"));
	        	cliente.setComuna(rs.getString("comuna"));
	        	cliente.setEdad(rs.getString("edad"));
	        }
	    } catch (SQLException e) {
	        // Manejar errores de SQL
	        e.printStackTrace();
	    } finally {
	        // Cerrar los recursos en el bloque finally
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (statement != null) {
	                statement.close();
	            }
	            if (conn != null) {
//	                conn.close();
	            }
	        } catch (SQLException e) {
	            // Manejar errores al cerrar los recursos
	            e.printStackTrace();
	        }
	    }

	    return cliente;
	}
	
	private int filasInsertadas;
	private int filasActualizadas;

	private void actualizarAdministrativoEnBaseDeDatos(Administrativo administrativo) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = Conexion.getConn();

			// Ejecutar la consulta SQL para modificar el administrativo
			String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, fechaNacimiento = ?, run = ?, area = ?, experienciaPrevia = ? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, administrativo.getNombre());
			statement.setString(2, administrativo.getTipo());
			statement.setString(3, administrativo.getFechaDeNacimiento());
			statement.setInt(4, administrativo.getRun());
			statement.setString(5, administrativo.getArea());
			statement.setString(6, administrativo.getExperienciaPrevia());
			statement.setInt(7, administrativo.getId()); // Agregar el ID para identificar el administrativo a modificar

			filasActualizadas = statement.executeUpdate();

			// Resto del código
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        // Cerrar los recursos en el bloque finally
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (conn != null) {
//	                conn.close();
	            }
	        } catch (SQLException e) {
	            // Manejar errores al cerrar los recursos
	            e.printStackTrace();
	        }

		}
	}

	private void actualizarProfesionalEnBaseDeDatos(Profesional profesional) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = Conexion.getConn();

			// Ejecutar la consulta SQL para modificar el administrativo
			String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, fechaNacimiento = ?, run = ?, titulo = ?, fechaDeIngreso = ? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, profesional.getNombre());
			statement.setString(2, profesional.getTipo());
			statement.setString(3, profesional.getFechaDeNacimiento());
			statement.setInt(4, profesional.getRun());
			statement.setString(5, profesional.getTitulo());
			statement.setString(6, profesional.getFechaDeIngreso());
			statement.setInt(7, profesional.getId()); // Agregar el ID para identificar el administrativo a modificar

			filasActualizadas = statement.executeUpdate();

			// Resto del código
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        // Cerrar los recursos en el bloque finally
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (conn != null) {
//	                conn.close();
	            }
	        } catch (SQLException e) {
	            // Manejar errores al cerrar los recursos
	            e.printStackTrace();
	        }

		}
	}
	
	private void actualizarClienteEnBaseDeDatos(Cliente cliente) {
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = Conexion.getConn();
//SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad FROM usuarios WHERE id = ?
			// Ejecutar la consulta SQL para modificar el administrativo
			String sql = "UPDATE usuarios SET nombre = ?, tipo = ?, fechaNacimiento = ?, run = ?, rut = ?, nombres = ?, apellidos = ?, telefono = ?, afp = ?, sistemaDeSalud = ?, direccion = ?, comuna = ?, edad = ? WHERE id = ?";
			statement = conn.prepareStatement(sql);

			// Configurar los parámetros de la declaración SQL
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getTipo());
			statement.setString(3, cliente.getFechaDeNacimiento());
			statement.setInt(4, cliente.getRun());
			statement.setInt(5, cliente.getRut());
			statement.setString(6, cliente.getNombres());
			statement.setString(7, cliente.getApellidos());
			statement.setInt(8, cliente.getTelefono());
			statement.setString(9, cliente.getAfp());
			statement.setString(10, cliente.getSistemaDeSalud());
			statement.setString(11, cliente.getDireccion());
			statement.setString(12, cliente.getComuna());
			statement.setInt(13, cliente.getEdad());
			statement.setInt(14, cliente.getId()); // Agregar el ID para identificar el administrativo a modificar

			filasActualizadas = statement.executeUpdate();

			// Resto del código
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        // Cerrar los recursos en el bloque finally
	        try {
	            if (statement != null) {
	                statement.close();
	            }
	            if (conn != null) {
//	                conn.close();
	            }
	        } catch (SQLException e) {
	            // Manejar errores al cerrar los recursos
	            e.printStackTrace();
	        }

		}
	}

	// Métodos para obtener la conexión a la base de datos
	// ...
}