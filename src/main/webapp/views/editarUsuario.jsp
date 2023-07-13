<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<title>Editar Usuario</title>
</head>
<body>
    <%@ include file='navbar.jsp'%>
    <img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg" alt="Logo de la empresa">

    <div class=contacto style="display: flex; justify-content: center;">

        <form action="CrearUsuarioServlet" method="post" onsubmit="return enviarFormulario(event)">
            <h1 class=tituloContacto>Formulario de Edicion de Usuario</h1>
            <label for="tipo">Selecciona un Tipo:</label> 
            <select id="tipo" name="tipo" onchange="mostrarCamposAdicionales()">
                <option value="Seleccione">Seleccione</option>
                <option value="Cliente">Cliente</option>
                <option value="Profesional">Profesional</option>
                <option value="Administrativo">Administrativo</option>
            </select> 
            <br>
            <br>
            
            <label for="nombre">Ingrese el nombre de usuario:</label><br>
            <input type="text" id="nombre" name="nombre"><br>
            <br> 
            <label for="fechaDeNacimiento">Ingrese fecha de nacimiento:</label><br> 
            <input type="text" id="fechaDeNacimiento" name="fechaDeNacimiento"><br> 
            <br> 
            <label for="run">Ingrese run:</label><br>
            <input type="text" id="run" name="run"><br>
            <br> 


            <div id="camposCliente" style="display: none;">
                <label for="rut">RUT:</label><br>
                <input type="text" id="rut" name="rut"><br>
                <br>
                <label for="nombres">Nombres:</label><br>
                <input type="text" id="nombres" name="nombres"><br>
                <br>
                <label for="apellidos">Apellidos:</label><br>
                <input type="text" id="apellidos" name="apellidos"><br>
                <br>
                <label for="telefono">Teléfono:</label><br>
                <input type="text" id="telefono" name="telefono"><br>
                <br>
                <label for="afp">AFP:</label><br>
                <input type="text" id="afp" name="afp"><br>
                <br>
                <label for="sistemaSalud">Sistema de salud:</label><br>
                <select id="sistemaSalud" name="sistemaSalud">
                	<option value="Seleccione">Seleccione</option>
                    <option value="1">Fonasa</option>
                    <option value="2">Isapre</option>
                </select>
                <br>
                <br>
                <label for="direccion">Dirección:</label><br>
                <input type="text" id="direccion" name="direccion"><br>
                <br>
                <label for="comuna">Comuna:</label><br>
                <input type="text" id="comuna" name="comuna"><br>
                <br>
                <label for="edad">Edad:</label><br>
                <input type="text" id="edad" name="edad"><br>
                <br>
            </div>

            <div id="camposProfesional" style="display: none;">
                <label for="titulo">Título:</label><br>
                <input type="text" id="titulo" name="titulo"><br>
                <br>
                <label for="fechaIngreso">Fecha de ingreso:</label><br>
                <input type="text" id="fechaIngreso" name="fechaIngreso"><br>
                <br>
            </div>

            <div id="camposAdministrativo" style="display: none;">
                <label for="area">Área:</label><br>
                <input type="text" id="area" name="area"><br>
                <br>
                <label for="experiencia">Experiencia previa:</label><br>
                <input type="text" id="experiencia" name="experiencia"><br>
                <br>
            </div>

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

        function mostrarCamposAdicionales() {
            var tipoSeleccionado = document.getElementById("tipo").value;
            var camposCliente = document.getElementById("camposCliente");
            var camposProfesional = document.getElementById("camposProfesional");
            var camposAdministrativo = document.getElementById("camposAdministrativo");

            camposCliente.style.display = "none";
            camposProfesional.style.display = "none";
            camposAdministrativo.style.display = "none";

            if (tipoSeleccionado === "Cliente") {
                camposCliente.style.display = "block";
            } else if (tipoSeleccionado === "Profesional") {
                camposProfesional.style.display = "block";
            } else if (tipoSeleccionado === "Administrativo") {
                camposAdministrativo.style.display = "block";
            }
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <%@ include file='footer.jsp'%>
</body>
</html>