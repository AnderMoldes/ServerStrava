package Servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.RetoDAO;
import DAO.UsuarioDAO;
import Dominio.Retos;
import Dominio.Usuarios;


public class RetoAppService {
	private static RetoAppService instance;
	public static RetoAppService getInstance() {
		if (instance == null) {
			instance = new RetoAppService();
		}
		
		return instance;
	}
	public ArrayList<Retos> getReto() {
		//TODO: Get User using DAO and check 		
		return RetoDAO.getInstance().getAll();
	}

	public Retos crearReto(int number, String name, Date fecha_ini, Date fecha_fin, double distanciaObjetivo,
			String deporte) {
		// TODO Auto-generated method stub
		Retos reto = new Retos();
		if(RetoDAO.getInstance().find(name)!= null) {
			System.out.println("Reto is already in the database.");
			return null;
		}else {
			reto.setDeporte(deporte);
			reto.setDistanciaObjetivo(distanciaObjetivo);
			reto.setFecha_fin(fecha_fin);
			reto.setFecha_inicio(fecha_ini);
			reto.setName(name);
			reto.setNumber(number);
			RetoDAO.getInstance().save(reto);
			return reto;
		}
	}
}
