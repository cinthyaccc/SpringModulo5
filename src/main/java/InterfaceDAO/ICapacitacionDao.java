package InterfaceDAO;

import java.util.List;

import modelo.DatosCapacitacion;

public interface ICapacitacionDao {
	public List<DatosCapacitacion>listarCapacitacion();
	public void registrarCapacitacion(DatosCapacitacion datosCapacitacion1);

}
