<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Usuarios</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<script	src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.all.min.js"></script>

<script>
  function mostrarCamposAdicionales() {
    var tipo = document.getElementById("tipo").value;
    var clienteTabla = document.getElementById("clienteTabla");
    var profesionalTabla = document.getElementById("profesionalTabla");
    var administrativoTabla = document.getElementById("administrativoTabla");

    // Oculta todas las tablas al principio
    clienteTabla.style.display = "none";
    profesionalTabla.style.display = "none";
    administrativoTabla.style.display = "none";

    // Muestra la tabla correspondiente según el tipo seleccionado
    if (tipo === "Cliente") {
      clienteTabla.style.display = "table";
    } else if (tipo === "Profesional") {
      profesionalTabla.style.display = "table";
    } else if (tipo === "Administrativo") {
      administrativoTabla.style.display = "table";
    }
  }
</script>

</head>
<body>
<%@ include file='navbar.jsp'%>
<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg" alt="Logo de la empresa">

<div class="contacto">
  <h1>Listar Usuarios</h1>

  <form action="CrearUsuarioServlet" method="post" onsubmit="return enviarFormulario(event)">
    <label for="tipo">Selecciona un Tipo:</label> 
    <select id="tipo" name="tipo" onchange="mostrarCamposAdicionales()">
      <option value="Seleccione">Seleccione</option>
      <option value="Cliente">Cliente</option>
      <option value="Profesional">Profesional</option>
      <option value="Administrativo">Administrativo</option>
    </select> 
    <br>
    <br>

    <% String tipoSeleccionado = request.getParameter("tipo"); %>

    <% if (tipoSeleccionado != null && !tipoSeleccionado.isEmpty()) { %>
    <% if (tipoSeleccionado.equals("Cliente")) { %>
    <table id="clienteTabla" class="tabla-usuario">
      <!-- Encabezado de la tabla -->
      <thead>
        <tr>
          <th>Nombre usuario</th>
          <th>Fecha de nacimiento</th>
          <th>RUN</th>
          <th>RUT</th>
          <th>Nombres</th>
          <th>Apellidos</th>
          <th>Teléfono</th>
          <th>AFP</th>
          <th>Sistema de salud</th>
          <th>Dirección</th>
          <th>Comuna</th>
          <th>Edad</th>
        </tr>
      </thead>
      <!-- Cuerpo de la tabla -->
      <tbody>
        <% 
          // Recorre la lista de clientes y muestra los datos en la tabla
          ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("u");
          for (Cliente cliente : clientes) {
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
        </tr>
        <% } %>
      </tbody>
    </table>
    <% } else if (tipoSeleccionado.equals("Profesional")) { %>
    <table id="profesionalTabla" class="tabla-usuario">
      <!-- Encabezado de la tabla -->
      <thead>
        <tr>
          <th>Nombre usuario</th>
          <th>Fecha de nacimiento</th>
          <th>RUN</th>
          <th>Título</th>
          <th>Fecha de ingreso</th>
        </tr>
      </thead>
      <!-- Cuerpo de la tabla -->
      <tbody>
        <% 
          // Recorre la lista de profesionales y muestra los datos en la tabla
          ArrayList<Profesional> profesionales = (ArrayList<Profesional>) request.getAttribute("u");
          for (Profesional profesional : profesionales) {
        %>
        <tr>
          <td><%= profesional.getNombre() %></td>
          <td><%= profesional.getFechaDeNacimiento() %></td>
          <td><%= profesional.getRun() %></td>
          <td><%= profesional.getTitulo() %></td>
          <td><%= profesional.getFechaDeIngreso() %></td>
        </tr>
        <% } %>
      </tbody>
    </table>
    <% } else if (tipoSeleccionado.equals("Administrativo")) { %>
    <table id="administrativoTabla" class="tabla-usuario">
      <!-- Encabezado de la tabla -->
      <thead>
        <tr>
          <th>Nombre usuario</th>
          <th>Fecha de nacimiento</th>
          <th>RUN</th>
          <th>Área</th>
          <th>Experiencia previa</th>
        </tr>
      </thead>
      <!-- Cuerpo de la tabla -->
      <tbody>
        <% 
          // Recorre la lista de administrativos y muestra los datos en la tabla
          ArrayList<Administrativo> administrativos = (ArrayList<Administrativo>) request.getAttribute("u");
          for (Administrativo administrativo : administrativos) {
        %>
        <tr>
          <td><%= administrativo.getNombre() %></td>
          <td><%= administrativo.getFechaDeNacimiento() %></td>
          <td><%= administrativo.getRun() %></td>
          <td><%= administrativo.getArea() %></td>
          <td><%= administrativo.getExperienciaPrevia() %></td>
        </tr>
        <% } %>
      </tbody>
    </table>
    <% } %>
    <% } %>

    <br>
    <br>
    <input type="submit" value="Enviar" class="boton-enviar">
  </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
	crossorigin="anonymous"></script>

<%@ include file='footer.jsp'%>

</body>
</html>
