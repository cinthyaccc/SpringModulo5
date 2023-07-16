<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/GrupalM5/css/styles.css">
<meta charset="UTF-8">
<style>
body {
    background-image: url("/GrupalM5/img/imagen5.jpeg");
    background-repeat: no-repeat;
    background-size: cover;
}
</style>
<title>Formulario de Contacto</title>
</head>

<body>
<%@ include file='navbar.jsp'%>	
<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg" alt="Logo de la empresa">

<div class=contacto style="display: flex; justify-content: center;">

<form action="ContactoServlet" method="post">
<h1 class=tituloContacto>Formulario de Contacto</h1>
  <label for="nombre">Nombre:</label><br>
  <input type="text" id="nombre" name="nombre"><br><br>
  
  <label for="email">Email:</label><br>
  <input type="email" id="email" name="email"><br><br>
  
  <label for="comentario">Comentario:</label><br>
  <textarea id="comentario" name="comentario"></textarea><br><br>
  
   <div style="display: flex; justify-content: center;">
      <input type="submit" value="Enviar" class="boton-enviar">
    </div>
  
  
</form>
    </div>
    
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
 <script>
    // Obtener el formulario y manejar el evento de envío
    var formulario = document.querySelector('form');
    formulario.addEventListener('submit', function(event) {
        event.preventDefault(); // Cancelar el envío del formulario

        // Obtener los datos del formulario
        var formData = new FormData(formulario);

        // Crear una instancia de XMLHttpRequest
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'ContactoServlet', true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

        // Manejar la respuesta del servidor
        xhr.onload = function() {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                if (response.mensaje) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Éxito',
                        text: response.mensaje
                    }).then(function() {
                        // Borrar los datos del formulario
                        formulario.reset();
                    });
                }
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Hubo un problema al enviar el formulario'
                });
            }
        };

        // Enviar la solicitud AJAX
        xhr.send(new URLSearchParams(formData));
    });
</script>

 
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<%@ include file='footer.jsp'%>
</body>
</html>