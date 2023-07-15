package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import InterfaceDAO.IUsuarioDAO;
import modelo.Usuario;

public class UsuarioDAOImpl implements IUsuarioDAO {
	private List<Usuario> usuario = new ArrayList<Usuario>();
	Scanner scanner = new Scanner(System.in);
		
	@Override
	public List<Usuario> listarUsuario() {
		// TODO Auto-generated method stub
		return usuario;
	}

	@Override
	public void registrarUsuario(Usuario usuario1) {
		// TODO Auto-generated method stub
		usuario.add(usuario1);
	}


}
