<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/GrupalM5/css/styles.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<title>Editar Administrativo</title>
<style>
.prueba {
	background-image: url("/GrupalM5/img/imagen4.jpeg");
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body class="prueba">
	<%@ include file='navbar.jsp'%>
	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">

	<div class=contacto style="display: flex; justify-content: center;">

		<form action="EditarUsuarioServlet" method="post"
			onsubmit="return enviarFormulario(event)">
			<h1 class=tituloContacto>Formulario de edición de administrativo</h1>
			<br> <br>
			<!--Datos en comun para todos los tipos de Usuario-->
			<label for="nombre">Ingrese el nombre de usuario:</label><br> <input
				type="text" id="nombre" name="nombre"
				value="${administrativo.nombre}" title="Campo Obligatorio"><br>
			<br> <span id="nombreValidationMessage" style="color: red;"></span>
			<br> <label for="fechaDeNacimiento">Ingrese fecha de
				nacimiento:</label><br> <input type="text" id="fechaDeNacimiento"
				name="fechaDeNacimiento" value="${administrativo.fechaDeNacimiento}"
				title="Formato DD/MM/AAAA"><br> <br> <span
				id="fechaDeNacimientoValidationMessage" style="color: red;"></span>
			<br> <label for="run">Ingrese run:</label><br> <input
				type="text" id="run" name="run" value="${administrativo.run}"
				title="Campo Obligatorio / Debe Introducir un Valor Númerico."><br>
			<br> <span id="runValidationMessage" style="color: red;"></span>
			<br> <label for="area">Área:</label><br> <input type="text"
				id="area" name="area" value="${administrativo.area}"
				title="Campo Obligatorio"><br> <br> <span
				id="areaValidationMessage" style="color: red;"></span> <br> <label
				for="experiencia">Experiencia previa:</label><br> <input
				type="text" id="experiencia" name="experiencia"
				value="${administrativo.experienciaPrevia}"
				title="Campo Obligatorio"><br> <br> <span
				id="experienciaValidationMessage" style="color: red;"></span> <br>

			<input type="hidden" name="tipo" value="Administrativo">
			<input type="hidden" name="id" value="${administrativo.id}">
			
			<div style="display: flex; justify-content: center;">
				<input type="submit" value="Enviar" class="boton-enviar">
			</div>
		</form>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
    //Validaciones
    //Validar Campo nombre
var nombreInput = document.getElementById('nombre');
var nombreValidationMessage = document.getElementById('nombreValidationMessage');

nombreInput.addEventListener('input', function() {
  var nombreValue = nombreInput.value.trim();
  
  if (nombreValue === '') {
    nombreValidationMessage.textContent = 'Por favor, introduzca nombre. Campo Obligatorio';
  } else {
    nombreValidationMessage.textContent = '';
  }
});
//Validar Campo fecha de nacimiento
var fechaDeNacimientoInput = document.getElementById('fechaDeNacimiento');
var fechaDeNacimientoValidationMessage = document.getElementById('fechaDeNacimientoValidationMessage');

fechaDeNacimientoInput.addEventListener('input', function() {
  var fechaDeNacimientoValue = fechaDeNacimientoInput.value.trim();
  
  if (fechaDeNacimientoValue === '') {
    fechaDeNacimientoValidationMessage.textContent = 'Por favor, introduzca la fecha de nacimiento. Campo Obligatorio';
  } else if (!isValidDateFormat(fechaDeNacimientoValue)) {
    fechaDeNacimientoValidationMessage.textContent = 'El formato de fecha debe ser dd/mm/aaaa';
  } else {
    fechaDeNacimientoValidationMessage.textContent = '';
  }
});

function isValidDateFormat(dateString) {
  // Validar el formato de fecha dd/mm/aaaa usando una expresión regular
  var datePattern = /^\d{2}\/\d{2}\/\d{4}$/;
  return datePattern.test(dateString);
}
//Validar Campo Run
var runInput = document.getElementById('run');
var runValidationMessage = document.getElementById('runValidationMessage');

runInput.addEventListener('input', function() {
  var runValue = runInput.value.trim();
  
  if (runValue === '') {
    runValidationMessage.textContent = 'Por favor, introduzca el Rut sin puntos ni guion';
  } else if (!/^[0-9]+$/.test(runValue)) {
    runValidationMessage.textContent = 'Por favor, introduzca un Rut válido. Solo se permiten dígitos numéricos.';
  } else if (parseInt(runValue, 10) > 99999999) {
    runValidationMessage.textContent = 'Por favor, introduzca un Rut válido. El número no puede ser mayor a 99.999.999.';
  } else {
    runValidationMessage.textContent = '';
  }
});

//Validar datos del usuario de tipo administrativo
  var areaInput = document.getElementById('area');
var areaValidationMessage = document.getElementById('areaValidationMessage');

areaInput.addEventListener('input', function() {
  var areaValue = areaInput.value.trim();
  
  if (areaValue === '') {
    areaValidationMessage.textContent = 'Por favor, introduzca el área. Campo Obligatorio';
  } else {
    areaValidationMessage.textContent = '';
  }
});

var experienciaInput = document.getElementById('experiencia');
var experienciaValidationMessage = document.getElementById('experienciaValidationMessage');

experienciaInput.addEventListener('input', function() {
  var experienciaValue = experienciaInput.value.trim();
  
  if (experienciaValue === '') {
    experienciaValidationMessage.textContent = 'Por favor, introduzca la experiencia. Campo Obligatorio';
  } else {
    experienciaValidationMessage.textContent = '';
  }
});

  
    
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
        }
    </script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
	<%@ include file='footer.jsp'%>
</body>
</html>