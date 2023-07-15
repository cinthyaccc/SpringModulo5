package InterfaceDAO;

import java.util.List;


import modelo.Usuario;

public interface IUsuarioDAO {
	public List<Usuario>listarUsuario();
	public void registrarUsuario(Usuario usuario);

}
