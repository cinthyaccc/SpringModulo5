<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>
<title>Listado de Usuarios</title>
</head>
<body>
	<%@ include file='navbar.jsp'%>
	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">

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
					}
					%>
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
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<%@ include file='footer.jsp'%>
</body>
</html>