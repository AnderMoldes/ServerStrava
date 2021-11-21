package Servicios;

import java.util.Date;

import Dominio.Reto;


public class RetoAppService {
	public Reto getReto(int number, String name, Date fecha_ini, Date fecha_fin, double dist, String deporte) {
		//TODO: Get User using DAO and check 		
		Reto reto = new Reto();
		reto.setDeporte("Correr");
		reto.setDistanciaObjetivo(100);
//		reto.setFecha_inicio(new Date(2021-12-11));
//		reto.setFecha_fin(new Date(2022-01-11));
		reto.setName("100 km");
		reto.setNumber(1);
		return reto;
	}
}
