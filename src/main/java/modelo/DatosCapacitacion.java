package modelo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DatosCapacitacion {

	// se crean los atributos de la clase Capacitacion
	private int identificador;
	private int rutCliente;
	private String dia;
	private String hora;
	private String lugar;
	private String duracion;
	private int cantidadAsistentes;
	private String nombre;
	private String detalle;

	// se crea el constructor con todos los atributos de la clase
	public DatosCapacitacion() {

	}

	public DatosCapacitacion(int identificador, int rutCliente, String dia, String hora, String lugar, String duracion, int cantidadAsistentes, String nombre, String detalle) {
		super();
		
		this.identificador = identificador;
		this.rutCliente = rutCliente;
		this.dia = dia;
		this.hora = hora;
		this.lugar = lugar;
		this.duracion = duracion;
		this.cantidadAsistentes = cantidadAsistentes;
		this.nombre = nombre;
		this.detalle= detalle;
	
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

	public String getDetalle() {
		return detalle;
	}

	public boolean setDetalle(String detalle) {
	    this.detalle = detalle;
	    if (detalle != null && !detalle.isEmpty()) {
	        this.detalle = detalle;
	        return true; // El nombre se estableció correctamente
	    } else {
	        return false; // El nombre es inválido
	    }
	}
	
	
	   

	// se crean los metodos de acceso y modificadores de yodos los atributos
	public int getIdentificador() {
		return identificador;
	}

	public boolean setIdentificador(int identificador) {
	    String identificadorString = Integer.toString(identificador);

	    if (identificadorString != null && !identificadorString.isEmpty()) {
	        try {
	            int id = Integer.parseInt(identificadorString);
	            this.identificador = id;
	            return true; // Indicar que el valor se estableció correctamente
	        } catch (NumberFormatException e) {
	            return false; 
	        }
	    } else {
	        return false;
	    }
	}

	public int getRutCliente() {
		return rutCliente;
	}

	
	public boolean setRutCliente(int rutCliente) {
		  
		    if (rutCliente < 99999999) {
		    	this.rutCliente = rutCliente;
		    	return true; 
		    
		    } else {
		       
		        return false; 
		    }
		}

		
	public String getDia() {
		return dia;
	}
	public boolean setDia(String dia) {
	    if (dia != null && !dia.isEmpty()) {
	        if (dia.equalsIgnoreCase("lunes") || dia.equalsIgnoreCase("martes") || dia.equalsIgnoreCase("miércoles")
	                || dia.equalsIgnoreCase("jueves") || dia.equalsIgnoreCase("viernes") || dia.equalsIgnoreCase("sábado")
	                || dia.equalsIgnoreCase("domingo")) {
	            return true; // El día es válido
	        }
	    }
	    return false; // El día es inválido o está vacío
	}



	public String getHora() {
		return hora;

	}
		
	
 public boolean setHora(String hora) {
	    this.hora = hora;
	    if (hora.length() == 5) {
	        String dosPrimerosCaracteres = hora.substring(0, 2);
	        String tercerCaracter = Character.toString(hora.charAt(2));
	        String ultimosCaracteres = hora.substring(3, 5);

	        // comparo
	        int numero = Integer.parseInt(dosPrimerosCaracteres);
	        int numero2 = Integer.parseInt(ultimosCaracteres);

	        if (numero < 24 && tercerCaracter.equals(":") && numero2 < 60) {
	            return true; // La hora es válida
	        }
	    }
	    return false; // La hora es inválida
	}

	

	public String getLugar() {
		return lugar;
	}

	
	public boolean setLugar(String lugar) {
		this.lugar = lugar;
		if (lugar.length() > 9 && lugar.length() < 51) {
	        return true; 
	    } else {
	        return false; 
	    }
	}

	public String getDuracion() {
		return duracion;
	}

	
	public boolean setDuracion(String duracion) {
		this.duracion = duracion;
		if (duracion.length() < 71) {
	    	this.duracion = duracion;
	        return true; 
	    } else {
	        return false; 
	    }
	}
	

	public int getCantidadAsistentes() {
		return cantidadAsistentes;
	}


	public boolean setCantidadAsistentes(int cantidadAsistentes) {
	    String cantidadString = Integer.toString(cantidadAsistentes);

	    if (cantidadAsistentes < 1000) { 
	        try {
	            int cantidad = Integer.parseInt(cantidadString);
	            this.cantidadAsistentes = cantidad;
	            return true; 
	        } catch (NumberFormatException e) {
	            return false; 
	        }
	    } else {
	       
	        return false; 
	    }
	}
	
	

	// se crea metodo toString

	public String toString() {
		return "Capacitacion [identificador=" + identificador + ", rutCliente=" + rutCliente + ", dia=" + dia
				+ ", hora=" + hora + ", lugar=" + lugar + ", duracion=" + duracion + ", cantidadAsistentes="
				+ cantidadAsistentes + "]";
	}

	public void mostrarDetalle() {
		System.out.println("La capacitación será en " + lugar + " a las " + hora + " del día " + dia + ", y durará "
				+ duracion + " minutos.");
	}

}
