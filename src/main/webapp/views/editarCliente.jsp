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
<title>Editar Cliente</title>
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
			<h1 class=tituloContacto>Formulario de edición de Cliente</h1>
			<br> <br>
			<!--Datos en comun para todos los tipos de Usuario-->
			<label for="nombre">Ingrese el nombre de usuario:</label><br> <input
				type="text" id="nombre" name="nombre" value="${cliente.nombre}" title="Campo Obligatorio"><br>
			<br> <span id="nombreValidationMessage" style="color: red;"></span>
			<br> <label for="fechaDeNacimiento">Ingrese fecha de
				nacimiento:</label><br> <input type="text" id="fechaDeNacimiento"
				name="fechaDeNacimiento" value="${cliente.fechaDeNacimiento}" title="Formato DD/MM/AAAA"><br>
			<br> <span id="fechaDeNacimientoValidationMessage"
				style="color: red;"></span> <br> <label for="run">Ingrese
				run:</label><br> <input type="text" id="run" name="run" value="${cliente.run}"
				title="Campo Obligatorio / Debe Introducir un Valor Númerico."><br>
			<br> <span id="runValidationMessage" style="color: red;"></span>
			<br> <label for="rut">RUT:</label><br> <input type="text"
				id="rut" name="rut" value="${cliente.rut}"
				title="Campo Obligatorio / Debe Introducir un Valor Númerico."><br>
			<br> <span id="rutValidationMessage" style="color: red;"></span>
			<br> <label for="nombres">Nombres:</label><br> <input
				type="text" id="nombres" name="nombres" value="${cliente.nombres}" title="Campo Obligatorio"><br>
			<br> <span id="nombresValidationMessage" style="color: red;"></span>
			<br> <label for="apellidos">Apellidos:</label><br> <input
				type="text" id="apellidos" name="apellidos" value="${cliente.apellidos}"
				title="Campo Obligatorio"><br>
			<br> <span id="apellidosValidationMessage" style="color: red;"></span>
			<br> <label for="telefono">Teléfono:</label><br> <input
				type="text" id="telefono" name="telefono" value="${cliente.telefono}"
				title="Campo Obligatorio / Debe Introducir un Valor Númerico."><br>
			<br> <span id="telefonoValidationMessage" style="color: red;"></span>
			<br> <label for="afp">AFP:</label><br> <input type="text"
				id="afp" name="afp" value="${cliente.afp}" title="Campo Obligatorio"><br>
			<br> <span id="afpValidationMessage" style="color: red;"></span>
			<br> <label for="sistemaSalud">Sistema de salud:</label><br>
			<select id="sistemaSalud" name="sistemaSalud" value="${cliente.sistemaDeSalud}">
				<option value="1">Fonasa</option>
				<option value="2">Isapre</option>
			</select> <br> <br> <label for="direccion">Dirección:</label><br>
			<input type="text" id="direccion" name="direccion" value="${cliente.direccion}"
				title="Campo Obligatorio"><br>
			<br> <span id="direccionValidationMessage" style="color: red;"></span>
			<br> <label for="comuna">Comuna:</label><br> <input
				type="text" id="comuna" name="comuna" value="${cliente.comuna}" title="Campo Obligatorio"><br>
			<br> <span id="comunaValidationMessage" style="color: red;"></span>
			<br> <label for="edad">Edad:</label><br> <input type="text"
				id="edad" name="edad" value="${cliente.edad}"
				title="Campo Obligatorio / Debe Introducir un Valor Númerico."><br>
			<br> <span id="edadValidationMessage" style="color: red;"></span>
			<br>

			<input type="hidden" name="tipo" value="Cliente">
			<input type="hidden" name="id" value="${cliente.id}">

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


//Validar datos del usurio tipo cliente
//Validar Campo Rut
var rutInput = document.getElementById('rut');
var rutValidationMessage = document.getElementById('rutValidationMessage');

