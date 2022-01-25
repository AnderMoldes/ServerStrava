package Servicios;

import java.util.Date;

import DAO.RetoDAO;
import DAO.UsuarioDAO;
import Dominio.Retos;
import Dominio.Usuarios;


public class RetoAppService {
	public Retos getReto() {
		//TODO: Get User using DAO and check 		
		Retos reto = new Retos();
		reto.setDeporte("Correr");
		reto.setDistanciaObjetivo(100);
		reto.setFecha_inicio(new Date(2021-12-11));
		reto.setFecha_fin(new Date(2022-01-11));
		reto.setName("100 km");
		reto.setNumber(1);
		return reto;
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
