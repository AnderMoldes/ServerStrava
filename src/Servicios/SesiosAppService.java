package Servicios;

import java.util.Date;

import DAO.RetoDAO;
import DAO.SesionDAO;
import DAO.UsuarioDAO;
import Dominio.Retos;
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

	public Sesiones crearSesion(int number, String titulo, Date fecha_ini, String hora_ini, double distancia,
			String deporte, double duracion) {
		// TODO Auto-generated method stub
		Sesiones sesion = new Sesiones();
		if(SesionDAO.getInstance().find(titulo)!= null) {
			System.out.println("Sesion is already in the database.");
			return null;
		}else {
			sesion.setDeporte(deporte);
			sesion.setDistancia(distancia);
			sesion.setDuracion(duracion);
			sesion.setFecha_inicio(fecha_ini);
			sesion.setHora_inicio(hora_ini);
			sesion.setNumber(number);
			sesion.setTitulo(titulo);
			SesionDAO.getInstance().save(sesion);
			return sesion;
		}
	}
}