rutInput.addEventListener('input', function() {
  var rutValue = rutInput.value.trim();

  if (rutValue === '') {
    rutValidationMessage.textContent = 'Por favor, introduzca el Rut. Campo Obligatorio';
  } else if (!/^[0-9]+$/.test(rutValue)) {
    rutValidationMessage.textContent = 'Por favor, introduzca un Rut válido. Solo se permiten dígitos numéricos.';
  } else if (parseInt(rutValue, 10) > 99999999) {
    rutValidationMessage.textContent = 'Por favor, introduzca un Rut válido. El número no puede ser mayor a 99.999.999.';
  } else {
    rutValidationMessage.textContent = '';
  }
});

  
//Validar Campo nombres
  var nombresInput = document.getElementById('nombres');
  var nombresValidationMessage = document.getElementById('nombresValidationMessage');

  nombresInput.addEventListener('input', function() {
    var nombresValue = nombresInput.value.trim();

    if (nombresValue === '') {
      nombresValidationMessage.textContent = 'Por favor, introduzca nombre. Campo Obligatorio';
    } else {
      nombresValidationMessage.textContent = '';
    }
  });

  // Validar Campo apellidos
  var apellidosInput = document.getElementById('apellidos');
  var apellidosValidationMessage = document.getElementById('apellidosValidationMessage');

  apellidosInput.addEventListener('input', function() {
    var apellidosValue = apellidosInput.value.trim();

    if (apellidosValue === '') {
      apellidosValidationMessage.textContent = 'Por favor, introduzca apellidos. Campo Obligatorio';
    } else {
      apellidosValidationMessage.textContent = '';
    }
  });

//Validar Campo telefono
  var telefonoInput = document.getElementById('telefono');
  var telefonoValidationMessage = document.getElementById('telefonoValidationMessage');

  telefonoInput.addEventListener('input', function() {
    var telefonoValue = telefonoInput.value.trim();
    
    if (telefonoValue === '') {
      telefonoValidationMessage.textContent = 'Por favor, introduzca un número de teléfono. Campo Obligatorio';
    } else if (!isValidPhoneNumber(telefonoValue)) {
      telefonoValidationMessage.textContent = 'Por favor, introduzca un número de teléfono válido';
    } else {
      telefonoValidationMessage.textContent = '';
    }
  });
  

  function isValidPhoneNumber(phoneNumber) {
    // Validar que el número de teléfono contenga solo dígitos numéricos
    var numberPattern = /^\d+$/;
    return numberPattern.test(phoneNumber);
  }

  //Validar Campo afp
  var afpInput = document.getElementById('afp');
  var afpValidationMessage = document.getElementById('afpValidationMessage');

  afpInput.addEventListener('input', function() {
    var afpValue = afpInput.value.trim();
    
    if (afpValue === '') {
      afpValidationMessage.textContent = 'Por favor, introduzca nombre. Campo Obligatorio';
    } else {
      afpValidationMessage.textContent = '';
    }
  });
  //Validar Campo direccion
  var direccionInput = document.getElementById('direccion');
  var direccionValidationMessage = document.getElementById('direccionValidationMessage');

  direccionInput.addEventListener('input', function() {
    var direccionValue = direccionInput.value.trim();
    
    if (direccionValue === '') {
      direccionValidationMessage.textContent = 'Por favor, introduzca nombre. Campo Obligatorio';
    } else {
      direccionValidationMessage.textContent = '';
    }
  });
  
//Validar Campo comuna
  var comunaInput = document.getElementById('comuna');
  var comunaValidationMessage = document.getElementById('comunaValidationMessage');

  comunaInput.addEventListener('input', function() {
    var comunaValue = comunaInput.value.trim();
    
    if (comunaValue === '') {
      comunaValidationMessage.textContent = 'Por favor, introduzca la comuna. Campo Obligatorio';
    } else {
      comunaValidationMessage.textContent = '';
    }
  });

  
  var edadInput = document.getElementById('edad');
  var edadValidationMessage = document.getElementById('edadValidationMessage');

  edadInput.addEventListener('input', function() {
    var edadValue = edadInput.value.trim();
    
    if (edadValue === '') {
      edadValidationMessage.textContent = 'Por favor, introduzca una edad. Campo Obligatorio';
    } else if (!isValidAge(edadValue)) {
      edadValidationMessage.textContent = 'Por favor, introduzca una edad válida';
    } else {
      edadValidationMessage.textContent = '';
    }
  });

  function isValidAge(age) {
    // Validar que la edad contenga solo dígitos numéricos
    var numberPattern = /^\d+$/;
    return numberPattern.test(age);
  }

    
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