<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Usuarios</title>
<link rel="stylesheet" type="text/css" href="/GrupalM5/css/styles.css">
<%@ page
	import="java.util.ArrayList, modelo.Usuario, modelo.Cliente, modelo.Administrativo, modelo.Profesional"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">


<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
<style>

/* Estilos personalizados */
</style>

</head>
<body>
	<%@ include file='navbar.jsp'%>
	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">

	<div class="container" style="text-align: center;">
		<h1 style="text-align: center; margin-bottom: 20px;">Usuarios:</h1>
		<form action="ListadoDeUsuariosServlet" method="post">
			<label for="tipo">Selecciona un Tipo:</label> <select id="tipo"
				name="tipo" onchange="mostrarCamposAdicionales()">
				<option>Seleccione</option>
				<option value="Cliente">Cliente</option>
				<option value="Profesional">Profesional</option>
				<option value="Administrativo">Administrativo</option>
			</select> <br>
			<div class="boton-container">
				<br>
				<button type="submit" class="boton-enviar">Enviar</button>
			</div>

		</form>


		<%
		/* String tipoSeleccionado = request.getParameter("tipo"); */
		%>

		<%
		if (request.getAttribute("tipo") != null && !((String) request.getAttribute("tipo")).isEmpty()) {
		%>
		<%
		if (request.getAttribute("tipo").equals("Cliente")) {
		%>



		<table id="clienteTabla" class="table table-striped">
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
					<td><%=cliente.getNombre()%></td>
					<td><%=cliente.getFechaDeNacimiento()%></td>
					<td><%=cliente.getRun()%></td>
					<td><%=cliente.getRut()%></td>
					<td><%=cliente.getNombres()%></td>
					<td><%=cliente.getApellidos()%></td>
					<td><%=cliente.getTelefono()%></td>
					<td><%=cliente.getAfp()%></td>
					<td><%=cliente.getSistemaDeSalud()%></td>
					<td><%=cliente.getDireccion()%></td>
					<td><%=cliente.getComuna()%></td>
					<td><%=cliente.getEdad()%></td>
					<td><a
						href="EditarUsuarioServlet?id=<%=cliente.getId()%>&tipo=Cliente">Editar</a>
					</td>
					<!-- Agrega aquí las demás celdas para Cliente -->
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
		<%
		} else if (request.getAttribute("tipo").equals("Profesional")) {
		%>

		<table id="profesionalTabla" class="table table-striped">
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
					<td><%=profesional.getNombre()%></td>
					<td><%=profesional.getFechaDeNacimiento()%></td>
					<td><%=profesional.getRun()%></td>
					<td><%=profesional.getTitulo()%></td>
					<td><%=profesional.getFechaDeIngreso()%></td>
					<td><a
						href="EditarUsuarioServlet?id=<%=profesional.getId()%>&tipo=Profesional">Editar</a>
					</td>
					<!-- Agrega aquí las demás celdas para Profesional -->
				</tr>
				<%
				}
				}
				%>


			</tbody>
		</table>
		<%
		} else if (request.getAttribute("tipo").equals("Administrativo")) {
		%>

		<table id="administrativoTabla" class="table table-striped">
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
					<td><%=administrativo.getNombre()%></td>
					<td><%=administrativo.getFechaDeNacimiento()%></td>
					<td><%=administrativo.getRun()%></td>
					<td><%=administrativo.getArea()%></td>
					<td><%=administrativo.getExperienciaPrevia()%></td>
					<td><a
						href="EditarUsuarioServlet?id=<%=administrativo.getId()%>&tipo=Administrativo">Editar</a>
					</td>
					<!-- Agrega aquí las demás celdas para Administrativo -->
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>
		<%
		}
		%>
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
		
		function editarAdministrativo(id) {
		    $.ajax({
		      url: "EditarUsuarioServlet",
		      type: "GET",
		      data: { id: id, tipo: "Administrativo" },
		      success: function(response) {
		        // Manejar la respuesta del servidor si es necesario
		      },
		      error: function(xhr, status, error) {
		        // Manejar errores en la solicitud AJAX si es necesario
		      }
		    });
		  }
	</script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<%@ include file='footer.jsp'%>
</body>
</html>