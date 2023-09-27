package intergrationtier.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import intergrationtier.DataSource;

public abstract class GenericDaoImpl<T> {
	
	private final Class<T> entityClass;
	
	private Session session;
//	private DataSource dataSource;
	
	public GenericDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public void beginTransaction() {
		session = DataSource.getSession();
		session.beginTransaction();
		
	}
	public Session getSession() {
		return this.session;
	}
	
	public abstract T findById(int id);
	
	public abstract List<T> findAll();
	
	public abstract List<T> findByContaining(String search);
	
	protected T findResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;
		try {
			Query<T> query = checkCreateAndSetNamedQuery(namedQuery);
			query = setParameters(query, parameters);
			result = query.getSingleResult();
//			System.out.println("find result: " + result);
		} catch (NoResultException e) {
			String message = "No ersult found for named query: " + namedQuery;
			Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.WARNING, message, e);
		}
		catch (Exception e) {
			Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return result;
	}
	
	protected List<T> findResults(String namedQuery, Map<String, Object> parameters) {
		List<T> results = null;
		try {

			Query<T> query = checkCreateAndSetNamedQuery(namedQuery);
			query = setParameters(query, parameters);

			results = query.getResultList();
//			System.out.println("find results: " + results.size());
		} catch (NoResultException e) {
			String message = "No rsult found for named query: " + namedQuery;
			Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.WARNING, message, e);
		}
		catch (Exception e) {
			Logger.getLogger(GenericDaoImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return results;
	}
	
	private Query<T> setParameters(Query<T> namedQuery, Map<String, Object> parameters) {
		if (parameters != null && !parameters.isEmpty()) {
			parameters.entrySet().forEach((entry) -> {
				Logger.getLogger(getClass().getName()).log(Level.INFO, "setParameters: {0}", new String[]{entry.toString()});
				namedQuery.setParameter(entry.getKey(), entry.getValue());
			});
		}
		return namedQuery;
	}

	private Query<T> checkCreateAndSetNamedQuery(String namedQuery) throws ParseException{
		Objects.requireNonNull(namedQuery, "named query cannot be null");
		
		
//		Timestamp time = new Timestamp(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-06-01 00:00:00").getTime());
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime time = LocalDateTime.parse("2023-09-01 00:00:00", formatter);
//		session.enableFilter("startDateFilter");
//		Timestamp ts = Timestamp.valueOf(time);
//		dateFilter.setParameter("startDate", ts);
//		
//		System.out.println(session.getEnabledFilter("startDateFilter").getFilterDefinition().toString());

		return session.<T>createNamedQuery(namedQuery, entityClass);
		
	}

	public void closeTransaction() {
		session.close();
	}


}
