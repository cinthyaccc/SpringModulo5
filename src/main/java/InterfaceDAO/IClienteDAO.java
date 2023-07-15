package InterfaceDAO;

import java.util.List;
import modelo.Cliente;

public interface IClienteDAO {
	void registrarCliente(Cliente cliente);
	void modificarCliente(Cliente cliente);
//	Cliente obtenerCliente(int id);
	//	void eliminarCliente(Cliente cliente);
	List<Cliente> obtenerListaClientes();
}
