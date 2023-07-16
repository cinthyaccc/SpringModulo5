<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.DatosCapacitacion" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de capacitaciones</title>
      <link rel="stylesheet" type="text/css" href="/GrupalM5/css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <%@ include file='navbar.jsp' %>
    <div class="container">
       
        	<img class="imagen-zoom" src="/GrupalM5/resources/logoGrupo5.jpg"
		alt="Logo de la empresa">
        <section>
            <div class="text-center">
                <h1>Capacitaciones</h1>
            </div>

            <%
            List<DatosCapacitacion> capacitaciones = (List<DatosCapacitacion>) request.getAttribute("capacitaciones");
            if (capacitaciones != null && !capacitaciones.isEmpty()) {
            %>
            <table class="table">
                <thead class="table-dark">
                    <tr>
                        <th>Id Capacitación</th>
                        <th>Nombre de la Capacitación</th>
                        <th>Detalle</th>
                        <th>Rut Cliente</th>
                        <th>Día de la Capacitación</th>
                        <th>Hora de la Capacitación</th>
                        <th>Lugar de la Capacitación</th>
                        <th>Duración de la Capacitación</th>
                        <th>Cantidad de Asistentes</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${capacitaciones}" var="capacitacion">
                        <tr>
                            <td>${capacitacion.getIdentificador()}</td>
                            <td>${capacitacion.getNombre()}</td>
                            <td>${capacitacion.getDetalle()}</td>
                            <td>${capacitacion.getRutCliente()}</td>
                            <td>${capacitacion.getDia()}</td>
                            <td>${capacitacion.getHora()}</td>
                            <td>${capacitacion.getLugar()}</td>
                            <td>${capacitacion.getDuracion()}</td>
                            <td>${capacitacion.getCantidadAsistentes()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <%
            } else {
                String mensaje = (String) request.getAttribute("mensaje");
            %>
            <p><%= mensaje %></p>
            <%
            }
            %>
        </section>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file='footer.jsp'%>
</body>
</html>