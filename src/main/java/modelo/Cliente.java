package modelo;

public class Cliente extends Usuario {
	//se crean los atributos de la clase Cliente
private int rut;
private String nombres;
private String apellidos;
private int telefono;
private String afp;
private String sistemaDeSalud;
private String direccion;
private String comuna;
private int edad;

public Cliente() {

}
//se crea el constructor con todos los atributos de la clase
public Cliente(int id, String nombre, String fechaDeNacimiento, int run, String tipo, int rut, String nombres, String apellidos, int telefono, String afp, String sistemaDeSalud,
		String direccion, String comuna, int edad) {
	super(id, nombre, fechaDeNacimiento, run, tipo);
	this.rut = rut;
	this.nombres = nombres;
	this.apellidos = apellidos;
	this.telefono = telefono;
	this.afp = afp;
	this.sistemaDeSalud = sistemaDeSalud;
	this.direccion = direccion;
	this.comuna = comuna;
	this.edad = edad;
}
//se crean los metodos de acceso y modificadores de yodos los atributos
public int getRut() {
	return rut;
}

public boolean setRut(String rutStr) {

	if (rutStr != null && !rutStr.isEmpty()) {
		for (char c : rutStr.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false; // El número contiene caracteres no numéricos
			}
		}
		int rut = Integer.parseInt(rutStr);
		if (rut < 99999999) {
			// Haces cualquier otra validación adicional que necesites
			this.rut = rut;
			return true; // El número entero se estableció correctamente
		} else {
			return false;
		}
	} else {
		return false; // El número es inválido o está vacío
	}
}

public String getNombres() {
	return nombres;
}

public boolean setNombres(String nombres) {
    if (nombres.length()>4 && nombres.length()<31) {
    	this.nombres = nombres;
        return true; // El nombre se estableció correctamente
    } else {
        return false; // El nombre es inválido
    }
}

public String getApellidos() {
	return apellidos;
}

public boolean setApellidos(String apellidos) {
    if (apellidos.length()>4 && apellidos.length()<31) {
    	this.apellidos = apellidos;
        return true; // El apellido se estableció correctamente
    } else {
        return false; // El apellido es inválido
    }
}
public int getTelefono() {
	return telefono;
}
public boolean setTelefono(String telefonoStr) {
    if (telefonoStr != null && !telefonoStr.isEmpty()) {
        for (char c : telefonoStr.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // El número contiene caracteres no numéricos
            }
        }
        int numero = Integer.parseInt(telefonoStr);
        // Haces cualquier otra validación adicional que necesites
        this.telefono = numero;
        return true; // El número entero se estableció correctamente
    } else {
        return false; // El número es inválido o está vacío
    }
}

public String getAfp() {
	return afp;
}

public boolean setAfp(String afp) {
    if (afp.length()>3 && afp.length()<31) {
    	this.afp = afp;
        return true; // La afp se estableció correctamente
    } else {
        return false; // La afp es inválida
    }
}
public String getSistemaDeSalud() {
	return sistemaDeSalud;
}

public boolean setSistemaDeSalud(String sistemaDeSalud) {
	if (sistemaDeSalud.equals("1") || sistemaDeSalud.equals("2")) {
		String sSalud;
		sSalud = obtenerSistemaSalud(sistemaDeSalud);
		this.sistemaDeSalud = sSalud;
        return true; // La afp se estableció correctamente
    } else {
        return false; // La afp es inválida
    }
}


public String getDireccion() {
	return direccion;
}

public boolean setDireccion(String direccion) {
	if (direccion.length()<71) {
		this.direccion = direccion;
        return true; // La direccion se estableció correctamente
    } else {
        return false; // La direccion es inválida
    }
}

public String getComuna() {
	return comuna;
}

public boolean setComuna(String comuna) {
	if (comuna.length()<51) {
		this.comuna = comuna;
        return true; // La comuna se estableció correctamente
    } else {
        return false; // La comuna es inválida
    }
}
public int getEdad() {
	return edad;
}

public boolean setEdad(String edadStr) {
	if (edadStr != null && !edadStr.isEmpty()) {
		for (char c : edadStr.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false; // El número contiene caracteres no numéricos
			}
		}
		int edad = Integer.parseInt(edadStr);
		if (edad >= 0 && edad < 151) {
			// Haces cualquier otra validación adicional que necesites
			this.edad = edad;
			return true; // El número entero se estableció correctamente
		} else {
			return false;
		}
	} else {
		return false; // El número es inválido o está vacío
	}
}

//se crea metodo toString

public String toString() {
	return super.toString() + " Cliente [rut=" + rut + ", nombres=" + nombres + ", apellidos=" + apellidos + ", telefono=" + telefono
			+ ", afp=" + afp + ", sistemaDeSalud=" + sistemaDeSalud + ", direccion=" + direccion + ", comuna=" + comuna
			+ ", edad=" + edad + "]";
}

public String obtenerNombre() {
    return nombres + " " + apellidos;
}

//creando metodo para obtener el sistema de salud
public String obtenerSistemaSalud(String sistemaDeSalud) {
    if (sistemaDeSalud.equals("1")) {
    	String resultado = "Fonasa";
        return resultado ;
        
    } else if (sistemaDeSalud.equals("2")) {
    	String resultado = "Isapre";
        return resultado;
    } else {
    	String resultado="Desconocido";
        return resultado;
    }
}

public void analizarUsuario() {
    super.analizarUsuario();
    System.out.println("Tipo de usuario: Cliente");
    // Mostrar los datos específicos del cliente
    System.out.println("RUT: " + rut);
    System.out.println("Nombres: " + nombres);
    System.out.println("Apellidos: " + apellidos);
    System.out.println("Teléfono: " + telefono);
    System.out.println("AFP: " + afp);
    System.out.println("Sistema de salud: " + sistemaDeSalud);
    System.out.println("Dirección: " + direccion);
    System.out.println("Comuna: " + comuna);
    System.out.println("Edad: " + edad);
}
	
}
