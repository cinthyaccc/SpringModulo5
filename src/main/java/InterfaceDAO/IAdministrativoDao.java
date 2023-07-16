package InterfaceDAO;

import java.util.List;

import modelo.Administrativo;

public interface IAdministrativoDao {
	void registrarAdministrativo(Administrativo administrativo);
	void modificarAdministrativo(Administrativo administrativo);
	Administrativo obtenerAdministrativo(int id);
	//	void eliminarAdministrativo(Administrativo administrativo);
	List<Administrativo> obtenerListaAdministrativos();
}
