package modelo;

public class Administrativo extends Usuario{
	//se crean los atributos de la clase Adiministrativo
	private String area;
	private String experienciaPrevia;
	 //se crea el constructor con todos los atributos de la clase
	public Administrativo() {
		
	}
	
	public Administrativo(String nombre, String area, String experienciaPrevia) {
		super(nombre);
		this.area = area;
		this.experienciaPrevia = experienciaPrevia;
	}
	//se crean los metodos de acceso y modificadores de yodos los atributos
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getExperienciaPrevia() {
		return experienciaPrevia;
	}

	public void setExperienciaPrevia(String experienciaPrevia) {
		this.experienciaPrevia = experienciaPrevia;
	}

	//se crea metodo toString

	public String toString() {
		return super.toString() + "Adiministrativo [area=" + area + ", experienciaPrevia=" + experienciaPrevia + "]";
	}
	
	public void analizarUsuario() {
		super.analizarUsuario();
       
        System.out.println("Tipo de usuario: Administrativo");
        // Mostrar los datos específicos del administrativo
        System.out.println("Area: " + area);
        System.out.println("Experiencia Previa: " + experienciaPrevia);
    }
	
	
}
