package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.*;

import Dominio.Sesion;

public class SesionDAO extends DataAccessObjectBase implements IDataAccessObject<Sesion> {
	private static SesionDAO instance;	
	
	private SesionDAO() { }
	
	public static SesionDAO getInstance() {
		if (instance == null) {
			instance = new SesionDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Sesion object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Sesion object) {
		super.deleteObject(object);
	}

	@Override
	public List<Sesion> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Sesion> articles = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Sesion> extent = pm.getExtent(Sesion.class, true);

			for (Sesion category : extent) {
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
	public Sesion find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Sesion result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Sesion.class.getName() + " WHERE number == " + param);
			query.setUnique(true);
			result = (Sesion) query.execute();
			
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
