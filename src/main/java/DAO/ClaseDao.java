package DAO;
 
public class ClaseDao {
	    private static InterfaceDAO.ICapacitacionDao daoInstance;

	    public static InterfaceDAO.ICapacitacionDao getCapacitacionDAO() {
	        if (daoInstance == null) {
	            daoInstance = new CapacitacionDAOImpl(); // Crea una nueva instancia de la implementación
	        }
	        return daoInstance;
	    }
	    private static InterfaceDAO.IUsuarioDAO daoInstanceU;
	    public static InterfaceDAO.IUsuarioDAO getUsuarioDAO() {
	        if (daoInstanceU == null) {
	            daoInstanceU = new UsuarioDAOImpl(); // Crea una nueva instancia de la implementación
	        }
	        return daoInstanceU;
	    }
	
}
