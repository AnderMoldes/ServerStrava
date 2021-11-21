package Servicios;

import java.util.Date;

import Dominio.Sesion;
import Dominio.Usuario;

public class SesiosAppService {
	public Sesion getSesion() {
		//TODO: Get User using DAO and check 		
		Sesion sesion = new Sesion();
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
