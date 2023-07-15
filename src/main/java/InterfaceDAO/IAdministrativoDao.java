package InterfaceDAO;

import java.util.List;

import modelo.Administrativo;


public interface IAdministrativoDao {
	public List<Administrativo>listarAdministrativo();
	public void registrarAdministrativo(Administrativo administrativo);


}
