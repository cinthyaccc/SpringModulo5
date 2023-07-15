package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import InterfaceDAO.IClienteDAO;
import modelo.Cliente;

public class ClienteDAOImpl implements IClienteDAO {
	private List<Cliente> cliente = new ArrayList<Cliente>();
	Scanner scanner = new Scanner(System.in);
		
	@Override
	public List<Cliente> listarCliente() {
		// TODO Auto-generated method stub
		return cliente;
	}

	@Override
	public void registrarCliente(Cliente cliente1) {
		// TODO Auto-generated method stub
		cliente.add(cliente1);
	}

}
