package modelo;

public class Administrativo extends Usuario{
	//se crean los atributos de la clase Adiministrativo
	private String area;
	private String experienciaPrevia;
	 //se crea el constructor con todos los atributos de la clase
	public Administrativo() {
		
	}
	
	 public Administrativo(int id, String nombre, String fechaDeNacimiento, int run, String tipo, String area, String experienciaPrevia) {
	        super(id, nombre, fechaDeNacimiento, run, tipo);
	        this.area = area;
	        this.experienciaPrevia = experienciaPrevia;
	    }
	//se crean los metodos de acceso y modificadores de yodos los atributos
	public String getArea() {
		return area;
	}

	public boolean setArea(String area) {
		this.area = area;
		if (area.length() > 5 && area.length() < 30) {
	        return true; 
	    } else {
	        return false; 
	    }
	}

	public String getExperienciaPrevia() {
		return experienciaPrevia;
	}

	public boolean setExperienciaPrevia(String experienciaPrevia) {
		this.experienciaPrevia = experienciaPrevia;
		if (experienciaPrevia.length() < 100) {
	    	this.experienciaPrevia = experienciaPrevia;
	        return true; 
	    } else {
	        return false; 
	    }
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
