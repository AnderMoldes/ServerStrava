package Servicios;

import Dominio.Usuario;
import Gateway.LoginGateway;

//TODO: Implement Singleton Pattern
public class LoginAppService {
		
	public Usuario login(String email, String password) {
		//TODO: Get User using DAO and check 		
		Usuario user = new Usuario();		
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
		Usuario user = new Usuario();		
		user.setEmail("thomas.e2001@gmail.com");
		user.setContrasenya("$!9PhNz,");
		if (user.getEmail().equals(email) && user.comprobarContrasenya(password)) {		
			return LoginGateway.getInstance().login(email, password);
		} else {
			return 0;
		}
	
	}
	public String comprobar() {
		return LoginGateway.getInstance().comprobar();		
	}
}