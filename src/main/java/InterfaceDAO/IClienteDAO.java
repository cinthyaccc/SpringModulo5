package InterfaceDAO;

import java.util.List;

import modelo.Cliente;

public interface IClienteDAO {
	public List<Cliente>listarCliente();
	public void registrarCliente(Cliente cliente);

}
