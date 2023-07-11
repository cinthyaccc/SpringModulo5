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
public Cliente(int run, int rut, String nombres, String apellidos, int telefono, String afp, String sistemaDeSalud,
		String direccion, String comuna, int edad) {
	super(run);
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

public void setRut(int rut) {
	this.rut = rut;
}

public String getNombres() {
	return nombres;
}

public void setNombres(String nombres) {
	this.nombres = nombres;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public int getTelefono() {
	return telefono;
}

public void setTelefono(int telefono) {
	this.telefono = telefono;
}

public String getAfp() {
	return afp;
}

public void setAfp(String afp) {
	this.afp = afp;
}

public String getSistemaDeSalud() {
	return sistemaDeSalud;
}

public void setSistemaDeSalud(String sistemaDeSalud) {
	this.sistemaDeSalud = sistemaDeSalud;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getComuna() {
	return comuna;
}

public void setComuna(String comuna) {
	this.comuna = comuna;
}

public int getEdad() {
	return edad;
}

public void setEdad(int edad) {
	this.edad = edad;
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
