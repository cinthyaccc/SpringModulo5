<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <style>
body {
    background-image: url("/GrupalM5/img/imagen3.png");
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
<link

	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
	  <link rel="stylesheet" type="text/css" href="/GrupalM5/css/styles.css">
	 
<title>Crear Capacitación</title>
</head>
<body>
	<%@ include file='navbar.jsp'%>
	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">

	<div class=contacto style="display: flex; justify-content: center;">
		<form action="CrearCapacitacionServlet" method="post">
			<h1 class=tituloContacto>Formulario de Capacitación</h1>
			<label for="nombre">Ingrese Identificador de la Capacitación:</label><br>
			<input type="text" id="idCapacitacion" name="idCapacitacion" title="Campo Obligatorio / Debe Introducir un Valor Númerico." ><br><br> 
			<span id="idCapacitacionValidationMessage" style="color: red;"></span> <br>
			<label for="nombre">Ingrese Nombre de la Capacitación:</label><br>
			 <input type="text" id="nombre" name="nombre" title="Campo Obligatorio"><br><br> 
			 <span id="nombreValidationMessage" style="color: red;"></span><br>
			 <label for="nombre">Ingrese Detalle:</label><br> 
			<input type="text" id="detalle" name="detalle" title="Campo Obligatorio"><br> <br>
			 <span id="detalleValidationMessage" style="color: red;"></span> <br>
			<label for="nombre">Ingrese Rut:</label><br> 
			<input type="text" id="rutCliente" name="rutCliente" title="Campo Obligatorio / Debe Introducir un Valor menor a 99.999.999 /sin puntos"><br> <br>
			 <span id="rutValidationMessage" style="color: red;"></span> <br>
			<label for="nombre">Ingrese el Día de la Capacitación:</label><br>
			<input type="text" id="dia" name="dia" pattern="(lunes|martes|miercoles|jueves|viernes|sabado|domingo)" title="Por favor, introduzca un día válido (lunes, martes, miércoles, jueves, viernes, sábado o domingo)."><br> <br>
			<span id="diaValidationMessage" style="color: red;"></span> <br>
			<label for="nombre">Ingrese la Hora de la Capacitación:</label><br> 
				<input type="text" id="hora" name="hora" title="Campo Obligatorio / en Formato HH:MM"><br><br> 
				<span id="horaValidationMessage" style="color: red;"></span> <br>
				<label for="nombre">Ingrese el Lugar de la Capacitación:</label><br> 
				<input type="text" id="lugar" name="lugar" title="Campo Obligatorio / Min 10 Caracteres Max 50"><br><br> 
				<span id="lugarValidationMessage" style="color: red;"></span> <br>
				<label for="nombre">Ingrese la Duración de la Capacitación:</label><br>
			<input type="text" id="duracion" name="duracion" title="Campo Obligatorio / Max 70 Caracteres."><br><br>
			<span id="duracionValidationMessage" style="color: red;"></span> <br>
			<label for="nombre">Ingrese la Cantidad de Asistentes:</label><br>
			<input type="text" id="cantidadAsistentes" name="cantidadAsistentes" title="Campo Obligatorio / Debe Introducir un Valor Númerico."><br><br>
			<span id="cantidadAsistentesValidationMessage" style="color: red;"></span> 
			<br>

			<div style="display: flex; justify-content: center;">
				<input type="submit" value="Enviar" class="boton-enviar">
			</div>
		</form>
	</div>

	<div id="mensaje-exito" class="alert alert-success" role="alert"
		style="display: none;">La capacitación se creó correctamente.</div>
<script>
//Validar Campo idCapacitacion
var idCapacitacionInput = document.getElementById('idCapacitacion');
var idCapacitacionValidationMessage = document.getElementById('idCapacitacionValidationMessage');

idCapacitacionInput.addEventListener('input', function() {
  var idCapacitacionValue = idCapacitacionInput.value.trim();
  
  if (idCapacitacionValue === '') {
    idCapacitacionValidationMessage.textContent = 'Por favor, introduzca el Identificador de la Capacitación. Campo Obligatorio';
  } else if (!/^\d+$/.test(idCapacitacionValue)) {
    idCapacitacionValidationMessage.textContent = 'Por favor, introduzca un Identificador de la Capacitación válido. Solo se permiten números enteros.';
  } else {
    idCapacitacionValidationMessage.textContent = '';
  }
});

//Validar Campo detalle
var detalleInput = document.getElementById('detalle');
var detalleValidationMessage = document.getElementById('detalleValidationMessage');

detalleInput.addEventListener('input', function() {
  var detalleValue = detalleInput.value.trim();
  
  if (detalleValue === '') {
    detalleValidationMessage.textContent = 'Por favor, introduzca el Detalle de la Capacitación. Campo Obligatorio';
  } else {
    detalleValidationMessage.textContent = '';
  }
});


  // Validar Campo Día
  var diaInput = document.getElementById('dia');
  var diaValidationMessage = document.getElementById('diaValidationMessage');

  diaInput.addEventListener('input', function() {
    if (diaInput.validity.patternMismatch) {
      diaValidationMessage.textContent = 'Por favor, introduzca un día válido (lunes, martes, miércoles, jueves, viernes, sábado o domingo).';
    } else {
      diaValidationMessage.textContent = '';
    }
  });

//Validar Campo nombre
  var nombreInput = document.getElementById('nombre');
  var nombreValidationMessage = document.getElementById('nombreValidationMessage');

  nombreInput.addEventListener('input', function() {
    var nombreValue = nombreInput.value.trim();

    if (nombreValue === '') {
      nombreValidationMessage.textContent = 'Por favor, introduzca el Nombre de la Capacitación. Campo Obligatorio';
    } else {
      nombreValidationMessage.textContent = '';
    }
  });

//Validar Campo Rut
  var rutInput = document.getElementById('rutCliente');
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
//Validar Campo hora
  var horaInput = document.getElementById('hora');
  var horaValidationMessage = document.getElementById('horaValidationMessage');

  horaInput.addEventListener('input', function() {
    var horaValue = horaInput.value.trim();
    var horaPattern = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/; // Expresión regular para validar el formato HH:MM
    
    if (horaValue === '') {
      horaValidationMessage.textContent = 'Por favor, introduzca la Hora de la Capacitación. Campo Obligatorio';
    } else if (!horaPattern.test(horaValue)) {
      horaValidationMessage.textContent = 'Por favor, introduzca la Hora en el formato correcto (HH:MM).';
    } else {
      horaValidationMessage.textContent = '';
    }
  });
//Validar Campo lugar
  var lugarInput = document.getElementById('lugar');
  var lugarValidationMessage = document.getElementById('lugarValidationMessage');

  lugarInput.addEventListener('input', function() {
    var lugarValue = lugarInput.value.trim();
    
    if (lugarValue === '') {
      lugarValidationMessage.textContent = 'Por favor, introduzca el Lugar de la Capacitación. Campo Obligatorio';
    } else if (lugarValue.length < 10 || lugarValue.length > 50) {
      lugarValidationMessage.textContent = 'Por favor, introduzca un Lugar con una longitud entre 10 y 50 caracteres.';
    } else {
      lugarValidationMessage.textContent = '';
    }
  });
//Validar Campo duracion
  var duracionInput = document.getElementById('duracion');
  var duracionValidationMessage = document.getElementById('duracionValidationMessage');

  duracionInput.addEventListener('input', function() {
    var duracionValue = duracionInput.value.trim();
    
    if (duracionValue === '') {
      duracionValidationMessage.textContent = 'Por favor, introduzca la Duración de la Capacitación. Campo Obligatorio';
    } else if (duracionValue.length > 70) {
      duracionValidationMessage.textContent = 'Por favor, introduzca una Duración con una longitud máxima de 70 caracteres.';
    } else {
      duracionValidationMessage.textContent = '';
    }
  });

//Validar Campo cantidadAsistentes
  var cantidadAsistentesInput = document.getElementById('cantidadAsistentes');
  var cantidadAsistentesValidationMessage = document.getElementById('cantidadAsistentesValidationMessage');

  cantidadAsistentesInput.addEventListener('input', function() {
    var cantidadAsistentesValue = cantidadAsistentesInput.value.trim();

    if (cantidadAsistentesValue === '') {
      cantidadAsistentesValidationMessage.textContent = 'Por favor, introduzca la Cantidad de Asistentes. Campo Obligatorio';
    } else if (!/^\d+$/.test(cantidadAsistentesValue)) {
      cantidadAsistentesValidationMessage.textContent = 'Por favor, introduzca solo valores numéricos para la Cantidad de Asistentes.';
    } else if (parseInt(cantidadAsistentesValue) > 999) {
      cantidadAsistentesValidationMessage.textContent = 'Por favor, introduzca un valor máximo de 999 para la Cantidad de Asistentes.';
    } else {
      cantidadAsistentesValidationMessage.textContent = '';
    }
  });

  
  
</script>

	<script>
	
	
        function enviarFormulario(event) {
            event.preventDefault(); // Cancelar el envío del formulario
            
            // Mostrar el mensaje de éxito
            document.getElementById("mensaje-exito").style.display = "block";
            
            // Recargar la página después de 3 segundos
            setTimeout(function() {
                location.reload();
            }, 3000);
            
            return false;
        }
    </script>
	<div style="height: 100px;"></div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
<%@ include file='footer.jsp'%>
</body>
</html>