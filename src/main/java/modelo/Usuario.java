package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Usuario implements IUsuarios {
	// se crean los atributos de la clase Usuario
	private int id;
	private String nombre;
	private String fechaDeNacimiento;
	private int run;
	// private int id;
	private String tipo;

	// se crea el constructor vacio
	public Usuario() {
	}

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public Usuario(int run) {
		this.run = run;
	}

	// se crea el constructor con todos los atributos de la clase
	public Usuario(int id, String nombre, String fechaDeNacimiento, int run, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombre = nombre;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.run = run;
	}

	// se crean los metodos de acceso y modificadores de yodos los atributos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;

	}

	public boolean setNombre(String nombre) {
		this.nombre = nombre;
		if (nombre != null && !nombre.isEmpty()) {
			return true; // El nombre se estableció correctamente
		} else {
			return false; // El nombre es inválido
		}
	}

	public String getTipo() {
		return tipo;
	}

	public boolean setTipo(String tipo) {
		if (tipo.equals("Cliente") || tipo.equals("Administrativo") || tipo.equals("Profesional")) {
			this.tipo = tipo;
			return true; // El tipo se estableció correctamente
		} else {
			return false; // El tipo es inválido
		}
	}

	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public boolean setFechaDeNacimiento(String fechaDeNacimiento) {
	    if (fechaDeNacimiento == null) {
	        return false; // La fecha es nula, formato incorrecto
	    }

	    String formatoFecha = "dd/MM/yyyy";
	    SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
	    sdf.setLenient(false); // Evita la flexibilidad en el análisis de fechas

	    try {
	        sdf.parse(fechaDeNacimiento);
	        this.fechaDeNacimiento = fechaDeNacimiento;
	        return true; // La fecha tiene el formato correcto y fue establecida correctamente
	    } catch (ParseException e) {
	        return false; // La fecha no tiene el formato correcto
	    }
	}

	public int getRun() {
		return run;
	}

	public boolean setRun(int run) {

		if (run < 99999999) {
			this.run = run;
			return true;

		} else {

			return false;
		}
	}

	// se crea metodo toString
	public String toString() {
		return "Usuario [nombre=" + nombre + ", fechaDeNacimiento=" + fechaDeNacimiento + ", run=" + run + "]";
	}
	// falta desplegar rut en analizar usuario

	public void analizarUsuario() {
		System.out.println("Nombre de usuario: " + nombre);
	}

	public void mostrarEdad() {

		LocalDate fechaNac = LocalDate.parse(fechaDeNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		int edad = periodo.getYears();
		System.out.println("El usuario tiene " + edad + " años");
	}
}