package Main;

import java.rmi.Naming;
import java.util.Calendar;

import DAO.UsuarioDAO;
import Dominio.Reto;
import Dominio.Sesion;
import Dominio.Usuario;
import Remoto.IRemoteFacade;
import Remoto.RemoteFacade;


public class MainProgram2 {

	public static void main(String[] args) {	
		//Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		
		//Initialize DB
		initDB();
		
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade();			
			Naming.rebind(name, remoteFacade);
			System.out.println(" * eAuction server v3 '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" # eAuction Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private static void initDB() {
		try {
			//Create Usuarios
			Usuario user0 = new Usuario();
			user0.setEmail("thomas.e2001@gmail.com");
			user0.setName("Thomas");
			String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("$!9PhNz,");
			user0.setContrasenya(sha1);			
							
			Usuario user1 = new Usuario();
			user1.setEmail("sample@gmail.com");
			user1.setName("buyer33");
			sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("hqc`}3Hb");			
			user1.setContrasenya(sha1);
			
			Usuario user2 = new Usuario();
			user2.setEmail("troyaikman08@hotmail.com");
			user2.setName("troyaikman08");
			sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex("RR$dW69N");			
			user2.setContrasenya(sha1);
			
			//Create Retos
			Reto RetoCorrer = new Reto();
			RetoCorrer.setName("Correr");	
			RetoCorrer.setDistanciaObjetivo(100);
			Reto RetoFutbol = new Reto();
			RetoFutbol.setName("Jugar Futbol");		
			RetoFutbol.setDeporte("Futbol");;
			Reto RetoGimnasio = new Reto();
			RetoGimnasio.setName("Ir al gimnasio");			

			//Create Sesion
			Sesion sesion1 = new Sesion();
			sesion1.setNumber(1);
			sesion1.setDuracion(2);
			sesion1.setPropietario(user0);
			sesion1.setTitulo("Entrenamiento");;
			
			user0.addSesiones(sesion1);
			user1.addSesiones(sesion1);
			
			
						
			//Save Users in the DB
			UsuarioDAO.getInstance().save(user0);
			UsuarioDAO.getInstance().save(user1);
			UsuarioDAO.getInstance().save(user2);
		} catch (Exception ex) {
			System.out.println(" $ Error initializing data base:" + ex.getMessage());
		}			
	}

}
