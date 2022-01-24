package Servicios;

import java.util.Date;

import Dominio.Retos;


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
}
