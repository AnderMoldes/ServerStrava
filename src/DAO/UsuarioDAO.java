package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import Dominio.Usuarios;
public class UsuarioDAO extends DataAccessObjectBase implements IDataAccessObject<Usuarios> {
	private static UsuarioDAO instance;	
	
	private UsuarioDAO() { }
	
	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}		
		
		return instance;
	}	
	
	@Override
	public void save(Usuarios object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Usuarios object) {
		super.deleteObject(object);
	}

	@Override
	public List<Usuarios> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Usuarios> users = new ArrayList<>();

		try {
			tx.begin();
			
			Extent<Usuarios> userExtent = pm.getExtent(Usuarios.class, true);
			
			for (Usuarios user : userExtent) {
				users.add(user);
			}
						
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying all users: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return users;
	}

	@Override
	public Usuarios find(String param) {		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		Usuarios result = null; 

		try {
			tx.begin();
			
			Query<?> query = pm.newQuery("SELECT FROM " + Usuarios.class.getName() + " WHERE email == '" + param + "'");
			query.setUnique(true);
			result = (Usuarios) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}
