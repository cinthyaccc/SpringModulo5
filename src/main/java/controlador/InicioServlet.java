package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/InicioServlet")
public class InicioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InicioServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String tipoUsuario = (String) session.getAttribute("usuario");

  
        
        // Lógica para habilitar/deshabilitar elementos del navbar según el tipo de usuario
        boolean mostrarFuncionalidadAdministrador = tipoUsuario.equals("administrador");
        boolean mostrarFuncionalidadCliente = tipoUsuario.equals("cliente");
        boolean mostrarFuncionalidadProfesional = tipoUsuario.equals("profesional");
        boolean mostrarFuncionalidadAdministrativo = tipoUsuario.equals("administrativo");

        // Guardar los valores en atributos de solicitud para su uso en la página JSP
        request.setAttribute("mostrarFuncionalidadAdministrador", mostrarFuncionalidadAdministrador);
        request.setAttribute("mostrarFuncionalidadCliente", mostrarFuncionalidadCliente);
        request.setAttribute("mostrarFuncionalidadProfesional", mostrarFuncionalidadProfesional);
        request.setAttribute("mostrarFuncionalidadAdministrativo", mostrarFuncionalidadAdministrativo);

        // Redireccionar a la página de inicio correspondiente
        request.getRequestDispatcher("/views/inicio.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
