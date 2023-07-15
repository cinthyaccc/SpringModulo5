package InterfaceDAO;

import java.util.List;

import modelo.Profesional;

public interface IProfesionalDao {
	void registrarProfesional(Profesional profesional);
	void modificarProfesional(Profesional profesional);
	//	Profesional obtenerProfesional(int id);
	//	void eliminarProfesional(Profesional profesional);
	List<Profesional> obtenerListaProfesionales();
}
