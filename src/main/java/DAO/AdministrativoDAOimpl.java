package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import InterfaceDAO.IAdministrativoDao;
import modelo.Administrativo;

public class AdministrativoDAOimpl implements IAdministrativoDao{
	private List<Administrativo> administrativo = new ArrayList<Administrativo>();
	Scanner scanner = new Scanner(System.in);
		
	@Override
	public List<Administrativo> listarAdministrativo() {
		// TODO Auto-generated method stub
		return administrativo;
	}

	@Override
	public void registrarAdministrativo(Administrativo administrativo1) {
		// TODO Auto-generated method stub
		administrativo.add(administrativo1);
	}

}
