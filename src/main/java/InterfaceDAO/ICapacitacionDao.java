package InterfaceDAO;

import java.util.List;

import modelo.DatosCapacitacion;

public interface ICapacitacionDao {
	void registrarCapacitacion(DatosCapacitacion capacitacion);
	void modificarCapacitacion(int id);
	
	//	void eliminarCapacitacion(int id);
	List<DatosCapacitacion> obtenerListaCapacitaciones();
}
