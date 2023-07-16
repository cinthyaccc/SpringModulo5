<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #008000;">
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav ml-auto">
        
        
        <% if (request.getAttribute("mostrarFuncionalidadAdministrativo") == null) { %>
        <li class="nav-item active">
                    <a class="nav-item nav-link active" href="InicioServlet">Inicio<span class="sr-only"></span></a>
                </li>
         <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Salir</a>
                </li>
        <% } %>
            <% if (request.getAttribute("mostrarFuncionalidadCliente") != null
                    && (boolean) request.getAttribute("mostrarFuncionalidadCliente")) { %>
                <li class="nav-item active">
                    <a class="nav-item nav-link active" href="InicioServlet">Inicio<span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="CrearCapacitacionServlet">Crear Capacitacion</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ListarCapacitacionServlet">Listar Capacitaciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ContactoServlet">Contacto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Salir</a>
                </li>
            <% } %>

            <% if (request.getAttribute("mostrarFuncionalidadAdministrativo") != null
                    && (boolean) request.getAttribute("mostrarFuncionalidadAdministrativo")) { %>
                <li class="nav-item active">
                    <a class="nav-item nav-link active" href="InicioServlet">Inicio<span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="CrearUsuarioServlet">Crear Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ListadoDeUsuariosServlet">Listado de Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Salir</a>
                </li>
            <% } %>

            <% if (request.getAttribute("mostrarFuncionalidadProfesional") != null
                    && (boolean) request.getAttribute("mostrarFuncionalidadProfesional")) { %>
                <li class="nav-item active">
                    <a class="nav-item nav-link active" href="InicioServlet">Inicio<span class="sr-only"></span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet">Salir</a>
                </li>
            <% } %>
        </ul>
    </div>
</nav>
