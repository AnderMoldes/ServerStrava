package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.*;

import Dominio.Sesiones;

public class SesionDAO extends DataAccessObjectBase implements IDataAccessObject<Sesiones> {
	private static SesionDAO instance;	
	
	private SesionDAO() { }
	
	public static SesionDAO getInstance() {
		if (instance == null) {
			instance = new SesionDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Sesiones object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Sesiones object) {
		super.deleteObject(object);
	}

	@Override
	public List<Sesiones> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Sesiones> articles = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Sesiones> extent = pm.getExtent(Sesiones.class, true);

			for (Sesiones category : extent) {
				articles.add(category);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Articles: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return articles;
	}

	@Override
	public Sesiones find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Sesiones result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Sesiones.class.getName() + " WHERE number == " + param);
			query.setUnique(true);
			result = (Sesiones) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an Article: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}
