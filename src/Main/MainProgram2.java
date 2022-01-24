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

import DAO.UsuarioDAO;
import Dominio.Retos;
import Dominio.Sesiones;
import Dominio.Usuarios;
import Remoto.IRemoteFacade;
import Remoto.RemoteFacade;


public class MainProgram2 {

	public static void main(String[] args) {	
		System.out.println("Datanucleus + JDO example");
		System.out.println("=========================");
	
		//Create objects -  objects in memory
		Usuarios user1= new Usuarios();
		user1.setName("Ander Moldes");
		user1.setEmail("amold");
		user1.setContrasenya("1234");
		
		Usuarios user2 = new Usuarios();
		user1.setName("Juan Sanz");
		user1.setEmail("juanillo");
		user1.setContrasenya("1234");
		
		Sesiones sesion1 = new Sesiones();
		sesion1.setDeporte("Ciclismo");
		sesion1.setDistancia(100);
		sesion1.setFecha_inicio(new Date("11-12-2001"));
		sesion1.setHora_inicio("13:00");
		sesion1.setDuracion(4);
		sesion1.setNumber(0);
		sesion1.setPropietario(user1);
		user1.addSesiones(sesion1);
		
		Sesiones sesion2 = new Sesiones();
		sesion2.setDeporte("Correr");
		sesion2.setDistancia(10);
		sesion2.setFecha_inicio(new Date("11-12-2004"));
		sesion2.setHora_inicio("11:00");
		sesion2.setDuracion(3);
		sesion2.setNumber(1);
		sesion2.setPropietario(user2);
		user2.addSesiones(sesion2);

		Retos reto1 = new Retos();
		reto1.setDeporte("Ciclismo");
		reto1.setDistanciaObjetivo(100);
		reto1.setFecha_inicio(new Date("11-12-2001"));
		reto1.setFecha_fin(new Date("18-12-2001"));
		reto1.setName("Tour Ispaster");
		reto1.setNumber(2);
		reto1.setUsuario(user1);
		
		Retos reto2 = new Retos();
		reto2.setDeporte("Correr");
		reto2.setDistanciaObjetivo(20);
		reto2.setFecha_inicio(new Date("11-12-2009"));
		reto2.setFecha_fin(new Date("18-12-2009"));
		reto2.setName("Lekeitio Run");
		reto2.setNumber(3);
		reto2.setUsuario(user2);

		
								
		// Load Persistence Manager Factory - referencing the Persistence Unit defined in persistence.xml
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		// Persistence Manager
		PersistenceManager pm = null;
		//Transaction to group DB operations
		Transaction tx = null;		
		
		try {
			System.out.println("- Store objects in the DB");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			
			//Start the transaction
			tx.begin();
			
			pm.makePersistent(user1);
			pm.makePersistent(user2);			
			
			//End the transaction
			tx.commit();
			
			System.out.println("    - " + sesion1.getTitulo() + "($ " + sesion2.getTitulo() + ")");

		} catch (Exception ex) {
			System.err.println(" $ Error storing objects in the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}
		
		try {
			System.out.println("- Retrieving all the Sessions using an 'Extent'...");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();
		
			Extent<Sesiones> extent = pm.getExtent(Sesiones.class, true);
			
			for (Sesiones session : extent) {
				System.out.println(" -> " + session);
			}
			//Notice the change in the accounts' balances
			//End the transaction
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error retrieving accounts using an 'Extent': " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

		try {
			System.out.println("- Retrieving propietary of sesion with a 'Query'...");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();

			Query<Sesiones> query = pm.newQuery(Sesiones.class);
			
			@SuppressWarnings("unchecked")
			List<Sesiones> sesiones = (List<Sesiones>) query.execute();

			//End the transaction
			tx.commit();
			
			for (Sesiones sesion : sesiones) {
				System.out.println("  -> " + sesion.getPropietario());
			}
		} catch (Exception ex) {
			System.err.println(" $ Error retrieving accounts using a 'Query': " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

		try {
			System.out.println("- Deleting 'Session -> Propietario' relation...");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();		
			//Start the transaction
			tx.begin();

			Query<Sesiones> query = pm.newQuery(Sesiones.class);
			@SuppressWarnings("unchecked")
			List<Sesiones> sessions = (List<Sesiones>) query.execute();
			
			for (Sesiones ses : sessions) {
				System.out.println("  -> Retrieved user: " + ses.getTitulo());
				System.out.println("     + Removing user from the sessions ... ");
				//ses.removeSession();
			}
			
			//End the transaction
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error deleting 'User->Address' relation: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

		try {
			System.out.println("- Cleaning the DB...");			
			//Get the Persistence Manager
			pm = pmf.getPersistenceManager();
			//Obtain the current transaction
			tx = pm.currentTransaction();
			//Start the transaction
			tx.begin();
			
			//Delete users from DB
			// As we are considering accounts as dependents on user - CASCADING BEHAVIOUR - ACCOUNTS DELETED
			Query<Usuarios> query1 = pm.newQuery(Usuarios.class);
			System.out.println(" * '" + query1.deletePersistentAll() + "' users and their accounts deleted from the DB.");
			//Delete addresses from DB
			Query<Sesiones> query2 = pm.newQuery(Sesiones.class);
			System.out.println(" * '" + query2.deletePersistentAll() + "' addresses deleted from the DB.");
			
			//End the transaction
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			
			if (pm != null && !pm.isClosed()) {
				pm.close();
			}
		}

		System.out.println("End of the Datanucleus + JDO example");
		System.out.println("====================================");	
	}
}
