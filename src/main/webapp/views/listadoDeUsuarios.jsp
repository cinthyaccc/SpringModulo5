<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="modelo.*"%>
<html>
<head>
<<<<<<< HEAD
<meta charset="UTF-8">

<link
=======
    <meta charset="UTF-8">
    <title>Listado de Usuarios</title>

    <link
>>>>>>> cinthya
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<<<<<<< HEAD
<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
<title>Listado de Usuarios</title>
=======
	<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
	  <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
 		.espaciado-celdas td, .espaciado-celdas th {
 		 padding: 15px; /* Ajusta el valor según tu preferencia */
		}
 
        .wrapper {
            min-height: 100%;
            display: flex;
            flex-direction: column;
        }

        .content {
            flex: 1;
            /* Aquí puedes agregar estilos adicionales para el contenido de tu página */
        }

        .footer {
            background-color: #f5f5f5;
            padding: 20px;
            /* Aquí puedes agregar estilos adicionales para el footer */
        }
         h1 {
            text-align: center;
        }
        .boton-container {
        display: flex;
        justify-content: center;
    }
    </style>
>>>>>>> cinthya
</head>
<body>
	<%@ include file='navbar.jsp'%>
	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">
<<<<<<< HEAD

	<div class="contacto" style="display: flex; justify-content: center;">
		<section>
			<h1>Usuarios</h1>

			<%
			if (request.getAttribute("u") != null) {
			%>
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th>Nombre</th>
						<th>Tipo</th>
						<th>Fecha de Nacimiento</th>
						<th>Run</th>
						<th>RUT</th>
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Teléfono</th>
						<th>AFP</th>
						<th>Sistema de Salud</th>
						<th>Dirección</th>
						<th>Comuna</th>
						<th>Edad</th>
						<th>Área</th>
						<th>Experiencia Previa</th>
						<th>Título</th>
						<th>Fecha de Ingreso</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("u");
					for (Usuario u : usuarios) {
					%>
					<tr>
						<td><%=u.getNombre()%></td>
						<td><%=u.getTipo()%></td>
						<td><%=u.getFechaDeNacimiento()%></td>
						<td><%=u.getRun()%></td>
						<%
						if (u instanceof Cliente) {
							Cliente cliente = (Cliente) u;
						%>
						<td><%=cliente.getRut()%></td>
						<td><%=cliente.getNombres()%></td>
						<td><%=cliente.getApellidos()%></td>
						<td><%=cliente.getTelefono()%></td>
						<td><%=cliente.getAfp()%></td>
						<td><%=cliente.getSistemaDeSalud()%></td>
						<td><%=cliente.getDireccion()%></td>
						<td><%=cliente.getComuna()%></td>
						<td><%=cliente.getEdad()%></td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<%
						} else if (u instanceof Administrativo) {
						Administrativo administrativo = (Administrativo) u;
						%>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td><%=administrativo.getArea()%></td>
						<td><%=administrativo.getExperienciaPrevia()%></td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<%
						} else if (u instanceof Profesional) {
						Profesional profesional = (Profesional) u;
						%>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td><%=profesional.getTitulo()%></td>
						<td><%=profesional.getFechaDeIngreso()%></td>
						<%
						}
						%>
					</tr>
					<%
=======
	<div class="container">
		<h1>Listado de Usuarios</h1>
		<form action="ListadoDeUsuariosServlet" method="post">
			<label for="tipo">Selecciona un Tipo:</label> <select id="tipo"
				name="tipo" onchange="mostrarCamposAdicionales()">
				<option value="Cliente">Cliente</option>
				<option value="Profesional">Profesional</option>
				<option value="Administrativo">Administrativo</option>
			</select> <br>
			<div class="boton-container">
    		<button type="submit" class="boton-enviar">Enviar</button>
			</div>
			
		</form>

		<%/* String tipoSeleccionado = request.getParameter("tipo"); */%>

		<% if (request.getAttribute("tipo") != null && !((String) request.getAttribute("tipo")).isEmpty()) { %>
		<% if (request.getAttribute("tipo").equals("Cliente")) { %>
		<table id="clienteTabla" class="tabla-usuario espaciado-celdas">
			<!-- Encabezado de la tabla para Cliente -->
			<thead>
				<tr>
					<th>Usuario</th>
					<th>F. nacimiento</th>
					<th>Run</th>
					<th>Rut</th>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Telefono</th>
					<th>AFP</th>
					<th>Sistema de salud</th>
					<th>Direccion</th>
					<th>Comuna</th>
					<th>Edad</th>
					<th>Editar</th>
					<!-- Agrega aquí las demás columnas para Cliente -->
				</tr>
			</thead>
			<!-- Cuerpo de la tabla para Cliente -->
			<tbody>
				<% 
	                    // Recorre la lista de clientes y muestra los datos en la tabla
						ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("u");
	                    for (Cliente cliente : clientes) {
	                    	 if (cliente.getTipo().equals("Cliente")) {
	                    %>
				<tr>
					<td><%= cliente.getNombre() %></td>
					<td><%= cliente.getFechaDeNacimiento() %></td>
					<td><%= cliente.getRun() %></td>
					<td><%= cliente.getRut() %></td>
					<td><%= cliente.getNombres() %></td>
					<td><%= cliente.getApellidos() %></td>
					<td><%= cliente.getTelefono() %></td>
					<td><%= cliente.getAfp() %></td>
					<td><%= cliente.getSistemaDeSalud() %></td>
					<td><%= cliente.getDireccion() %></td>
					<td><%= cliente.getComuna() %></td>
					<td><%= cliente.getEdad() %></td>
					<td>Editar</td>
					<!-- Agrega aquí las demás celdas para Cliente -->
				</tr>
				<%
>>>>>>> cinthya
					}
					 }
					%>
<<<<<<< HEAD
				</tbody>
			</table>
			<%
			} else {
			String mensaje = (String) request.getAttribute("mensaje");
			if (mensaje != null && !mensaje.isEmpty()) {
			%>
			<script>
				Swal.fire({
					icon: 'success',
					title: 'Éxito',
					text: '<%=mensaje%>
				',
					showConfirmButton : false,
					timer : 2000
				});
			</script>
			<%
			}
			%>
			<p><%=request.getAttribute("mensaje")%></p>
			<%
			}
			%>
		</section>
=======
			</tbody>
		</table>
		<% } else if (request.getAttribute("tipo").equals("Profesional")) { %>
		<table id="profesionalTabla" class="espaciado-celdas                                                                                                              {
        display: flex;
        justify-content: center;
    }">
			<!-- Encabezado de la tabla para Profesional -->
			<thead>
				<tr>
					<th>Usuario</th>
					<th>F. Nacimiento</th>
					<th>Run</th>
					<th>Titulo</th>
					<th>F. Ingreso</th>
					<th>Editar</th>
					<!-- Agrega aquí las demás columnas para Profesional -->
				</tr>
			</thead>
			<!-- Cuerpo de la tabla para Profesional -->
			<tbody>
				<% 
                    // Recorre la lista de profesionales y muestra los datos en la tabla
                    ArrayList<Profesional> profesionales = (ArrayList<Profesional>) request.getAttribute("u");
                    for (Profesional profesional : profesionales) {
                    	if (profesional.getTipo().equals("Profesional")) {
                    %>
				<tr>
					<td><%= profesional.getNombre() %></td>
					<td><%= profesional.getFechaDeNacimiento() %></td>
					<td><%= profesional.getRun() %></td>
					<td><%= profesional.getTitulo() %></td>
					<td><%= profesional.getFechaDeIngreso() %></td>
					<td>Editar</td>
					<!-- Agrega aquí las demás celdas para Profesional -->
				</tr>
				<%
				}
				}
				%>


			</tbody>
		</table>
		<% } else if (request.getAttribute("tipo").equals("Administrativo")) { %>
		<table id="administrativoTabla" class="espaciado-celdas">
			<!-- Encabezado de la tabla para Administrativo -->
			<thead>
				<tr>
					<th>Usuario</th>
					<th>F. nacimiento</th>
					<th>Run</th>
					<th>Area</th>
					<th>Experiencia P.</th>
					<th>Editar</th>
					<!-- Agrega aquí las demás columnas para Administrativo -->
				</tr>
			</thead>
			<!-- Cuerpo de la tabla para Administrativo -->
			<tbody>
				<% 
                    // Recorre la lista de administrativos y muestra los datos en la tabla
                    ArrayList<Administrativo> administrativos = (ArrayList<Administrativo>) request.getAttribute("u");
                    for (Administrativo administrativo : administrativos) {
                    	if (administrativo.getTipo().equals("Administrativo")) {
                    %>
				<tr>
					<td><%= administrativo.getNombre() %></td>
					<td><%= administrativo.getFechaDeNacimiento() %></td>
					<td><%= administrativo.getRun() %></td>
					<td><%= administrativo.getArea() %></td>
					<td><%= administrativo.getExperienciaPrevia() %></td>
					<td>Editar</td>
					<!-- Agrega aquí las demás celdas para Administrativo -->
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
		<% } %>
		<% } %>
>>>>>>> cinthya
	</div>
	<script>
        function mostrarCamposAdicionales() {
            var tipo = document.getElementById("tipo").value;
            var tablas = document.getElementsByClassName("tabla-usuario");

            // Oculta todas las tablas al principio
            for (var i = 0; i < tablas.length; i++) {
                tablas[i].style.display = "none";
            }

            // Muestra la tabla correspondiente según el tipo seleccionado
            var tablaSeleccionada = document.getElementById("tabla-" + tipo);
            tablaSeleccionada.style.display = "table";
            if (tipo === "Cliente") {
                clienteTabla.style.display = "table";
              } else if (tipo === "Profesional") {
                profesionalTabla.style.display = "table";
              } else if (tipo === "Administrativo") {
                administrativoTabla.style.display = "table";
              }
        }
    </script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<%@ include file='footer.jsp'%>
</body>
</html>