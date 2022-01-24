package Servicios;

import Dominio.Usuarios;
import Gateway.LoginGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public Usuarios login(String email, String password) {
		//TODO: Get User using DAO and check 		
		Usuarios user = new Usuarios();		
		user.setEmail("thomas.e2001@gmail.com");
		user.setName("Thomas");		
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
}