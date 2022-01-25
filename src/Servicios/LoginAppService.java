package Servicios;

import java.util.Date;

import DAO.UsuarioDAO;
import Dominio.Usuarios;
import Gateway.LoginGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public Usuarios login(String email, String password) {
		//TODO: Get User using DAO and check 		
		Usuarios user = UsuarioDAO.getInstance().find(email);	
		//Generate the hash of the password
		String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");		
		user.setContrasenya(sha1);
		
		if (user.getEmail().equals(email) && user.comprobarContrasenya(password)) {		
			return user;
		} else {
			return null;
		}
	}
	public long loginGoogle(String email, String password) {
		Usuarios user = new Usuarios();		
		user.setEmail("thomas.e2001@gmail.com");
		user.setContrasenya("$!9PhNz,");
		if (user.getEmail().equals(email) && user.comprobarContrasenya(password)) {		
			return LoginGateway.getInstance().loginGoogle(email, password);
		} else {
			return 0;
		}
	
	}
	public String comprobar() {
		return LoginGateway.getInstance().comprobar();		
	}
	public Usuarios registro(String email, String name, Date fecha_nac, double peso, String contrasenya, String frec) {
		// TODO Auto-generated method stub
		Usuarios usuario = new Usuarios();
		if(UsuarioDAO.getInstance().find(email)!= null) {
			System.out.println("User is already in the database.");
			return null;
		}else {
			usuario.setContrasenya(contrasenya);
			usuario.setEmail(email);
			usuario.setFecha_ncto(fecha_nac);
			usuario.setFrecuencia(frec);
			usuario.setName(name);
			usuario.setPeso(peso);
			UsuarioDAO.getInstance().save(usuario);
			return usuario;
		}
	}
}