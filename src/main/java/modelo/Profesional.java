package modelo;

public class Profesional extends Usuario {
	//se crean los atributos de la clase Profesional 
	private String titulo;
	private String fechaDeIngreso;
	 //se crea el constructor con todos los atributos de la clase
	public Profesional() {
		
	}
	public Profesional(String titulo, String fechaDeIngreso) {
		super();
		this.titulo = titulo;
		this.fechaDeIngreso = fechaDeIngreso;
	}
	//se crean los metodos de acceso y modificadores de yodos los atributos
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	public void setFechaDeIngreso(String fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
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
