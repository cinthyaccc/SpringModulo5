<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<title>Crear Usuario</title>
</head>
<body>
	<%@ include file='navbar.jsp'%>
	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">

	<div class=contacto style="display: flex; justify-content: center;">

		<form action="CrearUsuarioServlet" method="post" onsubmit="return enviarFormulario(event)">
			<h1 class=tituloContacto>Formulario de Creacion de Usuario</h1>
			<label for="nombre">Ingrese el nombre de usuario:</label><br>
			<input type="text" id="nombre" name="nombre"><br>
			<br> <label for="nombre">Ingrese fecha de nacimiento:</label><br> <input
				type="text" id="fechaDeNacimiento" name="fechaDeNacimiento"><br> <br>
			<label for="nombre">Ingrese run:</label><br>
			<input type="text" id="run" name="run"><br>
			<br> 
			<label for="tipo">Selecciona un Tipo:</label> 
			<select id="tipo" name="tipo">
				<option value="Seleccione">Seleccione</option>
				<option value="Cliente">Cliente</option>
				<option value="Profesional">Profesional</option>
				<option value="Administrativo">Administrativo</option>
			</select> 
			<br>
			<br>

			<div style="display: flex; justify-content: center;">
				<input type="submit" value="Enviar" class="boton-enviar">
			</div>
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		function enviarFormulario(event) {
			event.preventDefault(); // Cancelar el envío del formulario

			Swal.fire({
				icon: 'success',
				title: 'Éxito',
				text: 'El usuario se creó correctamente.'
			}).then(() => {
				document.forms[0].submit(); // Enviar el formulario después de mostrar la alerta
			});

			return false;
		}
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
<%@ include file='footer.jsp'%>
</body>
</html>
