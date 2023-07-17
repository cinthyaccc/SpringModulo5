package modelo;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesional extends Usuario {
	//se crean los atributos de la clase Profesional 
	private String titulo;
	private String fechaDeIngreso;
	static Pattern patronFecha = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
	static Pattern patronHora = Pattern.compile("\\d{2}:\\d{2}");
	 //se crea el constructor con todos los atributos de la clase
	public Profesional() {
		
	}
	public Profesional(int id, String nombre, String fechaDeNacimiento, int run, String tipo, String titulo, String fechaDeIngreso) {
		super(id, nombre, fechaDeNacimiento, run, tipo);
		this.titulo = titulo;
		this.fechaDeIngreso = fechaDeIngreso;
	}
	//se crean los metodos de acceso y modificadores de yodos los atributos
	public String getTitulo() {
		return titulo;
	}
	public boolean setTitulo(String titulo) {
		this.titulo = titulo;
		if (titulo.length() > 5 && titulo.length() < 30) {
	        return true; 
	    } else {
	        return false; 
	    }
	}
	public String getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	public boolean setFechaDeIngreso(String fechaDeIngreso) {
		Matcher matcher = patronFecha.matcher(fechaDeIngreso);

		if (!matcher.matches()) {
			return false;
		} else {
			int diaFecha = Integer.parseInt(fechaDeIngreso.substring(0, 2));
			int mesFecha = Integer.parseInt(fechaDeIngreso.substring(3, 5));
			int añoFecha = Integer.parseInt(fechaDeIngreso.substring(6, 10));

			if (diaFecha < 1 || diaFecha > LocalDate.of(añoFecha, mesFecha, 1).lengthOfMonth() || mesFecha < 1
					|| mesFecha > 12) {
			return false;
			}
			this.fechaDeIngreso = fechaDeIngreso;
			return true;
		}
		
	}
	//se crea metodo toString

	public String toString() {
		return super.toString() + "Profesional [titulo=" + titulo + ", fechaDeIngreso=" + fechaDeIngreso + "]";
	}
	
	public void analizarUsuario() {
        super.analizarUsuario();
        System.out.println("Tipo de usuario: Profesional");
        // Mostrar los datos específicos del profesional
        System.out.println("Título: " + titulo);
        System.out.println("Fecha de ingreso: " + fechaDeIngreso);
    }
	
	
}
