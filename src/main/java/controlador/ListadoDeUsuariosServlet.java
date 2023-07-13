@WebServlet("/ListadoDeUsuariosServlet")
public class ListadoDeUsuariosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int contador = 0;

    public ListadoDeUsuariosServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contador = Contador.getContador();
        HttpSession session = request.getSession();
        String nombre = (String) session.getAttribute("nombre");
        String password = (String) session.getAttribute("password");

        if (nombre == null || password == null || !validar(nombre, password)) {
            System.out.println("contador" + contador);
            if (contador > 0) {
                System.out.println("contador error" + contador);
                String mensaje = "clave incorrecta";
                request.setAttribute("mensaje", mensaje);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
            Contador.setContador(1);
            System.out.println("contador incrementar: " + contador);
            dispatcher.forward(request, response);

        } else {
            Contador.setContador(0);
            HttpSession sesion = request.getSession();
            sesion.setAttribute("nombre", nombre);
            sesion.setAttribute("password", password);

            // Obtener el valor del atributo de sesión
            String tipoU = (String) session.getAttribute("tipoU");
            System.out.println("tipo de usuario: " + tipoU);
            if (tipoU == null || tipoU.isEmpty()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/listadoDeUsuarios.jsp");
                dispatcher.forward(request, response);
            } else {
                // Obtener la conexión a la base de datos
                Connection conn = Conexion.getConn();

                try {
                    // Crear una declaración SQL
                    java.sql.Statement statement = conn.createStatement();

                    // verificar tipo de usuario
                    if (tipoU.equals("Administrativo")) {
                        // Ejecutar la consulta SQL
                        String sql = "SELECT id, nombre, tipo, fechaNacimiento, run, area, experienciaPrevia FROM usuarios";
                        ResultSet resultSet = statement.executeQuery(sql);
                        // Crear una lista para almacenar los usuarios
                        List<Administrativo> administrativos = new ArrayList<>();
                        // Recorrer los resultados de la consulta
                        while (resultSet.next()) {
                            // Obtener los valores de cada columna
                            int id = resultSet.getInt("id");
                            int run = resultSet.getInt("run");
                            String nombreU = resultSet.getString("nombre");
                            String fechaDeNacimiento = resultSet.getString("fechaNacimiento");
                            String tipo = resultSet.getString("tipo");
                            String area = resultSet.getString("area");
                            String experienciaPrevia = resultSet.getString("experienciaPrevia");

                            // Crear un objeto Administrativo y agregarlo a la lista
                            Administrativo administrativo = new Administrativo(nombreU, fechaDeNacimiento, run,
                                    area, experienciaPrevia);
                            administrativos.add(administrativo);
                            System.out.println(administrativos);

                            // Guardar la lista de administrativos en el request
                            request.setAttribute("Administrativos", administrativos);
                        }
                        // Cerrar el ResultSet y la declaración
                        resultSet.close();
                        statement.close();
                    }

                    if (tipoU.equals("Cliente")) {
                        // Ejecutar la consulta SQL
                        String sqlC = "SELECT id, nombre, tipo, fechaNacimiento, run, rut, nombres, apellidos, telefono, afp, sistemaDeSalud, direccion, comuna, edad FROM usuarios";
                        ResultSet resultSetC = statement.executeQuery(sqlC);
                        // Crear una lista para almacenar los usuarios
                        List<Cliente> clientes = new ArrayList<>();
                        // Recorrer los resultados de la consulta
                        while (resultSetC.next()) {
                            // Obtener los valores de cada columna
                            int id = resultSetC.getInt("id");
                            int run = resultSetC.getInt("run");
                            String nombreU = resultSetC.getString("nombre");
                            String fechaDeNacimiento = resultSetC.getString("fechaNacimiento");
                            int rut = resultSetC.getInt("rut");
                            String nombres = resultSetC.getString("nombres");
                            String apellidos = resultSetC.getString("apellidos");
                            int telefono = resultSetC.getInt("telefono");
                            String afp = resultSetC.getString("afp");
                            String sistemaDeSalud = resultSetC.getString("sistemaDeSalud");
                            String direccion = resultSetC.getString("direccion");
                            String comuna = resultSetC.getString("comuna");
                            int edad = resultSetC.getInt("edad");

                            // Crear un objeto Cliente y agregarlo a la lista
                            Cliente cliente = new Cliente(nombreU, fechaDeNacimiento, run, rut, nombres, apellidos,
                                    telefono, afp, sistemaDeSalud, direccion, comuna, edad);
                            clientes.add(cliente);
                            System.out.println(clientes);

                            // Guardar la lista de clientes en el request
                            request.setAttribute("Clientes", clientes);
                        }
                        // Cerrar el ResultSet y la declaración
                        resultSetC.close();
                        statement.close();
                    }
                    if (tipoU.equals("Profesional")) {
                        // Ejecutar la consulta SQL
                        String sqlP = "SELECT id, nombre, tipo, fechaNacimiento, run, titulo, fechaIngreso FROM usuarios";
                        ResultSet resultSetP = statement.executeQuery(sqlP);
                        // Crear una lista para almacenar los usuarios
                        List<Profesional> profesionales = new ArrayList<>();
                        // Recorrer los resultados de la consulta
                        while (resultSetP.next()) {
                            // Obtener los valores de cada columna
                            int id = resultSetP.getInt("id");
                            int run = resultSetP.getInt("run");
                            String nombreU = resultSetP.getString("nombre");
                            String fechaDeNacimiento = resultSetP.getString("fechaNacimiento");
                            String titulo = resultSetP.getString("titulo");
                            String fechaIngreso = resultSetP.getString("fechaIngreso");

                            // Crear un objeto Profesional y agregarlo a la lista
                            Profesional profesional = new Profesional(nombreU, fechaDeNacimiento, run, titulo,
                                    fechaIngreso);
                            profesionales.add(profesional);
