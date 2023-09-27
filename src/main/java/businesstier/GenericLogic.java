package businesstier;

import java.util.List;
import java.util.function.Supplier;

import intergrationtier.DomainStore;
import intergrationtier.dao.GenericDaoImpl;

public abstract class GenericLogic<G, E, T extends GenericDaoImpl<E>> {

	private final T DAO;
	
	protected GenericLogic( T dao) {
		this.DAO = dao;
	}
	
	protected final T dao() {
		return DAO;
	}
	
	protected <R> R get(Supplier<R> supplier) {
		R r = null;
		try {
			DAO.beginTransaction();
			r = supplier.get();
			
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			DAO.closeTransaction();
		}
		return r;
	}
	
	abstract public List<E> getAll();
	
//	abstract public List<G> getAllContents(String lang);
	
	abstract public E getWithId(int id);
	
//	abstract public G getContentWithId(int id, String lang);
	
	abstract public List<E> search(String search);
	
//	protected void saveResults(String path, List<E> results) {
//		DomainStore.saveResults(path, results);
//	}
//	
//	@SuppressWarnings("unchecked")
//	protected List<E> searchResults(String path) {
//		return (List<E>) DomainStore.searchResults(path);
//	}
	
//	abstract public List<G> getContents(List<E> collection, String lang);
	
//	abstract public G getContent(E full, String lang);
}
