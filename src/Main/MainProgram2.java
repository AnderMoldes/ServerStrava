package Main;

import java.rmi.Naming;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import DAO.RetoDAO;
import DAO.SesionDAO;
import DAO.UsuarioDAO;
import Dominio.Retos;
import Dominio.Sesiones;
import Dominio.Usuarios;
import Remoto.IRemoteFacade;
import Remoto.RemoteFacade;


public class MainProgram2 {

	public static void main(String[] args) {	
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		initDB();
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			IRemoteFacade remoteFacade = new RemoteFacade();			
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava Server v1 '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	private static void initDB() {
		System.out.println("Datanucleus + JDO example");
		System.out.println("=========================");
	
		//Create objects -  objects in memory
		Usuarios user1= new Usuarios();
		user1.setName("Ander Moldes");
		user1.setEmail("amold");
		user1.setContrasenya("1234");
		user1.setFecha_ncto(new Date());
		user1.setFrecuencia("90");
		user1.setPeso(100.0);
		
		Usuarios user2 = new Usuarios();
		user2.setName("Juan Sanz");
		user2.setEmail("juanillo");
		user2.setContrasenya("1234");
		user2.setFecha_ncto(new Date());
		user2.setFrecuencia("90");
		user2.setPeso(100.0);
		
		Sesiones sesion1 = new Sesiones();
		sesion1.setDeporte("Ciclismo");
		sesion1.setDistancia(100);
		sesion1.setFecha_inicio(new Date());
		sesion1.setHora_inicio("13:00");
		sesion1.setDuracion(4);
		sesion1.setNumber(0);
		sesion1.setPropietario(user1);
		sesion1.setTitulo("Ciclismo");
		user1.addSesiones(sesion1);
		
		Sesiones sesion2 = new Sesiones();
		sesion2.setDeporte("Correr");
		sesion2.setDistancia(10);
		sesion2.setFecha_inicio(new Date());
		sesion2.setHora_inicio("11:00");
		sesion2.setDuracion(3);
		sesion2.setNumber(1);
		sesion2.setPropietario(user2);
		sesion2.setTitulo("Running");
		user2.addSesiones(sesion2);

		Retos reto1 = new Retos();
		reto1.setDeporte("Ciclismo");
		reto1.setDistanciaObjetivo(100);
		reto1.setFecha_inicio(new Date());
		reto1.setFecha_fin(new Date());
		reto1.setName("Tour Ispaster");
		reto1.setNumber(2);
		reto1.setUsuario(user1);
		
		Retos reto2 = new Retos();
		reto2.setDeporte("Correr");
		reto2.setDistanciaObjetivo(20);
		reto2.setFecha_inicio(new Date());
		reto2.setFecha_fin(new Date());
		reto2.setName("Lekeitio Run");
		reto2.setNumber(3);
		reto2.setUsuario(user2);

		//UsuarioDAO.getInstance().save(user1);
		//UsuarioDAO.getInstance().save(user2);
		RetoDAO.getInstance().save(reto1);
		RetoDAO.getInstance().save(reto2);
		//SesionDAO.getInstance().save(sesion1);
		//SesionDAO.getInstance().save(sesion2);
		
		

		System.out.println("End of the Datanucleus + JDO example");
		System.out.println("====================================");	
	}
	
}
