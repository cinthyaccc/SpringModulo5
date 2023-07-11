<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Usuarios</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
<%@ include file='navbar.jsp'%>	
<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg" alt="Logo de la empresa">

<div class=contacto style="display: flex; justify-content: center;">

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
						
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			} else {
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