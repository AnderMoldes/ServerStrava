package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import Dominio.Reto;

public class RetoDAO extends DataAccessObjectBase implements IDataAccessObject<Reto> {
	private static RetoDAO instance;	
	
	private RetoDAO() { }
	
	public static RetoDAO getInstance() {
		if (instance == null) {
			instance = new RetoDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Reto object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Reto object) {
		super.deleteObject(object);
	}

	@Override
	public List<Reto> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Reto> articles = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Reto> extent = pm.getExtent(Reto.class, true);

			for (Reto category : extent) {
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
	public Reto find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Reto result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Reto.class.getName() + " WHERE number == " + param);
			query.setUnique(true);
			result = (Reto) query.execute();
			
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
