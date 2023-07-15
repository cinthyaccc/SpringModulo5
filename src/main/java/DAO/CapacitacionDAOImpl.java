package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import InterfaceDAO.ICapacitacionDao;
import modelo.DatosCapacitacion;

public class CapacitacionDAOImpl implements ICapacitacionDao {
	private List<DatosCapacitacion> capacitaciones = new ArrayList<DatosCapacitacion>();
	Scanner scanner = new Scanner(System.in);
		
	@Override
	public List<DatosCapacitacion> listarCapacitacion() {
		// TODO Auto-generated method stub
		return capacitaciones;
	}

	@Override
	public void registrarCapacitacion(DatosCapacitacion datosCapacitacion1) {
		// TODO Auto-generated method stub
		capacitaciones.add(datosCapacitacion1);
	}

}
