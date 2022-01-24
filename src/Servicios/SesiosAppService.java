package Servicios;

import java.util.Date;

import Dominio.Sesiones;
import Dominio.Usuarios;

public class SesiosAppService {
	public Sesiones getSesion() {
		//TODO: Get User using DAO and check 		
		Sesiones sesion = new Sesiones();
		sesion.setDeporte("Ciclismo");
		sesion.setDistancia(30);
		sesion.setDuracion(1.5);
		sesion.setFecha_inicio(new Date(2021-10-19));
		sesion.setHora_inicio("9:00");
		sesion.setNumber(1);
		//sesion.setPropietario();
		sesion.setTitulo("Ciclismo 30 km");
		return sesion;
	}
}
