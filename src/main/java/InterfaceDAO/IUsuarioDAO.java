package InterfaceDAO;

import java.util.List;

import modelo.Usuario;

public interface IUsuarioDAO {
	void registrarUsuario(Usuario usuario);
	void modificarUsuario(int id);
	
	//	void eliminarUsuario(Usuario usuario);
	List<Usuario> obtenerListaUsuarios();
}
